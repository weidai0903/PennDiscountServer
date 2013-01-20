
public class companyCoupon {
	private String couponID;
	private String description;
	public companyCoupon(String c, String d){
		this.couponID=c;
		this.description =d;
	}
	public String getCouponID() {
		return couponID;
	}
	public void setCouponID(String couponID) {
		this.couponID = couponID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
