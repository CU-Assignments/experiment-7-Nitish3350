//login.html
<!DOCTYPE html>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        Username: <input type="text" name="username" required><br><br>
        Password: <input type="password" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>

//LoginServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Accepting form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Hardcoded credentials for demo
        String validUser = "admin";
        String validPass = "password123";

        // Login validation
        if (username.equals(validUser) && password.equals(validPass)) {
            out.println("<html><body>");
            out.println("<h1>Welcome, " + username + "!</h1>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h2>Login Failed! Invalid username or password.</h2>");
            out.println("<a href='login.html'>Try Again</a>");
            out.println("</body></html>");
        }
    }
}

//Servlet Configuration
<web-app>
    <servlet>
        <servlet-name>LoginServlet</servlet-name>
        <servlet-class>LoginServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>LoginServlet</servlet-name>
        <url-pattern>/LoginServlet</url-pattern>
    </servlet-mapping>
</web-app>

