package assignment;

import java.io.*;
import java.util.*;

public class Phonebook {

	
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(new File("phonebook.text"));
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> lastName = new ArrayList<String>();
		ArrayList<String> firstName = new ArrayList<String>();
		ArrayList<String> phone = new ArrayList<String>();
		ArrayList<ArrayList<String>> pb = new ArrayList<ArrayList<String>>();
		pb.add(lastName);
		pb.add(firstName);
		pb.add(phone);
		int lCount = 0; 
		int rCount = 0; 
		
		readData(input, pb);
		char userCommand = readCommand(sc);
		while(sc.hasNext() && userCommand !='q') {
			if(userCommand == 'l') {
				readName(sc, pb);
				lCount++; 
				
			}else if(userCommand =='r') {
				readNum(sc, pb);
				rCount++;
			}
			userCommand = readCommand(sc);
		}
		
		System.out.println("\n" + lCount + " lookups performed");
		System.out.println(rCount + " reverse lookups performed");
		sc.close();
		input.close();
		
	}
	
	public static void readData(Scanner input, ArrayList<ArrayList<String>> pb) {
		while(input.hasNext()) {
			pb.get(0).add(input.next());
			pb.get(1).add(input.next());
			pb.get(2).add(input.next());
		}
	}
	public static char readCommand(Scanner sc) {
		System.out.print("lookup, reverse-lookup, quit (l/r/q)? ");
		char userCommand = sc.next().charAt(0);
		return userCommand;
	}
	public static void readName(Scanner sc, ArrayList<ArrayList<String>> pb) {
		System.out.print("last name? ");
		String last = sc.next();
		System.out.print("first name? ");
		String first = sc.next();
		Boolean hasName = pb.get(0).indexOf(last) == pb.get(1).indexOf(first) && pb.get(0).indexOf(last)>-1; 
		
		if(hasName) {
			int pos = pb.get(0).indexOf(last);
			String name = pb.get(1).get(pos) + " " + pb.get(0).get(pos);
			System.out.print(name + "'s phone number is " + pb.get(2).get(pos) + "\n\n");
			return;
		}
		System.out.print("-- Name not found\n\n");
	}
	public static void readNum(Scanner sc, ArrayList<ArrayList<String>> pb) {
		System.out.print("phone number (nnn-nnn-nnnn)? ");
		String num = sc.next();
		Boolean hasNum = pb.get(2).indexOf(num) > -1;
		
		if(hasNum) {
			int pos = pb.get(2).indexOf(num);
			String name = pb.get(0).get(pos) + ", " + pb.get(1).get(pos);
			System.out.print(num + " belongs to " + name+ "\n\n");
			return; 
		}
		System.out.print("-- Phone number not found\n\n");
		
	}

}
