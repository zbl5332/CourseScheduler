
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ziyulin
 */
public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getCourseList;
    private static PreparedStatement getDescription;
    private static ResultSet resultSet;
    
    public static void addClass(ClassEntry classes)
    {
        connection = DBConnection.getConnection();
        try
        {
            getDescription = connection.prepareStatement("SELECT description FROM app.courses WHERE courseCode = ?");
            getDescription.setString(1, classes.getCourseCode());
            ResultSet resultSet = getDescription.executeQuery();
            
            String description = "";
            if (resultSet.next()) {
                description = resultSet.getString("description");
            }
            
            addClass = connection.prepareStatement("insert into app.classes (semester, courseCode, description, seats) values (?, ?, ?, ?)");
            addClass.setString(1, classes.getSemester());
            addClass.setString(2, classes.getCourseCode());
            addClass.setString(3, description);
            addClass.setInt(4, classes.getSeats());

            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getCourseList = connection.prepareStatement("select coursecode from app.classes where semester = ?");
            getCourseList.setString(1, semester);
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
    }
    
    public static int getClassSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int count = 0;
        try
        {
            getCourseList = connection.prepareStatement("select seats from app.classes where semester = ? and coursecode = ?");
            getCourseList.setString(1, semester);
            getCourseList.setString(2, courseCode);
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
                count = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    
    public static void dropClass (String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
            getCourseList = connection.prepareStatement("delete from app.classes where semester = ? and coursecode = ?");
            getCourseList.setString(1, semester);
            getCourseList.setString(2, courseCode);
            getCourseList.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
}
