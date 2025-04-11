// MySQL Table

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    roll_number VARCHAR(20),
    subject VARCHAR(100),
    attendance_date DATE,
    status VARCHAR(10)
);
//JSP Form (attendance.jsp)


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Attendance</title>
</head>
<body>
    <h2>Enter Attendance</h2>
    <form action="AttendanceServlet" method="post">
        Name: <input type="text" name="name" required><br><br>
        Roll Number: <input type="text" name="roll" required><br><br>
        Subject: <input type="text" name="subject" required><br><br>
        Date: <input type="date" name="date" required><br><br>
        Status:
        <select name="status" required>
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br><br>
        <input type="submit" value="Submit Attendance">
    </form>
</body>
</html>
//Servlet to Handle Form (AttendanceServlet.java)

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String roll = request.getParameter("roll");
        String subject = request.getParameter("subject");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database", "root", "your_password");

            String query = "INSERT INTO attendance (student_name, roll_number, subject, attendance_date, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, roll);
            ps.setString(3, subject);
            ps.setDate(4, java.sql.Date.valueOf(date));
            ps.setString(5, status);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3>Attendance recorded successfully!</h3>");
            } else {
                out.println("<h3>Error recording attendance.</h3>");
            }

            conn.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
// web.xml Configuration

<web-app>
    <servlet>
        <servlet-name>AttendanceServlet</servlet-name>
        <servlet-class>AttendanceServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>AttendanceServlet</servlet-name>
        <url-pattern>/AttendanceServlet</url-pattern>
    </servlet-mapping>
</web-app>
