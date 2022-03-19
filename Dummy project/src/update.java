import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class update extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");

		String name=request.getParameter("name");
		String cno=request.getParameter("cno");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String website=request.getParameter("website");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/Company","root","root");
			String qr="update details set cno=?,address=?,city=?,state=?,country=?,website=? where name=?";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(7, name);
			ps.setString(1, cno);
			ps.setString(2, address);
			ps.setString(3, city);
			ps.setString(4, state);
			ps.setString(5, country);
			ps.setString(6, website);
			int i=ps.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("Show");
			rd.include(request, response);
			if(i>0)
			{
				out.println("updated");
			}
			else
			{
				out.println("not updated");
			}
			con.close();
		} catch (Exception e) {
			out.println(e);
		}
	}
}
