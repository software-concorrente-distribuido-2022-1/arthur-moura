package crud_java;

public class User {
	
	String name;
	String username;
	String password;
	
	public User() {
		
	}
	
	public User(String n, String u, String p) {
		name = n;
		username = u;
		password = p;		
	}
	
	public String getName() {
		return name;
		
	}
	
	public void SetName(String n) {
		name = n;		
	}
	
	public String getUsername() {
		return username;
		
	}
	
	public void SetUsername(String u) {
		username = u;		
	}
	
	public String getPassword() {
		return password;
		
	}
	
	public void SetPassword(String p) {
		password = p;		
	}
	
}
