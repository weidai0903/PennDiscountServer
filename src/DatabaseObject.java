
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Random;



public class DatabaseObject {
	public static final String serverName="localhost";
	public static final String database="penndiscount";
	public static final String url="jdbc:mysql://" + serverName + "/" + database;
	public static final String user="root";
	public static final String password="admin";
	private String randomString="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefqhijklmnopqrstuvwxyz0123456789";
	private Connection connection = null;
	
	public boolean connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean disconnect(){
		try {
			connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public int login(String username, String password){
		try {
			Statement st=connection.createStatement();
			String login="select isBusiness from user where username='"+username+"' and password='"+password+"'";
			if(st.execute(login)){
				ResultSet rs=st.getResultSet();
				if(rs.next()){
					int isbussiness=rs.getInt(1);
					return isbussiness+1;
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public boolean checkusername(String username){
		try {
			Statement st=connection.createStatement();
			String check="select 1 from user where username='"+username+"'";
			if(st.execute(check)){
				ResultSet rs=st.getResultSet();
				if(rs.next()){
					int res=-1;
					res=rs.getInt(1);
					if(res==1){
						return false;
					}else{
						return true;
					}
				}else{
					return true;
				}
			}else{
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean checkcompanyid(String id){
		try {
			Statement st=connection.createStatement();
			String check="select 1 from company where companyID='"+id+"'";
			if(st.execute(check)){
				ResultSet rs=st.getResultSet();
				if(rs.next()){
					int res=-1;
					res=rs.getInt(1);
					if(res==1){
						return false;
					}else{
						return true;
					}
				}else{
					return true;
				}
			}else{
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	public String getcompanyID(String username){
		try {
			Statement st=connection.createStatement();
			String check="select companyID from company where companyName='"+username+"'";
			if(st.execute(check)){
				ResultSet rs=st.getResultSet();
				if(rs.next()){
					String id=rs.getString(1);
					if(id!=null&&id.length()>0){
						return id;
					}else{
						return null;
					}
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}
	
	public boolean checkcouponid(String id){
		try {
			Statement st=connection.createStatement();
			String check="select 1 from coupon where couponID='"+id+"'";
			if(st.execute(check)){
				ResultSet rs=st.getResultSet();
				if(rs.next()){
					int res=-1;
					res=rs.getInt(1);
					if(res==1){
						return false;
					}else{
						return true;
					}
				}else{
					return true;
				}
			}else{
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean register(String username, String password, String email, String isbussiness){
		try{
			Statement st=connection.createStatement();
			String register="insert into user values('"+username+"','"+password+"','"+email+"',"+isbussiness+")";
			st.execute(register);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	private String generateid( int number){
		StringBuffer temp=new StringBuffer();
		Random rand=new Random(System.currentTimeMillis());
		int n=randomString.length();
		for(int i=0;i<number;i++){
			temp.append(randomString.charAt(rand.nextInt(n)));
		}
		return temp.toString();
	}
	
	public LinkedList<coupon> getcoupon(String username){
		LinkedList<coupon> res=new LinkedList<coupon>();
		if(!checkusername(username)){
			try{
				Statement st=connection.createStatement();
				String sql="select a.companyName, b.couponID from company a, coupon b, customerCoupon c where a.companyID=b.companyID and b.couponID=c.couponID and c.customerID='"+username+"'";
				if(st.execute(sql)){
					ResultSet rs=st.getResultSet();
					while(rs.next()){
						String companyname=rs.getString(1);
						String couponid=rs.getString(2);
						String url=companyname+".png";
						coupon temp=new coupon(companyname,url,couponid);
						res.add(temp);
					}
					return res;
				}
			}catch(Exception e){
				return res;
			}
		}else{
			return res;
		}
		return res;
	}
	
	public LinkedList<couponDetail> getDetail(String couponID){
		LinkedList<couponDetail> res=new LinkedList<couponDetail>();
		try{
			Statement st=connection.createStatement();
			String sql="select a.address, a.phoneNumber, b.description,c.expire from company a, coupon b, customerCoupon c where c.couponID='"+couponID+"' and c.couponID=b.couponID and b.companyID=a.companyID";
			if(st.execute(sql)){
				ResultSet rs=st.getResultSet();
				while(rs.next()){
					String address=rs.getString(1);
					String phonenumber=rs.getString(2);
					String description=rs.getString(3);
					String date=rs.getString(4);
					couponDetail temp=new couponDetail(address,phonenumber,date,description);
					res.add(temp);
				}
				return res;
			}
		}catch(Exception e){
			return res;
		}
		return res;
	}
	
	public LinkedList<companyCoupon> getCompanyCoupon(String username){
		LinkedList<companyCoupon> res=new LinkedList<companyCoupon>();
		try{
			Statement st=connection.createStatement();
			String sql="select a.couponID, a.description from coupon a, company b where a.companyID=b.companyID and b.companyName='"+username+"'";
			if(st.execute(sql)){
				ResultSet rs=st.getResultSet();
				while(rs.next()){
					String couponID=rs.getString(1);
					String desc=rs.getString(2);
					companyCoupon temp=new companyCoupon(couponID,desc);
					res.add(temp);
				}
				return res;
			}
		}catch(Exception e){
			return res;
		}
		return res;
	}
	
	public int addCoupon(String company, String description){
		try{
			String companyid=getcompanyID(company);
			if(companyid!=null){
				String id=generateid(10);
				while(!checkcouponid(id)){
					id=generateid(10);
				}
				Statement st=connection.createStatement();
				String sql="insert into coupon values('"+id+"','"+companyid+"','"+description+"')";
				System.out.println(sql);
				st.execute(sql);
				return 1;
			}else{
				return 0;
			}
		}catch(Exception e){
			return 0;
		}
	}
	
	public int offerCoupon(String username, String couponID,String expire){
		if(checkusername(username)||checkcouponid(couponID)){
			return 0;
		}else{
			try{
				Statement st=connection.createStatement();
				String sql="insert into customerCoupon values('"+couponID+"','"+username+"','"+expire+"')";
				st.execute(sql);
				return 1;
			}catch(Exception e){
				return 0;
			}
		}
	}
	
}
