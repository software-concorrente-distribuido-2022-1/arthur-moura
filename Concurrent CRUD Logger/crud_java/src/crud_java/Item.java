package crud_java;

public class Item {
	
	String description;
	String value;
	
	public Item() {
		
	}	
	
	public Item(String d, String v) {
		description = d;
		value = v;
	}
	
	public synchronized void setDescription(String d) {
		description = d;		
	}
	
	public synchronized void setValue(String v) {
		value = v;		
	}
	
	public synchronized String getDescription() {
		return description;
	}
	
	public synchronized String getValue() {
		return value;
	}

}
