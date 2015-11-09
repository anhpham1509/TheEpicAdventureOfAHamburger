import java.io.IOException;
import java.util.*;

public class gameProcessor {
	public gameProcessor() throws IOException{
		this.initiatePaths();
		this.initiateQuestionLibrary();
		this.initiateCharactersNames();
	}
	
	private ioProcessor myIoP = new ioProcessor();
	private player myPlayer = new player();
	private library myLib = new library();
	private challenges myChallenges = new challenges (myLib, myPlayer, myIoP);
	
	private ArrayList<String> charactersNames = new ArrayList <String>();
	private ArrayList<String> path = new ArrayList <String>();
	private boolean[] checkPaths = new boolean[6];
	
	public void startGame() throws IOException{
		this.showStory();
		myPlayer.updateName(myIoP.getPlayerName());
		this.showHighScore(); // need update
		while (myPlayer.getLives() > 0){
			this.showPaths();
			this.meetCharacters(myIoP.getInput(1) - 1);
		}
	}
	
	public void initiateCharactersNames(){
		charactersNames.add("Lettuce");
		charactersNames.add("Tomato");
		charactersNames.add("Cheese");
		charactersNames.add("Ham");
		charactersNames.add("Bacon");
		charactersNames.add("Taco");
	}
	
	public void initiatePaths(){
		path.add("1. Go To Meet Lettuce.");
		path.add("2. Go To Meet Tomato.");
		path.add("3. Go To Meet Cheese.");
		path.add("4. Go To Meet Ham.");
		path.add("5. Go To Meet Bacon.");
		path.add("6. Go Straight To The Boss.");
	}
	
	public void initiateQuestionLibrary() throws IOException{
		myLib.importQuestLib();
	}
	
	public void showHighScore() throws IOException{
		myLib.printHighScore();
	}
	
	public void showStory() throws IOException{
		myLib.printStories();
	}
	
	public void recordScore() throws IOException{
		myLib.writeHighScore(myPlayer.getName(), myPlayer.getPoints());
	}
	
	public void endGameProcedure() throws IOException{
		this.recordScore();
		this.showHighScore();
		System.exit(0);
	}
	
	public void showPaths() {
		System.out.println("Please choose a way (number) for the Bread:");
		for (String namePaths:path){
			System.out.println(namePaths);
		}
		System.out.println();
		System.out.print("----> I choose: ");
	}
	
	public void updatePaths(int x){
		this.path.set(x, this.path.get(x)+"----> Done");
		this.checkPaths[x] = true;
	}
	
	public boolean getCheckPaths(int x){
		return this.checkPaths[x];
	}
	
	public String getCharactersNames(int i){
		return this.charactersNames.get(i);
	}
	
	public void meetCharacters(int i) throws IOException{ // i - Index of Characters.
		boolean resultMeeting;
		if (!this.getCheckPaths(i)) {
			this.updatePaths(i);
			
			if (i < 5){ // Meeting Taco or not?
				System.out.println();
				System.out.println("You come to see the " + this.getCharactersNames(i) + ".\n");
				resultMeeting = myChallenges.showChallenges(i);
				myPlayer.updateLives(resultMeeting); // Show Challenges for Characters with Index of Characters. Then update Player's Lives
				myIoP.printMeetingResult(i, this.getCharactersNames(i), resultMeeting);
				myPlayer.getStatus();
			}
			else{
				System.out.println("You are now facing the evil Taco ....\n");
				//resultMeeting = myChallenges.showChallenges(i);
				myIoP.endGameNoti(myChallenges.showChallenges(i), myPlayer.getName());
				this.endGameProcedure();
			}
		}
		else {
			System.out.println("You have already met the " + this.getCharactersNames(i) + ", why do you come back?");
		}
		
		if (myPlayer.getLives() <= 0){
			myIoP.endGameNoti(false, myPlayer.getName());
			this.endGameProcedure();
		}
	}
}
