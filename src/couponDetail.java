
public class couponDetail {
	private String address;
	private String phoneNo;
	private String expiredate;
	private String description;
	
	public couponDetail(String a, String p, String e, String d){
		this.address=a;
		this.phoneNo=p;
		this.expiredate=e;
		this.description=d;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}
	
}
