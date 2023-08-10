
import java.io.*;
import java.util.Scanner;

public class Phonebook {
	
	public static void main(String[] args) throws IOException {
		final int LENGTH = 100;
		Scanner sc = new Scanner(System.in);
		Scanner input = new Scanner(new File ("phonebook.text"));
		String[][] pb = new String[LENGTH][2];
		int size = readData(input, pb);
		int lCount = 0;
		int rCount = 0;
		
//		for(int i=0; i<size; i++) {
//			System.out.println(pb[i][0] + " " + pb[i][1]);
//		}
		
		char userCommand = readCommand(sc);
		while(sc.hasNext() && userCommand !='q') {
			if(userCommand == 'l') {
				readName(sc, pb, size);
				lCount++;
				
			}else if(userCommand =='r') {
				readNum(sc, pb, size);
				rCount++;
			}
			userCommand = readCommand(sc);
		}
		System.out.println("\n" + lCount + " lookups performed");
		System.out.println(rCount + " reverse lookups performed");
		
		
		sc.close();
		input.close();
	}
	public static int readData(Scanner input, String[][] pb) {
		int size = 0;  
		while(input.hasNext()) {
			pb[size][0] = input.next();
			pb[size][0] = input.next() + " " + pb[size][0];
			pb[size][1] = input.next();
			size++;
		}
		return size;
	}
	public static char readCommand(Scanner sc) {
		System.out.print("lookup, reverse-lookup, quit (l/r/q)? ");
		char userCommand = sc.next().charAt(0);
		return userCommand;
	}
	public static void readName(Scanner sc, String[][] pb, int size) {
		System.out.print("last name? ");
		String name = sc.next();
		System.out.print("first name? ");
		name = sc.next() + " " + name;
		
		for(int i=0; i<size; i++) {
			if(pb[i][0].equals(name)) {
				System.out.print(name + "'s phone number is " + pb[i][1] + "\n\n");
				return;
			}
		}
		System.out.print("-- Name not found\n\n");
	}
	public static void readNum(Scanner sc, String[][] pb, int size) {
		System.out.print("phone number (nnn-nnn-nnnn)? ");
		String num = sc.next();
		
		for(int i=0; i<size; i++) {
			if(pb[i][1].equals(num)) {
				// Originally, I did not separate the first and last name in my program
				// eventually I was too lazy to rewrite the whole prgram 
				// index of white space
				int indexW = pb[i][0].indexOf(" ");
				String name = pb[i][0].substring(indexW+1,pb[i][0].length()) + ", " + pb[i][0].substring(0,indexW);
				System.out.print(num + " belongs to " + name+ "\n\n");
				return; 
			}
		}
		System.out.print("-- Phone number not found\n\n");
		
	}

}
