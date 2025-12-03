package domain;

public class User {
	private String id;
	private String pwd;
	private String email;
	private String phone;
	private String regdate;
	private String lastlogin;
	
	public User() {}
	
	public User(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public User(String id, String pwd, String email, String phone) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
	}

	public User(String id, String pwd, String email, String phone, String regdate, String lastlogin) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
		this.regdate = regdate;
		this.lastlogin = lastlogin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", email=" + email + ", phone=" + phone + ", regdate=" + regdate
				+ ", lastlogin=" + lastlogin + "]";
	}
	

}
