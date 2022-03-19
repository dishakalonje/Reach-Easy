
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String name = request.getParameter("name");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Company","root","root");
			String qr = "delete from details where name=?";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1, name);
			int i=ps.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("Show");
			if(i>0)
			{
				rd.include(request, response);
				out.println("Company Deleted");
			}
			else
			{
				rd.include(request, response);
				out.println("Not Deleted");
			}
			con.close();
		} catch (Exception e) {
			out.println(e);
		}
		}
	}