

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

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String name = request.getParameter("name");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Company","root","root");
			String qr="select * from details where name=?";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				out.println("<table align='center' border='2px' >");
				do
				{
					name=rs.getString("name");
					int cno=rs.getInt("cno");
					String address=rs.getString("adress");
					String city=rs.getString("city");
					String state=rs.getString("state");
					String country=rs.getString("country");
					String website=rs.getString("website");
					
					out.println("<tr>");
					out.println("<td>");
					out.println(name);
					out.println("</td>");
					out.println("<td>");
					out.println(cno);
					out.println("</td>");
					out.println("<td>");
					out.println(address);
					out.println("</td>");
					out.println("<td>");
					out.println(city);
					out.println("</td>");
					out.println("<td>");
					out.println(state);
					out.println("</td>");
					out.println("<td>");
					out.println(country);
					out.println("</td>");
					out.println("<td>");
					out.println(website);
					out.println("</td>");
					out.println("");
				}
				while(rs.next());
				out.println("</table>");
			}
			else
			{
				out.println("no records found");
			}
			con.close();
		} 
			catch (Exception e) 
			{
				out.println(e);
			}
		}
	}

