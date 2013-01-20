import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class usercouponservlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String username=request.getParameter("username");
		PrintWriter out=response.getWriter();
		DatabaseObject db=new DatabaseObject();
		db.connect();
		LinkedList<coupon> temp=db.getcoupon(username);
		StringBuffer res=new StringBuffer();
		while(!temp.isEmpty()){
			coupon t=temp.remove();
			res.append(t.getCompanyname()+":"+t.getPicURL()+":"+t.getCouponID());
			if(!temp.isEmpty()){
				res.append(";");
			}
		}
		out.write(res.toString());
		out.flush();
		out.close();
		db.disconnect();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		doPost(request,response);
	}
}
