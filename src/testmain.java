import java.util.LinkedList;


public class testmain {
	public static void main(String arg[]){
		DatabaseObject db=new DatabaseObject();
		db.connect();
		String username="sankee noodle house";
		System.out.println(db.addCoupon("sankee noodle house", "testcoupon2"));
		db.disconnect();
	}
}
