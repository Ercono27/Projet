package mvc.Model;

import Sport.Course;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseModelDB extends DAOCourse{
    protected Connection dbConnect;
    public CourseModelDB(){
        dbConnect= DBConnection.getConnection();
        if (dbConnect==null){
            System.err.println("Erreur de connexion.");
            System.exit(1);
        }
    }

    @Override
    public Course addCourse(Course course) {
        String query1 = "insert into APICOURSE(nom,pricemoney,km) values(?,?,?)";
        String query2 = "select idcourse from APICOURSE where nom=? and pricemoney=? and km=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,course.getNom());
            pstm1.setBigDecimal(2,course.getPriceMoney());
            pstm1.setInt(3,course.getKm());
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,course.getNom());
                pstm2.setBigDecimal(2,course.getPriceMoney());
                pstm2.setInt(3,course.getKm());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idCourse= rs.getInt(1);
                    course.setIdCourse(idCourse);
                    notifyObservers();
                    return course;
                }
                else {
                    System.out.println("record introuvable");
                    return null;
                }
            }
            else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
    }
    @Override
    public boolean removeCourse(Course course) {
        String query = "delete from APICOURSE where idCOURSE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,course.getIdCourse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return false;
        }
    }
    @Override
    public Course updateCourse(Course course) {
        String query = "update APICOURSE set nom=?,pricemoney=?,km=? where idCourse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,course.getNom());
            pstm.setBigDecimal(2,course.getPriceMoney());
            pstm.setInt(3,course.getKm());
            pstm.setInt(4,course.getIdCourse());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readCourse(course.getIdCourse());
            else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Course readCourse(int idCourse) {
        String query = "select * from APICOURSE where idcourse = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idCourse);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                BigDecimal pm = rs.getBigDecimal(3);
                int km = rs.getInt(4);
                Course c = new Course(idCourse,nom,pm,km);
                return c;
            }
            else return null;
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<Course> getCourses() {
        List<Course> lc=new ArrayList<>();
        String query="select * from APICOURSE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idcourse = rs.getInt(1);
                String nom = rs.getString(2);
                BigDecimal pm = rs.getBigDecimal(3);
                int km = rs.getInt(4);
                Course c = new Course(idcourse,nom,pm,km);
                lc.add(c);
            }
        return lc;
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
    }
    @Override
    public List getNotification() {
        return getCourses();
    }
}
