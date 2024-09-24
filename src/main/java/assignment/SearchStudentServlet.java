package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
      
        String studentId = request.getParameter("studentId");

    
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            
   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wip?useSSL=false", "root", "root");

           
            String query = "SELECT * FROM student_profile WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, studentId);

        
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                out.println("<h2>Student Details</h2>");
                out.println("<ul>");
                out.println("<li><strong>First Name:</strong> " + rs.getString("first_name") + "</li>");
                out.println("<li><strong>Last Name:</strong> " + rs.getString("last_name") + "</li>");
                out.println("<li><strong>Gender:</strong> " + rs.getString("gender") + "</li>");
                out.println("<li><strong>Class:</strong> " + rs.getString("class") + "</li>");
                out.println("<li><strong>Blood Group:</strong> " + rs.getString("blood_group") + "</li>");
                out.println("<li><strong>Date of Birth:</strong> " + rs.getString("date_of_birth") + "</li>");
                out.println("<li><strong>Year of Admission:</strong> " + rs.getString("year_of_admission") + "</li>");
                out.println("<li><strong>Address:</strong> " + rs.getString("address") + "</li>");
                out.println("<li><strong>City:</strong> " + rs.getString("city") + "</li>");
                out.println("<li><strong>State:</strong> " + rs.getString("state") + "</li>");
                out.println("<li><strong>Country:</strong> " + rs.getString("country") + "</li>");
                out.println("<li><strong>Mobile No:</strong> " + rs.getString("mobile_no") + "</li>");
                out.println("<li><strong>Email:</strong> " + rs.getString("email") + "</li>");
                out.println("</ul>");
            } else {
                out.println("<h3>No student found with ID " + studentId + "</h3>");
            }
            
          
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>There was an error retrieving the student details.</h3>");
        } finally {
            out.close();
        }
    }
}

