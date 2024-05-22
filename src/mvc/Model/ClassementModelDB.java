package mvc.Model;

import Sport.Classement;
import Sport.Coureur;
import Sport.Course;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassementModelDB extends DAOClassement {
    private Connection dbConnect;
    private DAOCourse daoCourse;
    private DAOCoureur daoCoureur;

    public ClassementModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion.");
            System.exit(1);
        }
        daoCourse=new CourseModelDB();
        daoCoureur=new CoureurModelDB();
    }

    @Override
    public Classement addClassement(Classement classement) {
        String query1 = "INSERT INTO APICLASSEMENT(place, gain, idCoureur, idCourse) VALUES(?, ?, ?, ?)";
        String query2 = "SELECT idclassement FROM APICLASSEMENT WHERE place=? AND gain=? AND idCoureur=? AND idCourse=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setInt(1, classement.getPlace());
            pstm1.setBigDecimal(2, classement.getGain());
            pstm1.setInt(3, classement.getCour().getIdCoureur());
            pstm1.setInt(4, classement.getCourse().getIdCourse());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setInt(1, classement.getPlace());
                pstm2.setBigDecimal(2, classement.getGain());
                pstm2.setInt(3, classement.getCour().getIdCoureur());
                pstm2.setInt(4, classement.getCourse().getIdCourse());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idClassement = rs.getInt(1);
                    classement.setIdClassement(idClassement);
                    notifyObservers();
                    return classement;
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
    public boolean removeClassement(Classement classement) {
        String query = "DELETE FROM APICLASSEMENT WHERE idClassement = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, classement.getIdClassement());
            int n = pstm.executeUpdate();
            notifyObservers();
            return n != 0;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return false;
        }
    }

    @Override
    public Classement updateClassement(Classement classement) {
        String query = "UPDATE APICLASSEMENT SET place = ?, gain = ?, idCoureur = ?, idCourse = ? WHERE idClassement = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, classement.getPlace());
            pstm.setBigDecimal(2, classement.getGain());
            pstm.setInt(3, classement.getCour().getIdCoureur());
            pstm.setInt(4, classement.getCourse().getIdCourse());
            pstm.setInt(5, classement.getIdClassement());
            int n = pstm.executeUpdate();
            notifyObservers();
            return n != 0 ? readClassement(classement.getIdClassement()) : null;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public Classement readClassement(int idClassement) {
        String query = "SELECT * FROM APICLASSEMENT WHERE idClassement = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idClassement);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int place = rs.getInt(2);
                BigDecimal gain = rs.getBigDecimal(3);
                int coureurId = rs.getInt(4);
                Coureur coureur = daoCoureur.readCoureur(coureurId);
                int courseId = rs.getInt(5);
                Course course = daoCourse.readCourse(courseId);
                return new Classement(idClassement, place, gain, coureur, course);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public List<Classement> getClassements() {
        List<Classement> lc = new ArrayList<>();
        String query = "SELECT * FROM APICLASSEMENT";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idClassement = rs.getInt(1);
                int place = rs.getInt(2);
                BigDecimal gain = rs.getBigDecimal(3);
                int coureurId = rs.getInt(4);
                Coureur coureur = daoCoureur.readCoureur(coureurId);
                int courseId = rs.getInt(5);
                Course course = daoCourse.readCourse(courseId);
                lc.add(new Classement(idClassement, place, gain, coureur, course));
            }
            return lc;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public List getNotification() {
        return getClassements();
    }
}
