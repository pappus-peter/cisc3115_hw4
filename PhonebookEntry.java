package assignment;

import java.io.*;
import java.util.Scanner;

public class PhonebookEntry {
	private Name name; 
	private PhoneNumber phoneNumber; 

	public PhonebookEntry() {
		this.name = new Name("");
		this.phoneNumber = new PhoneNumber(""); 
	}
	public PhonebookEntry(Name name, PhoneNumber pn) {
		this.name = name; 
		this.phoneNumber = pn; 
	}
	public Name getName() {
		return this.name; 
	}
	public PhoneNumber getPhoneNumber() {
		return this.phoneNumber; 
	}
	
	public void call() {
		System.out.print("Dialing ");
		if(this.phoneNumber.isTollFree()) {
			System.out.print("(toll-free) ");
		}
		System.out.println(this.name + ": " + this.phoneNumber);
	}
	public String toString() {
		return this.name + ": " + this.phoneNumber;
	}
	public boolean equals(PhonebookEntry pbeO) {
		return this.name.equals(pbeO.getName()) && this.phoneNumber.equals(pbeO.getPhoneNumber());
	}
	public static PhonebookEntry read(Scanner scanner) {
		if(!scanner.hasNext()) {
			return null;
		}
		Name name = new Name(scanner.next(), scanner.next()); 
		PhoneNumber phoneNumber = new PhoneNumber(scanner.next()); 
		return new PhonebookEntry(name, phoneNumber); 
	}
	
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("phonebook.text")); 
		int count = 0; 
		PhonebookEntry pbeTemp = new PhonebookEntry(); 
		
		PhonebookEntry pbe = read(sc); 
		while( pbe!= null) {
			if(pbe.equals(pbeTemp)) {
				System.out.println("Duplicate phone book entry \"" + pbe + "\" discovered"); 
			}else {
				if(pbe.getName().equals(pbeTemp.getName())) {
					System.out.println("Warning duplicate name encountered \"" + pbe +  "\" discovered");
				}
				System.out.println("phone book entry: " + pbe);  
				pbe.call();
				System.out.println(); 
			}
			count++; 
			pbeTemp = pbe; 
			pbe = read(sc); 
		}
		System.out.println("---"); 
		System.out.println(count + " phonebook entries processed."); 

	}

}
