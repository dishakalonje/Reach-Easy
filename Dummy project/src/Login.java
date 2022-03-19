

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/Company","root","root");
			String qr="select * from user where email=? and pass=?";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				
				HttpSession session=request.getSession();
				session.setAttribute("email", email);
				
				
				response.sendRedirect("home.html");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("index.html");
				rd.include(request, response);
				out.println("<script>window.alert('Invalid email or password');</script>");
			}
			con.close();
		} 
		catch (Exception e) 
		{
			out.println(e);
		}
	}
}
