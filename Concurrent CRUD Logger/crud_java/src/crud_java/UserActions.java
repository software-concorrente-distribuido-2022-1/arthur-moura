package crud_java;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UserActions extends Thread {
	
	User currentUser;
	Item item;
	boolean sinal = true;
	
	
	public UserActions(User u, Item i) {
		currentUser = u;
		item = i;	
	}
	
	public void createLog(String string) {
		String sqlLog = "INSERT INTO log(description, time) VALUES (?, ?)";
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime nowDateTime = LocalDateTime.now();
		
		Connection conLog = null;		
		conLog = ConnectionFactory.createConnectionSQL();
		
		PreparedStatement pstmLog = null;		
		try {
			pstmLog = conLog.prepareStatement(sqlLog);
			pstmLog.setString(1, string);
			pstmLog.setString(2, dateTimeFormatter.format(nowDateTime));
			
			pstmLog.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean login() {
		
		String sql = "SELECT * FROM user";
		
		List<User> users = new ArrayList<User>();
		
		Connection con = null;
		
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			con = ConnectionFactory.createConnectionSQL();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();				
			
			while(rset.next()) {
				User user = new User();
				user.SetName(rset.getString("name"));
				user.SetUsername(rset.getString("username"));
				user.SetPassword(rset.getString("password"));
				
				users.add(user); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(User user: users) {
			if(user.getUsername().equals(currentUser.username) && user.getPassword().equals(currentUser.password)){
				createLog(currentUser.username + " logou.");
				return true;				
			}
		}
		
		createLog("Não foi possível realizar o login");
		return false;		
	}
	
	public void insertItem() {
		String sql = "INSERT INTO item(description, value) VALUES (?, ?)";		
		Connection con = null;		
		con = ConnectionFactory.createConnectionSQL();
		
		PreparedStatement pstm = null;		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.description);
			pstm.setString(2, item.value);
			
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createLog(currentUser.username + " inseriu o item " + item.description + ".");
	}
	
	public synchronized void editItem() {
		
		while(!sinal) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		sinal = false;
		String sql = "UPDATE item SET description = ?, value = ?" + "WHERE description = ?";
		
		Connection con = null;		
		con = ConnectionFactory.createConnectionSQL();
		
		PreparedStatement pstm = null;	
		String newTitle = "Título editado";
		
		try {			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, newTitle);
			pstm.setString(2, "Valor editado");
			pstm.setString(3, item.description);
			
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createLog(currentUser.username + " editou o item para " + newTitle +".");
		
		sinal = true;
		notifyAll();		
	}
	
	public synchronized void deleteItem() {		
		
		while(!sinal) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		sinal = false;		
		String sql = "DELETE FROM item WHERE description = ?";
		
		Connection con = null;
		con = ConnectionFactory.createConnectionSQL();
		
		PreparedStatement pstm = null;
		String title = "Título editado";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		createLog(currentUser.username + " deletou o item " + title +".");
		
		sinal = true;
		notifyAll();		
	}
	
	public List<Item> viewItems(){
		String sql = "SELECT * FROM item";
		
		List<Item> items = new ArrayList<Item>();
		
		Connection con = null;
		
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			con = ConnectionFactory.createConnectionSQL();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();				
			
			while(rset.next()) {
				Item item = new Item();
				
				item.setDescription(rset.getString("description"));
				item.setValue(rset.getString("value"));
				
				items.add(item);
				System.out.println(item.description);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;	
	}
	
	public void run() {
		if(login()) {
			insertItem();
			editItem();
			deleteItem();
			viewItems();
		}
	}
}
