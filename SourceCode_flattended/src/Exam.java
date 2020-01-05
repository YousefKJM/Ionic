import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Exam {
	
	 
	static int seconds;
	static Timer timer;
	static int correct;

	private static Scanner kb;
		
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Welcome to the Online Test System... ");
		 kb = new Scanner(System.in);
		 Student[] student = getStudents();
		boolean k = false;
		do {	//this loop for the main menu (OUTER LOOP)	
		System.out.println("Please choose an action:\n 1-Login \n 2-Exit "); // the Main Menu.
		int log = kb.nextInt();
		if(log == 1) { // for the Main Menu
			
			boolean h = true;
	do {    // this loop for looping the Login + the Choices    
		int a = -1;
		String name;
		String pass;
		boolean login = false;
		do {     // this loop for checking the LOGIN
			System.out.println("Please Enter your Name and Password: ");
			System.out.print("Name: ");
			name = kb.next();
			System.out.print("Password: ");
			pass = kb.next();
			
			for(int i=0 ; i < getStudents().length ; i++){
				if(getStudents()[i].getName().equals(name) && getStudents()[i].getPass().equals(pass) )
				a=i;	
			}
			if(a == -1){
				login = false;
				}
			else {
			login = true;
			}
			if(login) {
				System.out.println("welcome " + name + "!");
			} else {
				System.out.println("Wrong attempt, try again: ");
			}
		
			
		}while(!login); // end of loop of checking LOGIN
		
		int choice;
		
		do {   // this loop for looping THE choices in INNER MENU 
			
		System.out.println("Please enter the number of the choice you want from the following menu:");
		System.out.println(" 1-Take a test.\n 2-View Leaderboard.\n 3-View my profile.\n 4-Logout.");
		 choice = kb.nextInt();
		 int attempt = 0;
			correct = 0;
		if(choice == 1) {
			
			takeQuestion();
			attempt++;
			student[a].updScore(correct);
			student[a].updAttempt(attempt);
			updateStudent(student);
			
		}
		
		else if (choice == 2) {
			displayAllStudents();
			}
		
		else if (choice == 3) {
			System.out.println();	
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Your ID is: " + student[a].getID());
			System.out.println("Your name is " + student[a].getName() ); 
			System.out.println("Your  highest score is: " + student[a].getScore());
			System.out.println("You have taken this test " + student[a].getAttempt() + " times.");
			System.out.println("----------------------------------------------------------------------");
			System.out.println();
		}
		
		else if (choice == 4) {
			System.out.println();
			h = false;
		}
				} while(choice != 4); // end of loop of the Choices
	 
			}while (h);	// end of loop for the Login + the Choices
		}
		else if (log == 2) {
			System.out.println("Thank you for using this system... ");
			k = true;
			System.exit(0);
			}
		
	} while(!k); // end of loop for the main menu (OUTER LOOP)
		kb.close();
	
	}
	
	
	
	
	//This method stores arrays of Questions and Answers from TestBank file then print it Randomly. 
	public static void takeQuestion() throws FileNotFoundException {
		seconds = 600; // 10 Minutes
		 
		
		 	int number;  
		 	int delay = 1000;
		    int period = 1000;
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Your test has started now, you have 10 minutes...\nGood Luck… ");
			
			timer = new Timer();
		    //this is the actual timer method with three parameters (new TimerTask() { }, period, delay)
			timer.scheduleAtFixedRate(new TimerTask() {
				
				   public void run() {
					   setInterval(); //calls the timer method
				   }
				}, period,delay);
			
			String [] questions = new String[96];
			String [] answers = new String[96];
			Scanner s1 = new Scanner(new File("TestBank.txt"));
			String storeQues = "";
			for (int i =0;i< questions.length;i++){
				while (s1.hasNextLine()) {
					String nextLine = s1.nextLine();
					if(nextLine.length() > 1 && storeQues == ""){
						storeQues += nextLine + "\t" +"\n"; 
					}
					else if (nextLine.length() > 1 )
						storeQues += nextLine + "\n";
					else if (nextLine.length() == 1){
						answers[i] = nextLine;
						break;
					}
				}
				questions[i] = storeQues;
				storeQues="";
			}
			 number = 1;
			 correct = 0;
				kb = new Scanner(System.in);
			while (number<11 && seconds > 1)  //start of counter for loop
			{
				
				Random r = new Random();  //creates new random object
				int choose = r.nextInt(96);  //randomly chooses a question
				System.out.println("\t\t\t\t\t\t\t\t\t\t " + " ("+seconds/60+" mins remaining...) ");
				System.out.println(number +") " + questions[choose]); 	//prints questions and choices  
				String entered = kb.next(); //read input
				if (entered.equalsIgnoreCase(answers[choose]))  //checks the users input
				{
					correct = correct + 1;  //counts number of correct answers
				}
				number++;
				if(number==11){
					timer.cancel();
					seconds =600; //10 Minutes
				}
			}
			
		
			if (seconds < 1) {
				System.out.println("Time's up !!! ");
			}
			s1.close();

				System.out.println("-----------------------------------------------------");  //prints footer top border
				System.out.println("Your final result is " + correct + " / 10" );    			//prints results
				System.out.println("-----------------------------------------------------");  //prints footer bottom border
		}
	
	
	
	//This method takes-in a method of an array of students -getStudents()-, takes the TOP10s, calculate the average, and  display it. 
	public static void displayAllStudents(){
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("ID\tName\t\tHighest Score\t#of Attempts");
		double sumscore = 0;
		double attemptsum = 0;
		double scoreavg = 0;
		double attemptavg = 0;
			for(int i=0 ; i < 10 ; i++){
				 sumscore += getStudents()[i].getScore();
				 attemptsum += getStudents()[i].getAttempt();
					scoreavg =sumscore/10;
					attemptavg = attemptsum/10;
				System.out.println(getStudents()[i].toString());
				}
		System.out.println("Average\t\t\t"+scoreavg+"\t\t" +attemptavg); 
		System.out.println("----------------------------------------------------------------------");
	}
	
	
	//This method takes-in an array of students from a StudentsData file for the leaderBoard + Profile and calls the method -sortStudent(). 
	public static Student[] getStudents(){
		Scanner fi = null;

		try
		{
			fi = new Scanner(new FileInputStream("StudentsData.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}

		int count = 0;
		while(fi.hasNextLine()){
			count++;
			fi.nextLine();}
		
		Scanner fi2 = null;
		try
		{
			fi2 = new Scanner(new FileInputStream("StudentsData.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		Student [] slist = new Student [count];
		  for(int i=0 ; i < count ; i++){
				int ID = fi2.nextInt();
				String name = fi2.next();
				String pass = fi2.next();
				int score = fi2.nextInt();
				int attempt = fi2.nextInt();
				slist[i] = new Student (ID, name, pass, score, attempt);
		  }
		
		  sortStudents(slist);
		  
		  return slist;
	}
	
	
	
	//This method will update the scores and numOfattempets then print it out in StudentsData file. 
	public static void updateStudent(Student[] Student){
		PrintWriter out = null;
		try
		{
			out = new PrintWriter(new FileOutputStream("StudentsData.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		
		
		for(int i = 0; i < Student.length; i++)
		{
			out.print(Student[i].toStringA());
			if (i!=(Student.length))
				out.println("");
		}
		
		out.close();
	}
	
	
	
	//This method related to the Timer. 
	private static final int setInterval() {

	    	if (seconds < 1) 
	    	timer.cancel();
	    	return --seconds;
	  }

	
	
	//This method takes-in an array of students, sort it using selection sort algorithm. 
	public static void sortStudents(Student[] sArray) {
			int i,j;
			Student temp;
			for ( j = 0; j < sArray.length-1; j++)  // advance the position through the entire array 
			  {			// find the max element in the unsorted students[j .. n-1]  	 assume the max is the first element 
				int iMax = j;  // iMax = index of max element
			    for ( i = j+1; i < sArray.length; i++) { // test against elements after j to find the largest 
			    	if (sArray[i].getScore() > sArray[iMax].getScore()) { // if this element is larger, then it is the new maximum 
			    		 iMax = i;                              // found new maximum; remember its index 
			        }
			    }
			    if(iMax != j) 
			    {
			        temp = sArray[iMax];
			        sArray[iMax] = sArray[j];
			        sArray[j] = temp;
			    }
			}             
		}
}
