package mvc.Model;

import Sport.Coureur;
import Sport.Course;
import Sport.Ville;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VilleModelDB extends DAOVille {
    private Connection dbConnect;

    public VilleModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion.");
            System.exit(1);
        }
    }

    @Override
    public Ville addVille(Ville ville) {
        String query1 = "INSERT INTO APIVILLE(nom, latitude, longitude, pays) VALUES (?, ?, ?, ?)";
        String query2 = "SELECT idville FROM APIVILLE WHERE nom = ? AND latitude = ? AND longitude = ? AND pays = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, ville.getNom());
            pstm1.setDouble(2, ville.getLatitude());
            pstm1.setDouble(3, ville.getLongitude());
            pstm1.setString(4, ville.getPays());

            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, ville.getNom());
                pstm2.setDouble(2, ville.getLatitude());
                pstm2.setDouble(3, ville.getLongitude());
                pstm2.setString(4, ville.getPays());

                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idVille = rs.getInt(1);
                    ville.setIdVille(idVille);
                    notifyObservers();
                    return ville;
                } else {
                    System.out.println("Erreur lors de la récupération de l'ID de la ville nouvellement insérée");
                    return null;
                }
            } else {
                System.out.println("Erreur lors de l'insertion de la ville");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
            return null;
        }
    }


    @Override
    public boolean removeVille(Ville ville) {
        String query = "delete from APIVILLE where idVille = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, ville.getIdVille());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public Ville updateVille(Ville ville) {
        String query = "update APIVILLE set nom=?, latitude=?, longitude=?,pays=? where idVille = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, ville.getNom());
            pstm.setDouble(2, ville.getLatitude());
            pstm.setDouble(3, ville.getLongitude());
            pstm.setString(4, ville.getPays());
            pstm.setInt(5, ville.getIdVille());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readVille(ville.getIdVille());
            else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Ville readVille(int idVille) {
        String query = "select * from APIVILLE where idville = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idVille);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                double latitude = rs.getDouble(3);
                double longitude = rs.getDouble(4);
                String pays = rs.getString(5);
                Ville v = new Ville(idVille, nom, latitude, longitude, pays);
                return v;
            } else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Ville> getVilles() {
        List<Ville> lc = new ArrayList<>();
        String query = "select * from APIVILLE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idVille = rs.getInt(1);
                String nom = rs.getString(2);
                double latitude = rs.getDouble(3);
                double longitude = rs.getDouble(4);
                String pays = rs.getString(5);
                Ville v = new Ville(idVille, nom, latitude, longitude, pays);
                lc.add(v);
            }
            return lc;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }
    @Override
    public List<Coureur> getCoureurByVille(int idVille){
        return List.of();
    }
    @Override
    public List<Coureur>getCoureurAyantCouruDansVille(int idVille){return List.of();}

    @Override
    public List getNotification() {
        return getVilles();
    }
}
