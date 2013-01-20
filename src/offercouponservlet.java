import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class offercouponservlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String username=request.getParameter("username");
		String couponID=request.getParameter("couponID");
		String expire=request.getParameter("expire");
		PrintWriter out=response.getWriter();
		DatabaseObject db=new DatabaseObject();
		db.connect();
		out.write(db.offerCoupon(username, couponID, expire));
		out.flush();
		out.close();
		db.disconnect();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		doPost(request,response);
	}
}
