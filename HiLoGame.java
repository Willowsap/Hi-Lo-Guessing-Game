import java.util.ArrayList;
import java.util.Random;

public class HiLoGame {
  private Random rand;
  private int numInts;
  private int startInt;
  private int numGames;
  private ArrayList<Integer> results;

  public HiLoGame(int numInts, int startInt) {
    this.rand = new Random();
    this.startInt = startInt;
    this.numInts = numInts;
    this.results = new ArrayList<Integer>(100);
  }

  public void setNumInts(int numInts) {
    this.numInts = numInts;
  }

  public void setStartInt(int startInt) {
    this.startInt = startInt;
  }

  public int getNumInts() {
    return this.numInts;
  }

  public int getStartInt() {
    return this.startInt;
  }

  public ArrayList<Integer> getResults() {
    return this.results;
  }

	public int playGame() {
		int topMid = numInts/2;
		boolean guessedRight = true;
		int currNumber = rand.nextInt(numInts)+startInt;
		int nextNumber = 0;
		int numCount = 0;
		String guess = "";
		do {
			guess = (currNumber > topMid) ? "smaller" : "bigger";
			do {
				nextNumber = this.rand.nextInt(numInts)+startInt;
			} while(nextNumber == currNumber);
			if (((nextNumber > currNumber) && (guess == "smaller"))
				|| ((nextNumber < currNumber) && (guess == "bigger"))) {
				guessedRight = false;
			} else {
				numCount++;
				currNumber = nextNumber;
			}
		} while(guessedRight);
		return numCount;
	}

  public void playGames(int numGames) {
    this.numGames = numGames;
    // zero out results list
    for(int i = 0; i < 100; i++) {
			this.results.add(0);
		}
    // play games
    for(int i = 0; i < numGames; i++) {
			int result = this.playGame();
			int count = this.results.get(result) + 1;
			this.results.set(result, count);
		}
  }

  public void printResults() {
    double average = 0.0;
    String topLine = "    Correct Guesses          Percent Chance";
    double lineAverage = 0.0;

    System.out.println(topLine);
		for(int i = 0; i < this.results.size(); i++) {
			int result = this.results.get(i);
			if(result != 0) {
        lineAverage = (((double)result/(double)this.numGames)*100.0);
				System.out.println(calcLine(i,result, lineAverage));
			}
			average += (result * i);
		}
		average /= numGames;
		System.out.println();
		System.out.println("Average: " + average);
  }

  private String calcLine(int length, int numTimes, double average) {

    int lineSegmentOneLength = 4;
    int lineSegmentTwoLength = 25;
    int lineSegmentThreeLength = 14;
    String lineSegmentOne = "";
    String lineSegmentTwo = "";
    String lineSegmentThree = "";
    int digitCalcStorage = 0;
    int numDigits = 0;

    // lineSegmentOne
    digitCalcStorage = length;
    numDigits = 0;
    if(length == 0) {
      numDigits = 1;
    } else {
      while(digitCalcStorage > 0) {
        digitCalcStorage = digitCalcStorage / 10;
        numDigits++;
      }
    }
    lineSegmentOne += length;
    for(int i = 0; i < (lineSegmentOneLength-numDigits); i++) {
      lineSegmentOne += " ";
    }

    // lineSegmentTwo
    digitCalcStorage = numTimes;
    numDigits = 0;
    while(digitCalcStorage > 0) {
      digitCalcStorage = digitCalcStorage / 10;
      numDigits++;
    }
    lineSegmentTwo += numTimes;
    for(int i = 0; i < (lineSegmentTwoLength-numDigits); i++) {
      lineSegmentTwo += " ";
    }

    // lineSegmentThree
    lineSegmentThree += average;
    return lineSegmentOne + lineSegmentTwo + lineSegmentThree;
    //System.out.println(i + " correct guesses: " + result + "             " +  + "%");
  }

	public static void main(String[] args) {
    //setup
    int numInts = 9;
    int startInt = 1;
    int numGames = 1000000;
    HiLoGame game = new HiLoGame(numInts, startInt);
    game.playGames(numGames);
    game.printResults();
	}

}
