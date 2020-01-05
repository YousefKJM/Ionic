public class Student  {  
 
	private int ID; 
	private String name;
	private String pass;
	private  int score; 
	private  int attempt;

	
	public Student(int ID, String name,String pass, int score,int attempt ){ // this Constructor for The Login + Profile
		this.ID = ID;
		this.name = name;
		this.pass = pass;
		this.score = score;
		this.attempt = attempt;
	}

	public int getID() {
			
	      return ID;
	 }  
	
	
	public String getName() {
			
		return name;
	 }
	
	
	public String getPass() {
			
	      return pass;
	}
	
	
	public int getScore() {
			
	      return score;
	}
	
	
	public int getAttempt() {
			
	      return attempt ;
	}
	 
	
	 public String toString() {
		return getID() + "\t" + getName() + "    \t" + getScore() +"\t\t"+ getAttempt();
	}
	 
	 public String toStringA() {
			return getID() + "\t" + getName() + "\t" + getPass() + "    \t" + getScore() +"\t\t"+ getAttempt();
		}
	 
	 
  public void updScore(int correct) {
	  if (correct > this.score) {
		  int temp;
		  temp = this.score;
		  this.score = correct;
		  correct = temp;	
	       }
	   }
  
  
  public void updAttempt(int attempt) {
      this.attempt += attempt;
       }
 }
