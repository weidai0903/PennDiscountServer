import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class companycouponservlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String username=request.getParameter("username");
		PrintWriter out=response.getWriter();
		DatabaseObject db=new DatabaseObject();
		db.connect();
		LinkedList<companyCoupon> temp=db.getCompanyCoupon(username);
		StringBuffer res=new StringBuffer();
		while(!temp.isEmpty()){
			companyCoupon t=temp.remove();
			res.append(t.getCouponID()+":"+t.getDescription());
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
