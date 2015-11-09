import java.util.*;

public class ioProcessor {
	public ioProcessor(){
	}
	
	private Scanner sc = new Scanner(System.in);
	
	public String getPlayerName(){
		return sc.nextLine();
	}
	
	public int getInput(int mode){ //Mode 1 for path Input. Mode 2 for Answer Input.
		int max;
		
		if (mode == 1)
			max = 6;
		else
			max = 2;
		
		while (true){ // Check Input
			if (sc.hasNextInt()){
				int temp = sc.nextInt();
				if ((temp>=1) && (temp<=max))
					return temp;
				tryAgainNoti(max);
			}
			else{
				tryAgainNoti(max);
				sc.next();
			}
		}
	}
	
	public void tryAgainNoti(int x){ // x - Number of maximum choices
		System.out.println("Input your choice NUMBER correctly!");
		System.out.println("Your choice must be an integer between 1 and " + x);
		System.out.println("Try again..");
	}
	
	public void showChoices(){
		System.out.println("1.Yes\tor\t2.No");
		System.out.print("\n----> I choose: ");
	}
	
	public void printMeetingResult(int i, String s, boolean b){ // i - Index of Characters. s - Name of Characters. y - Number of Correct Answers
		if (i < 5){
			if (b){
				System.out.println ("You have given at least 3/5 correct answers.");
				System.out.println (s + " has joined in your team.");
				System.out.println("You gained 1 more life.");
				System.out.println();
			}
			else{
				System.out.println("You have not given enough correct answers to invite " + s + " to your team!.");
				System.out.println ("You lost 1 life.");
			}	
		}
		else{
			if (b)
				System.out.println("You have given at least 12/15 correct answers.");
			else
				System.out.println("You have not given enough correct answers to win this Game.");
		}
	}
	
	public void endGameNoti(boolean x, String name){ // x - Win/ Loss Game. name - Player Name.
		if (x){
			System.out.println("You have given at least 12/15 correct answers.");
			System.out.println("The Taco has been defeated....");
			System.out.println("Congratulation " + name + "! Victory!");
			System.out.println();
		}
		else{
			System.out.println("So sorry! You're LOSER, " + name + "!!!");
			System.out.println("GAME OVER!!!");
			System.out.println();
		}
	}
}
