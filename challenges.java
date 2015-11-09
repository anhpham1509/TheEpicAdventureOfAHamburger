import java.io.IOException;

public class challenges{
	public challenges (library Lib, player Player, ioProcessor ioP) {
		this.myLib = Lib;
		this.myPlayer = Player;
		this.myIoP = ioP;
	}
	
	private library myLib;
	private player myPlayer;
	private ioProcessor myIoP;
	
	public void endGameProcedure() throws IOException{
		myLib.writeHighScore(myPlayer.getName(), myPlayer.getPoints());
		myLib.printHighScore();
		System.exit(0);
	}
	
	public boolean showChallenges(int i) throws IOException{
		int numberQuestions, mode;
		int currentQuestion = 1;
		int minCorrectAns, maxIncorrectAns;
		
		if (i < 5){
			numberQuestions =  5;
			mode = 1;
			minCorrectAns = 3;
		}
		else{
			numberQuestions = 15;
			mode = 2;
			minCorrectAns = 12;
		}
		maxIncorrectAns = numberQuestions - minCorrectAns;
		
		while (currentQuestion <= numberQuestions){
			if (myPlayer.getLives() > 0){
				int indexCurrentQuestion = myLib.getRandomIndex(mode);
				this.showQuestions(currentQuestion, indexCurrentQuestion);
				myIoP.showChoices();
				if (this.checkAnswer(indexCurrentQuestion))
					minCorrectAns--;
				else
					maxIncorrectAns--;
				myPlayer.getStatus();
				if (myPlayer.getLives() < 1){
					myIoP.endGameNoti(false, myPlayer.getName());
					this.endGameProcedure();
				}
				if (minCorrectAns == 0)
					return true;
					
				if ((maxIncorrectAns + 1) == 0)
					return false;
			}
			else{
				myIoP.endGameNoti(false, myPlayer.getName());
				this.endGameProcedure();
			}
			currentQuestion++;
		}
		return false;
	}
	
	public void showQuestions(int i, int y){ // i - Index of Current Question. y - Index of Question in Library
		System.out.println("Question number " + i + ": ");
		System.out.println(myLib.getQuestions(y));
	}
	
	public boolean checkAnswer(int i){ // i - Index of Question in Library.
		if (myPlayer.getAnswer() == myLib.getCorrectChoices(i)){
			System.out.println();
			System.out.println ("You have given a correct answer.");
			if (myLib.getQuestionPoints(i) == 1)
				System.out.println ("You have collected 1 more point.");
			else
				System.out.println ("You have collected " + myLib.getQuestionPoints(i) + " more points.");
			System.out.println();
			myPlayer.updatePoints(myLib.getQuestionPoints(i));
			return true;
		}
		else{
			System.out.println();
			System.out.println ("You have given an incorrect answer." + "\n" + "You have lost a life.");
			System.out.println();
			myPlayer.updateLives(false);
			return false;
		}
	}
}
