
public class coupon {
	private String companyname;
	private String picURL;
	private String couponID;

	public coupon(String cn, String pu, String ci){
		this.companyname=cn;
		this.picURL=pu;
		this.couponID=ci;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	public String getCouponID() {
		return couponID;
	}

	public void setCouponID(String couponID) {
		this.couponID = couponID;
	}
}
