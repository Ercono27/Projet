package demodb;

import Sport.Course;
import Sport.Ville;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class GestionCourse {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.tous\n6.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }

    public void ajout() {

        System.out.print("Nom :");
        String nom = sc.nextLine();
        System.out.print("PriceMoney :");
        BigDecimal pm = sc.nextBigDecimal();
        System.out.print("Kilometrage :");
        int km = sc.nextInt();
        String query1 = "insert into APICOURSE(nom,pricemoney,km) values(?,?,?)";
        String query2 = "select idcourse from APICOURSE where nom=? and pricemoney=? and km=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,nom);
            pstm1.setBigDecimal(2,pm);
            pstm1.setInt(3,km);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,nom);
                pstm2.setBigDecimal(2,pm);
                pstm2.setInt(3,km);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idCourse= rs.getInt(1);
                    System.out.println("idCourse = "+idCourse);
                }
                else System.out.println("record introuvable");
            }
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }


    public void recherche() {

        System.out.println("id de la course recherchée ");
        int idrech = sc.nextInt();
        String query = "select * from APICOURSE where idcourse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                BigDecimal pm = rs.getBigDecimal(3);
                int km = rs.getInt(4);
                Course c = new Course(idrech,nom,pm,km);
                System.out.println(c);
            }
            else System.out.println("record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public void modification() {
        System.out.println("id de la course recherchée :");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau price money : ");
        BigDecimal npm = sc.nextBigDecimal();
        String query = "update APICOURSE set pricemoney=? where idCourse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setBigDecimal(1,npm);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }
    public void suppression() {
        System.out.println("id de la course à supprimée : ");
        int idrech = sc.nextInt();
        String query = "delete from APICOURSE where idCOURSE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    private void tous() {
        String query="select * from APICOURSE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idcourse = rs.getInt(1);
                String nom = rs.getString(2);
                BigDecimal pm = rs.getBigDecimal(3);
                int km = rs.getInt(4);
                Course c = new Course(idcourse,nom,pm,km);
                System.out.println(c);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }


    public static void main(String[] args) {
        GestionCourse g = new GestionCourse();
        g.gestion();
    }

}
