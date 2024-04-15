
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
public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getClassDescription;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getStudentDetails;
    private static ResultSet scheduledResultSet;
    private static ResultSet studentscheduledResultSet;
    private static ResultSet waitlistedResultSet;
    private static ResultSet studentwaitlistedResultSet;
    private static ResultSet resultSet;
    
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classDescriptions = new ArrayList<ClassDescription>();
        try
        {
            getClassDescription = connection.prepareStatement("select coursecode, description, seats from app.classes where semester = ?");
            getClassDescription.setString(1, semester);
            resultSet = getClassDescription.executeQuery();
            
            while(resultSet.next())
            {
                String courseCode = resultSet.getString("coursecode");
                String description = resultSet.getString("description");
                int seats = resultSet.getInt("seats");
                
                ClassDescription classDescription = new ClassDescription(courseCode, description, seats);
                classDescriptions.add(classDescription);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classDescriptions;
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode)
    {
        
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> scheduledStudents = new ArrayList<StudentEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select studentid from app.schedule where semester = ? and courseCode = ? and status = 'Scheduled'");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, courseCode);
            scheduledResultSet = getScheduleByStudent.executeQuery();
            
            while(scheduledResultSet.next())
            {
                String studentId = scheduledResultSet.getString("studentid");
                getStudentDetails = connection.prepareStatement("select lastname, firstname from app.student where studentid = ?");
                getStudentDetails.setString(1, studentId);
                studentscheduledResultSet = getStudentDetails.executeQuery();
                if (studentscheduledResultSet.next()) {
                    String firstName = studentscheduledResultSet.getString("firstname");
                    String lastName = studentscheduledResultSet.getString("lastname");
                    scheduledStudents.add(new StudentEntry(studentId, firstName, lastName));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledStudents;
    }
    
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode)
    {
        
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> waitlistedStudents = new ArrayList<StudentEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select studentid from app.schedule where semester = ? and courseCode = ? and status = 'Waitlisted'");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, courseCode);
            waitlistedResultSet = getScheduleByStudent.executeQuery();
            
            while(waitlistedResultSet.next())
            {
                String studentId = waitlistedResultSet.getString("studentid");
                getStudentDetails = connection.prepareStatement("select lastname, firstname from app.student where studentid = ?");
                getStudentDetails.setString(1, studentId);
                studentwaitlistedResultSet = getStudentDetails.executeQuery();
                if (studentwaitlistedResultSet.next()) {
                    String firstName = studentwaitlistedResultSet.getString("firstname");
                    String lastName = studentwaitlistedResultSet.getString("lastname");
                    waitlistedStudents.add(new StudentEntry(studentId, firstName, lastName));
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistedStudents;
        
    }
}
