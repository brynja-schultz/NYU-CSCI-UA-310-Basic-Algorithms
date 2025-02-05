import java.util.Random;

public class BdayParadox {
	
	public static void main(String[] argv) {
		int ss = argv.length>0? Integer.valueOf(argv[0]) : 111; // seed
		int nn = argv.length>1? Integer.valueOf(argv[1]) : 23; //number of birthdays
		int mm = argv.length>2? Integer.valueOf(argv[2]) : 0; // mode that determines if multiple trials are run
		Random rgen = (ss==0)? new Random(): new Random(ss);
		
		boolean found = false; // FALSE if no duplicate is found, TRUE if duplicate is found 

		int[] bdayArray = new int[nn]; // array of bdays in number format
		String[] bdayMonths = new String[nn]; // array of bdays in month/date format
		
		int failedTrials = 0; 

		if(mm==1){  // run trials until success 
			while(!found){
				bdayMonths = runTrial(argv, rgen, nn); // array in month/date format
				found = testDuplicates(bdayMonths); //determines if trial was a success 
				if(!found){
					failedTrials ++;
				}
			} // while loop
			
			System.out.println("\n" + "===============");

			System.out.println("The total number of failed trials is " + failedTrials);

		} // if statement

		else{ // runs when mode is 0, prints out bdays and whether there are duplicates 
			bdayMonths = runTrial(argv, rgen, nn);
			printBdays(bdayMonths);
		} // else statement 
			
	} // main
	

	// returns bdayArray
	static String[] runTrial(String[] argv, Random rgen, int nn){

		int[] bdayArray = new int[nn]; // array of bdays in number format
		String[] bdayMonths = new String[nn]; // array of bdays in month-date format
		


		// generating random birthdays in number format
		for (int i = 0; i < nn; i++) {
			bdayArray[i] = rgen.nextInt(365) + 1;
		} // for loop
		
		// CONVERTING DAY NUMBER TO MONTH DAY FORMAT
		for (int i = 0; i < nn; i++) {
			bdayMonths[i] = getMonth(bdayArray[i]);
		} // for loop

		return bdayMonths; // returns array of bdays in month-date format to check if trial was success or print bdays 

	} //runTrial method
		
		
	static boolean testDuplicates(String[] bdays){
		int key = 0;
		//for loops to search bdays for duplicates 
		for(int i = 0; i < (bdays.length); i++) {
			
			for(int j = i + 1; j < bdays.length; j++){
				if(bdays[i].equals(bdays[j])) { //returns true when duplicate is found
					return true;
				} // if statement
			} // inside for loop
		} // outside for loop
		
		return false; // if no duplicate is found, returns false

	} // testDuplicates		

	
	//used when mode is 0 
	static void printBdays(String[] bdays){
		int key = 0; // records position in the array of birthdays we are currently at
		boolean found = false; // true if duplicate is found, false if not found 
		
		//prints bdays in correct format (lines of 10)
		System.out.println("\n" + "===============The  " + bdays.length + " birthdays are:"); // add the number used in between strings
		
		while(key < bdays.length) {
			for (int i = 0; i < 10; i++) {
				
				if((key + i) >= bdays.length) { //stops for loop if we already reached the end of the bdays
					break;
				} // if statement
				
				System.out.print("" + bdays[key+i] + ", "); //prints bdays in groups of 10
			} // for loop
			
			key += 10;
			System.out.println(); //begins new line of 10 dates

		} // while loop
		
		//finds & prints if duplicate is found 
		for(int i = 0; i < (bdays.length); i++) {
			
			for(int j = i + 1; j < bdays.length; j++){
				if(bdays[i].equals(bdays[j])) {
					System.out.print("The " + (i+1) + "-th bday and " + (j+1) + "-th bday are equal: " + bdays[i] + "!");
					found = true; 
					break;
				} // if statement
			} // inside for loop
		} // outside for loop
		
		if(!found) { //no duplicate was found so that is printed instead
			System.out.print("No duplicates found in " + bdays.length + " birthdays!");
		} // if statement
		
	} // printBdays
	
	static String getMonth(int birthdate){ //converts bday from number format to month-date
		String bday = "";
		//JANUARY
		if(birthdate <= 31) {
			bday = "Jan" + birthdate;	
			return bday;
		}//jan

		//FEBRUARY
		else if(birthdate <= 59) {
			int day = birthdate - 31; 
			bday = "Feb" + day;
			return bday;
		}//feb

		//MARCH	
		else if(birthdate <= 90) {
			int day = birthdate - 59; 
			bday = "Mar" + day;
			return bday;
		}//mar

		//APRIL	
		else if(birthdate <= 120) {
			int day = birthdate - 90; 
			bday = "Apr" + day;
			return bday;
		}//apr

		//MAY	
		else if(birthdate <= 151) {
			int day = birthdate - 120; 
			bday = "May" + day;
			return bday;
		}//may

		//JUNE	
		else if(birthdate <= 181) {
			int day = birthdate - 151; 
			bday = "Jun" + day;
			return bday;
		}//jun

		//JULY	
		else if(birthdate <= 212) {
			int day = birthdate - 181; 
			bday = "Jul" + day;
			return bday;
		}//jul

		//AUGUST	
		else if(birthdate <= 243) {
			int day = birthdate - 212; 
			bday = "Aug" + day;
			return bday;
		}//aug

		//SEPTEMBER	
		else if(birthdate <= 273) {
			int day = birthdate - 243; 
			bday = "Sep" + day;
			return bday;
		}//sep

		//OCTOBBER	
		else if(birthdate <= 304) {
			int day = birthdate - 273; 
			bday = "Oct" + day;
			return bday;
		}//oct
		
		//NOVEMBER	
		else if(birthdate <= 334) {
			int day = birthdate - 304; 
			bday = "Nov" + day;
			return bday;
		}//nov

		//DECEMBER	
		else {
			int day = birthdate - 334; 
			bday = "Dec" + day;
			return bday;
	    	}//dec
	} // getMonth


} // class
