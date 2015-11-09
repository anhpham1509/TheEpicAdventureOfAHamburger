public class player {
	public player(){
		this.lives = 2;
		this.points = 0;
		this.name = "";
	}
	
	private ioProcessor myIoP = new ioProcessor();
	
	private int lives;
	private int points;
	private String name;
	
	public String getName(){
		return this.name;
	}
	
	public void updateName(String s){
		this.name = s;
	}
	
	public int getPoints(){
		return points;
	}
	
	public void updatePoints(int value){
		this.points += value;
	}
	
	public int getLives(){
		return this.lives;
	}
	
	public void updateLives(boolean b){
		if (b)
			this.lives++;
		else
			this.lives--;
	}
	
	public boolean getAnswer(){
		if (myIoP.getInput(2) == 1)
			return true;
		else
			return false;
	}
	
	public void getStatus(){
		if (this.lives > 0){
			if (this.lives == 1)
				System.out.println ("You are having 1 life to finish this journey.");
			else
				System.out.println ("You are having " + this.lives + " lives to finish this journey.");
		}
		else
			System.out.println ("You are now run out of lives, the journey ends here.");
		
		if (this.points < 2)
			System.out.println ("Till now, you have collected " + this.points + " point.");
		else
			System.out.println ("Till now, you have collected " + this.points + " points.");
		System.out.println();
	}
}
