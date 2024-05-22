package mvc.Model;

import Sport.*;
import myconnections.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InfosModelDB extends DAOInfos {
    private Connection dbConnect;
    private DAOVille daoVille;
    private DAOCourse daoCourse;

    public InfosModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion.");
            System.exit(1);
        }
        daoVille=new VilleModelDB();
        daoCourse=new CourseModelDB();
    }

    @Override
    public Infos addInfos(Infos infos) {
        String query1 = "INSERT INTO APIINFOS(departDate, idVille) VALUES(?, ?)";
        String query2 = "SELECT idInfos FROM APIINFOS WHERE departDate=? AND idVille=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setDate(1, Date.valueOf(infos.getDepartDate()));
            pstm1.setInt(2, infos.getVille().getIdVille());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setDate(1, Date.valueOf(infos.getDepartDate()));
                pstm2.setInt(2, infos.getVille().getIdVille());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idInfos = rs.getInt(1);
                    infos.setIdInfos(idInfos);
                    notifyObservers();
                    return infos;
                } else {
                    System.out.println("Record introuvable");
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public boolean removeInfos(Infos infos) {
        String query = "DELETE FROM APIINFOS WHERE idInfos = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, infos.getIdInfos());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return false;
        }
    }

    @Override
    public Infos updateInfos(Infos infos) {
        String query = "UPDATE APIINFOS SET departDate = ?, idVille = ?,idCourse=? WHERE idInfos = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setDate(1, Date.valueOf(infos.getDepartDate()));
            pstm.setInt(2, infos.getVille().getIdVille());
            pstm.setInt(3, infos.getCourse().getIdCourse());
            pstm.setInt(4, infos.getIdInfos());
            int n = pstm.executeUpdate();
            notifyObservers();
            return n != 0 ? readInfos(infos.getIdInfos()) : null;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public Infos readInfos(int idInfos) {
        String query = "SELECT * FROM APIINFOS WHERE idInfos = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idInfos);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                LocalDate departDate = rs.getDate(2).toLocalDate();
                int villeId = rs.getInt(3);
                Ville ville = daoVille.readVille(villeId);
                int courseId= rs.getInt(4);
                Course course = daoCourse.readCourse(courseId);
                return new Infos(idInfos, departDate, ville,course);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public List<Infos> getInfos() {
        List<Infos> li = new ArrayList<>();
        String query = "SELECT * FROM APIINFOS";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idInfos = rs.getInt(1);
                LocalDate departDate = rs.getDate(2).toLocalDate();
                int villeId = rs.getInt(3);
                Ville ville = daoVille.readVille(villeId);
                int courseId=rs.getInt(4);
                Course course=daoCourse.readCourse(courseId);
                li.add(new Infos(idInfos, departDate, ville,course));
            }
            return li;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }
    @Override
    public List getNotification() {
        return getInfos();
    }
}
