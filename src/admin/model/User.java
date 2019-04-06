package admin.model;

public class User {
	private int id;
	private String code;
	private String name;
	private String birthday;
	private String address;
	private String profile;
	private String email;
	private String password;
	private int role_id;
	
	public User() {
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(String code) {
		this.code=code;
	}
	public User(String code, String name, String birthday, String address, String profile, int role_id) {
		this.code=code;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.profile = profile;
		this.role_id = role_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [code=" + code + ", name=" + name + ", birthday=" + birthday + ", address="
				+ address + ", profile=" + profile + ", role_id=" + role_id + "]";
	}

	
	

}
