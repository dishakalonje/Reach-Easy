import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Show")
public class Show extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/Company","root","root");
			String qr="select * from details";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			
			if(rs.next())
			{
				out.println("<table align='center' border='2px' >");
				do
				{
					String name=rs.getString("name");
					int cno=rs.getInt("cno");
					String address=rs.getString("address");
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
					
					out.println("</tr>");
					
				}
				while(rs.next());
				out.println("</table>");
			}
			else
			{
				out.println("<script>window.alert('No Data Found')</script>");
			}
			con.close();
		} 
		catch (Exception e) 
		{
			out.println(e);
		}
	}
}
