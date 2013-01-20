import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class detailservlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String couponID=request.getParameter("couponID");
		PrintWriter out=response.getWriter();
		DatabaseObject db=new DatabaseObject();
		db.connect();
		LinkedList<couponDetail> temp=db.getDetail(couponID);
		StringBuffer res=new StringBuffer();
		while(!temp.isEmpty()){
			couponDetail t=temp.remove();
			res.append(t.getAddress()+":"+t.getPhoneNo()+":"+t.getExpiredate()+":"+t.getDescription());
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
