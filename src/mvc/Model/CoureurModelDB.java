package mvc.Model;

import Sport.*;
import mvc.Controller.VilleController;
import myconnections.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CoureurModelDB extends DAOCoureur {
    protected Connection dbConnect;
    private DAOVille daoVille;

    public CoureurModelDB(DAOVille daoVille) {
        this.daoVille = daoVille;
    }

    public CoureurModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion.");
            System.exit(1);
        }
    }

    public Coureur addCoureur(Coureur coureur) {
        String query1 = "insert into APICOUREUR(matricule,nom,prenom,datenaiss,nationalite,idville) values(?,?,?,?,?,?)";
        String query2 = "select idcourse from APICOURSE where matricule =? and nom=? and prenom=? and datenaiss=? and nationalite=? and idville=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, coureur.getMatricule());
            pstm1.setString(2, coureur.getNom());
            pstm1.setString(3, coureur.getPrenom());
            pstm1.setDate(4, Date.valueOf(coureur.getDateNaiss()));
            pstm1.setString(5, coureur.getNationalite());
            pstm1.setInt(6, coureur.getVilleResidence().getIdVille());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, coureur.getMatricule());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idCoureur = rs.getInt(1);
                    coureur.setIdCoureur(idCoureur);
                    notifyObservers();
                    return coureur;
                } else {
                    System.out.println("record introuvable");
                    return null;
                }
            } else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public boolean removeCoureur(Coureur coureur) {
        String query = "delete from APICOUREUR where idCOUREUR = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, coureur.getIdCoureur());
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
    public Coureur updateCoureur(Coureur coureur) {
        String query = "update APICOUREUR set matricule=? where idCoureur = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, coureur.getMatricule());
            pstm.setInt(2, coureur.getIdCoureur());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readCoureur(coureur.getIdCoureur());
            else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Coureur readCoureur(int idCoureur) {
        String query = "select * from APICOUREUR where idcoureur = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idCoureur);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                LocalDate dateNaiss = rs.getDate(5).toLocalDate();
                String nationalite = rs.getString(6);
                int villeId = rs.getInt(7);
                Ville villeResidence = daoVille.readVille(villeId);
                return new Coureur(idCoureur, matricule, nom, prenom, dateNaiss, nationalite, villeResidence);
            } else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Coureur> getCoureurs() {
        List<Coureur> lc = new ArrayList<>();
        String query = "select * from APICOUREUR";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idcoureur = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                LocalDate dateNaiss = rs.getDate(5).toLocalDate();
                String nationalite = rs.getString(6);
                int villeId = rs.getInt(7);
                Ville villeResidence = daoVille.readVille(villeId);

                Coureur c = new Coureur(idcoureur, matricule, nom, prenom, dateNaiss, nationalite, villeResidence);
                lc.add(c);
            }
            return lc;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List getNotification() {
        return getCoureurs();
    }
}
