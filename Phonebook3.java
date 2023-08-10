package assignment;

import java.io.*;
import java.util.*;

public class Phonebook3 {
	
	public static void main(String[] args) throws Exception {
		try {
		
			Scanner input = new Scanner(new File("phonebook.text"));
			Scanner sc = new Scanner(System.in);
			int lCount = 0; 
			int rCount = 0; 	
			
			ArrayList<PhonebookEntry> pb = new ArrayList<PhonebookEntry>(); 
			while(input.hasNext()) {
				
				pb.add(PhonebookEntry.read(input)); 
				if(pb.size()>=100) {
					//System.out.println("****tteest");
					throw new Exception();
				}
			}
			char userCommand = readCommand(sc);
			while(sc.hasNext() && userCommand !='q') {
				if(userCommand == 'l') {
					lookup(sc, pb);
					lCount++; 
					
				}else if(userCommand =='r') {
					lookupR(sc, pb);
					rCount++;
				}
				userCommand = readCommand(sc);
			}
			System.out.println("\n" + lCount + " lookups performed");
			System.out.println(rCount + " reverse lookups performed");
			sc.close();
			input.close();
			
		
		} catch(FileNotFoundException s) {
			//System.out.println("*** IOException *** phonebook.text ());
			System.out.println(s.getMessage()); 
		} catch(Exception e) {
			System.out.println("*** Exception *** Phonebook capacity exceeded - increase size of underlying array"); 
			//"*** Exception *** Phonebook capacity exceeded - increase size of underlying array "
		}
	}
	
		
	
	public static char readCommand(Scanner sc) {
		System.out.print("lookup, reverse-lookup, quit (l/r/q)? ");
		char userCommand = sc.next().charAt(0);
		return userCommand;
	}
	
	public static void lookup(Scanner sc, ArrayList<PhonebookEntry> pb) {
		System.out.print("last name? ");
		String last = sc.next();
		System.out.print("first name? ");
		String first = sc.next();
		Name tar = new Name(last, first);
		
		for(int i=0; i<pb.size(); i++) {
			if(pb.get(i).getName().equals(tar)) {
				System.out.print(tar.toString() + "'s phone number is " + pb.get(i).getPhoneNumber().toString() + "\n\n");
				return;
			}
		}
		System.out.print("-- Name not found\n\n");
	}
	
	public static void lookupR(Scanner sc, ArrayList<PhonebookEntry> pb) {
		System.out.print("phone number (nnn-nnn-nnnn)? ");
		String num = sc.next();
		PhoneNumber tar = new PhoneNumber(num); 
		
		for(int i=0; i<pb.size(); i++) {
			if(pb.get(i).getPhoneNumber().equals(tar)) {
				System.out.print(num + " belongs to " + pb.get(i).getName().toString()+ "\n\n");
				return;
			}
		}
		System.out.print("-- Phone number not found\n\n");
		
	}
	
	
	////// PhonebookEntry //////
	static class PhonebookEntry {
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
		
		public String toString() {
			return this.name.toString() + ": " + this.phoneNumber.toString();
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
	}
	////// Name //////
	static class Name {
		private String last; 
		private String first; 
		
		public Name(String last, String first) {
			this.last = last; 
			this.first = first; 
		}
		public Name(String first) {
			this("",first); 
		}
		
		public String getFormal() {
			return this.first + " " + this.last;
		}
		public String getOfficial() {
			return this.last + ", " + this.first;
		}
		public String getInitals() {
			return this.first.substring(0,1) + "." + this.last.substring(0,1) + "."; 
		}
		public boolean equals(Name other) {
			return this.first.equals(other.first ) && this.last.equals(other.last);
		}
		
		public String toString() {
			return first + " " + last;
		}
		public static Name read(Scanner scanner) {
			if(!scanner.hasNext()) {
				return null;
			}
			String last = scanner.next(); 
			String first = scanner.next();
			return new Name(last, first); 
		}
	}
	////// PhoneNumber //////
	static class PhoneNumber {
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
	}

}
