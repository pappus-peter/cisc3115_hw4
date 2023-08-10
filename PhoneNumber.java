package assignment;

import java.io.* ;
import java.util.*;

public class PhoneNumber {
	private String number;
	
	public PhoneNumber(String num) {
		this.number = num;
	}
	
	public String getAreaCode() {
		return this.number.substring(1,4);
	}
	public String getExchange() {
		return this.number.substring(5,8);
	}
	public String getLineNumber() {
		return this.number.substring(9,13); 
	}
	public boolean isTollFree() {
		return this.number.charAt(1)=='8';
	}
	
	public boolean equals(PhoneNumber other) {
		return this.number.equals(other.toString());
	}
	public String toString() {
		return number;
	}
	public static PhoneNumber read(Scanner scanner) {
		if(!scanner.hasNext()) {
			return null;
		}
		String number = scanner.next();
		return new PhoneNumber(number); 
	}


	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("numbers.text")); 
		int count = 0; 
		PhoneNumber pnTemp = new PhoneNumber("(000)000-0000"); 
		
		PhoneNumber pn = read(sc); 
		while(pn != null) {
			if(pn.equals(pnTemp)) {
				System.out.println("Duplicate phone number \"" + pn + "\" discovered"); 
			}else {
				System.out.println("phone number: " + pn);  
				System.out.println("area code: " + pn.getAreaCode()); 
				System.out.println("exchange: " + pn.getExchange());
				System.out.println("line number: " + pn.getLineNumber());
				System.out.println("is toll free: " + pn.isTollFree());
				System.out.println(); 
			}
			count++; 
			pnTemp = pn; 
			pn = read(sc); 
		}
		System.out.println("---"); 
		System.out.println(count + " phone numbers processed."); 
		
	}

}

