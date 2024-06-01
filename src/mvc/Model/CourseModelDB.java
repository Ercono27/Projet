package mvc.Model;

import Sport.Coureur;
import Sport.CoureurPlaceGain;
import Sport.Course;
import Sport.Ville;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseModelDB extends DAOCourse {
    protected Connection dbConnect;
    private DAOCoureur daoCoureur;
    private DAOVille daoVille;

    public CourseModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion.");
            System.exit(1);
        }
        daoCoureur = new CoureurModelDB();
        daoVille = new VilleModelDB();
    }

    @Override
    public Course addCourse(Course course) {
        String query1 = "insert into APICOURSE(nom,pricemoney,km) values(?,?,?)";
        String query2 = "select idcourse from APICOURSE where nom=? and pricemoney=? and km=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, course.getNom());
            pstm1.setBigDecimal(2, course.getPriceMoney());
            pstm1.setInt(3, course.getKm());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, course.getNom());
                pstm2.setBigDecimal(2, course.getPriceMoney());
                pstm2.setInt(3, course.getKm());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idCourse = rs.getInt(1);
                    course.setIdCourse(idCourse);
                    notifyObservers();
                    return course;
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
    public boolean removeCourse(Course course) {
        String query = "delete from APICOURSE where idCOURSE = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
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
    public Course updateCourse(Course course) {
        String query = "update APICOURSE set nom=?,pricemoney=?,km=? where idCourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, course.getNom());
            pstm.setBigDecimal(2, course.getPriceMoney());
            pstm.setInt(3, course.getKm());
            pstm.setInt(4, course.getIdCourse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readCourse(course.getIdCourse());
            else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Course readCourse(int idCourse) {
        String query = "select * from APICOURSE where idcourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idCourse);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                BigDecimal pm = rs.getBigDecimal(3);
                int km = rs.getInt(4);
                Course c = new Course(idCourse, nom, pm, km);
                return c;
            } else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Course> getCourses() {
        List<Course> lc = new ArrayList<>();
        String query = "select * from APICOURSE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idcourse = rs.getInt(1);
                String nom = rs.getString(2);
                BigDecimal pm = rs.getBigDecimal(3);
                int km = rs.getInt(4);
                Course c = new Course(idcourse, nom, pm, km);
                lc.add(c);
            }
            return lc;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<CoureurPlaceGain> listeCoureurPlaceGain(Course c) {
        List<CoureurPlaceGain> lc = new ArrayList<>();
        String query = "select * from APICLASSEMENT where idcourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, c.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int place = rs.getInt(2);
                BigDecimal gain = rs.getBigDecimal(3);
                int idCoureur = rs.getInt(4);
                Coureur coureur = daoCoureur.readCoureur(idCoureur);
                lc.add(new CoureurPlaceGain(coureur, gain, place));
            }
            return lc;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public BigDecimal gainTotal(Course c) {
        BigDecimal total = BigDecimal.valueOf(0);
        String query = "select SUM(GAIN) from APICLASSEMENT where idcourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, c.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                total = rs.getBigDecimal(1);
            }
            return total;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public Coureur vainqueur(Course c) {
        int idcoureur = -1;
        String query = "select idCoureur from APICLASSEMENT where idCourse = ? and place = 1";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, c.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                idcoureur = rs.getInt(1);
            }
            return daoCoureur.readCoureur(idcoureur);
        } catch (
                SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public boolean adCoureur(Coureur coureur, Course c) {
        String query1 = "INSERT INTO APICLASSEMENT(place, gain, idCoureur, idCourse) VALUES(?, ?, ?, ?)";
        String query2 = "SELECT idclassement FROM APICLASSEMENT WHERE place=? AND gain=? AND idCoureur=? AND idCourse=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setInt(1, 0);
            pstm1.setBigDecimal(2, BigDecimal.valueOf(0));
            pstm1.setInt(3, coureur.getIdCoureur());
            pstm1.setInt(4, c.getIdCourse());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setInt(1, 0);
                pstm2.setBigDecimal(2, BigDecimal.valueOf(0));
                pstm2.setInt(3, coureur.getIdCoureur());
                pstm2.setInt(4, c.getIdCourse());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    System.out.println("Record introuvable");
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return false;
        }
    }

    @Override
    public boolean supCoureur(Coureur coureur, Course course) {
        String query = "DELETE FROM APICLASSEMENT where idCoureur=? AND idCourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, coureur.getIdCoureur());
            pstm.setInt(2, course.getIdCourse());
            int n = pstm.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return false;
        }
    }

    @Override
    public boolean resultat(Coureur coureur, int place, BigDecimal gain, Course course) {
        String query = "update APICLASSEMENT set place=?,gain=? where idCourse = ? AND idCoureur = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, place);
            pstm.setBigDecimal(2, gain);
            pstm.setInt(3, course.getIdCourse());
            pstm.setInt(4, coureur.getIdCoureur());
            int n = pstm.executeUpdate();
            notifyObservers();
            return n != 0;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean modifierC(Coureur coureur, int place, BigDecimal gain, Course course) {
        String query = "update APICLASSEMENT set place=?,gain=? where idCourse = ? AND idCoureur = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, place);
            pstm.setBigDecimal(2, gain);
            pstm.setInt(3, course.getIdCourse());
            pstm.setInt(4, coureur.getIdCoureur());
            int n = pstm.executeUpdate();
            notifyObservers();
            return n != 0;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean adVille(Course course, Ville ville) {
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);
        String query1 = "INSERT INTO APIINFOS(DEPARTDATE,IDVILLE,IDCOURSE)VALUES(?,?,?)";
        String query2 = "SELECT idINFOS from APIINFOS WHERE DEPARTDATE=? AND IDVILLE=? AND IDCOURSE=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setDate(1, sqlDate);
            pstm1.setInt(2, ville.getIdVille());
            pstm1.setInt(3, course.getIdCourse());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setDate(1, sqlDate);
                pstm2.setInt(2, ville.getIdVille());
                pstm2.setInt(3, course.getIdCourse());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    System.out.println("Record introuvable");
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return false;
        }
    }

    @Override
    public boolean supVille(Course course, Ville ville) {
        String query = "DELETE FROM APIINFOS where idVille=? AND idCourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, ville.getIdVille());
            pstm.setInt(2, course.getIdCourse());
            int n = pstm.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return false;
        }
    }

    @Override
    public List<Ville> listeVille(Course course) {
        List<Ville> lv = new ArrayList<>();
        String query = "select idVille from APIINFOS where idcourse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idVille = rs.getInt(1);
                Ville ville = daoVille.readVille(idVille);
                lv.add(ville);
            }
            return lv;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    @Override
    public boolean classementComplet(Course course) {
        int place;
        String query = "select PLACE from APICLASSEMENT where idCourse =?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, course.getIdCourse());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                place = rs.getInt(1);
                if (place == 0) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return false;
        }
    }

    @Override
    public List getNotification() {
        return getCourses();
    }
}
