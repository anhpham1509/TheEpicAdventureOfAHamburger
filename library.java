import java.io.*;
import java.util.*;

public class library {
	
	public library(){
		this.f.add("Stories.txt");
		this.f.add("Questions.txt");
		this.f.add("HighScores.txt");
	}
	
	private ArrayList<String> f = new ArrayList<String>(3);
	private ArrayList<String> questions = new ArrayList<String>(100);
	private ArrayList<Integer> questionPoints = new ArrayList <Integer>(100);
	private boolean[] correctChoice = new boolean[100];
	private boolean[] checkQuestions = new boolean[100];
	private int totalQuestions;
	
	public String getFileName(int i){
		return f.get(i);
	}
	
	public void printHighScore() throws IOException{
		System.out.println();
		this.loadFile(this.getFileName(2));
		System.out.println();
	}
	
	public void importQuestLib() throws IOException{
		this.loadFile(this.getFileName(1));
	}
	
	public void printStories() throws IOException{
		this.loadFile(this.getFileName(0));
	}
	
	public void writeHighScore(String name, int point) throws IOException{ //Record Player Score
		this.writeFile(this.getFileName(2), name, point);
	}
	
	public void loadFile(String f) throws IOException{
		FileReader fileReader = new FileReader(f);
		BufferedReader reader = new BufferedReader(fileReader);
		
		String s = null;
		this.totalQuestions = 0;
		
		while ((s = reader.readLine()) != null){
			if (f != this.getFileName(1)){
				System.out.println(s);
			}
			else{
				importQuestions(s, this.totalQuestions);
				this.totalQuestions++;
			}	
		}
		reader.close();
		fileReader.close();
	}
	
	public void importQuestions(String s, int i){ // s - A line in Library File. i - index of current importing question.
		String[] temp = s.split("/");
		
		this.questions.add(temp[0]); // Import Question
		
		if (temp[1].compareTo("1") == 0) // Import Correct Answer
			this.correctChoice[i] = true;
		else
			this.correctChoice[i] = false;
		
		this.questionPoints.add(Integer.parseInt(temp[2])); // Import point of questions
		
		this.checkQuestions[i] = true; // Mark Imported Questions
	}
	
	public int getRandomIndex(int mode){ // Mode 2 - Taco's questions. Mode 1 - Other Characters questions.
		int r, higherLim, lowerLim;
		if (mode == 1){
			higherLim = 30;
			lowerLim = 1;
		}
		else{
			higherLim = this.totalQuestions;
			lowerLim = 31;
		}
		
		while (true){
			r = (int) (Math.random() * (higherLim - lowerLim)) + lowerLim;
			if (this.checkQuestions[r-1]){
				this.checkQuestions[r-1] = false;
				return r-1;
			}
		}
	}
	
	public void writeFile (String f, String name, int point) throws IOException{
		FileWriter fileWriter = new FileWriter(f,true); // Continue Write File not Overwrite
		BufferedWriter writer = new BufferedWriter(fileWriter);
		writer.newLine();
		if (name.length() <= 7)
			writer.write(name + "\t\t" + point);
		else
			writer.write(name + "\t" + point);
		writer.close();
		fileWriter.close();
	}
	
	public String getQuestions(int i){
		return this.questions.get(i);
	}
	
	public boolean getCorrectChoices(int i){
		return this.correctChoice[i];
	}
	
	public int getQuestionPoints(int i){
		return this.questionPoints.get(i);
	}
}
