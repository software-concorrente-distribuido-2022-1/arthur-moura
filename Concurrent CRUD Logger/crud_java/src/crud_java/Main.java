package crud_java;

import java.sql.SQLException;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		
		boolean autenticador = false;
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connectionFactory.createConnectionSQL();
		
		User user = new User("arthur", "mantis", "123");
		User user2 = new User("pietro", "pokz", "12345");
		User user3 = new User("laercio", "stone", "m4nt1s");
		
		Item item = new Item("Título", "Conteúdo");	
		
		UserActions userActions = new UserActions(user, item);
		UserActions userActions2 = new UserActions(user2, item);
		UserActions userActions3 = new UserActions(user3, item);
		
		userActions.start();
		userActions2.start();
		userActions3.start();
	}
}
