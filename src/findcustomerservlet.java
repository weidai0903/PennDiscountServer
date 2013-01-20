import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class findcustomerservlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String username=request.getParameter("username");
		PrintWriter out=response.getWriter();
		DatabaseObject db=new DatabaseObject();
		db.connect();
		if(db.checkusername(username)){
			out.write("0");
		}else{
			out.write("1");
		}
		out.flush();
		out.close();
		db.disconnect();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		doPost(request,response);
	}
}
