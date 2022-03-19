

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Add")
public class Add extends HttpServlet {

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
		String list=request.getParameter("list");

		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Company","root","root");
			String qr = "insert into details values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(qr);
			ps.setString(1, name);
			ps.setString(2, cno);
			ps.setString(3, address);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, country);
			ps.setString(7, website);
			ps.setString(8, list);
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.println("Company Added");
			}
			else
			{
				out.println("Not Added");
			}
			con.close();
		} catch (Exception e) {
			out.println(e);
		}
	}

}
