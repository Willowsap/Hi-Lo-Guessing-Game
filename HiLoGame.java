import java.util.ArrayList;
import java.util.Random;

public class HiLoGame {
  private Random rand;
  private int numInts;
  private int startInt;
  private int numGames;
  private ArrayList<Integer> results;
  private ArrayList<Integer[]> numberPerStep;
  private ArrayList<Integer> totalPerNumber;

  public HiLoGame(int numInts, int startInt) {
    this.rand = new Random();
    this.startInt = startInt;
    this.numInts = numInts;
    this.results = new ArrayList<Integer>(100);
    this.numberPerStep = new ArrayList<Integer[]>();
    this.totalPerNumber = new ArrayList<Integer>();
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
    if (numCount == this.numberPerStep.size()) {
      this.numberPerStep.add(new Integer[10]);
      for (int i = 0; i < 10; i++) {
        this.numberPerStep.get(numCount)[i] = 0;
      }
    }
    if (numCount == this.totalPerNumber.size()) {
      this.totalPerNumber.add(0);
    }
		do {
			guess = (currNumber > topMid) ? "smaller" : "bigger";
			do {
				nextNumber = this.rand.nextInt(numInts)+startInt;
			} while(nextNumber == currNumber);
      this.numberPerStep.get(numCount)[currNumber] = this.numberPerStep.get(numCount)[currNumber] + 1;
      this.totalPerNumber.set(numCount, this.totalPerNumber.get(numCount) + 1);
			if (((nextNumber > currNumber) && (guess == "smaller"))
				|| ((nextNumber < currNumber) && (guess == "bigger"))) {
				guessedRight = false;
			} else {
				numCount++;
        if (numCount == this.numberPerStep.size()) {
          this.numberPerStep.add(new Integer[10]);
          for (int i = 0; i < 10; i++) {
            this.numberPerStep.get(numCount)[i] = 0;
          }
        }
        if (numCount == this.totalPerNumber.size()) {
          this.totalPerNumber.add(0);
        }
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
    int totalCheck = 0;
    double average = 0.0;
    String topLine = "    " +
      "Correct Guesses          " +
      "Percent Chance           " +
      "Dropoff Ratio            ";
    double lineAverage = 0.0;

    double prevAverage = -1;
    System.out.println(topLine);
		for(int i = 0; i < this.results.size(); i++) {
			int result = this.results.get(i);
			if(result != 0) {
        lineAverage = (((double)result/(double)this.numGames)*100.0);
				System.out.println(calcLine(i,result, lineAverage, prevAverage));
        prevAverage = lineAverage;
			}
			average += (result * i);
      totalCheck += result;
		}
		average /= numGames;
		System.out.println();
		System.out.println("Average: " + average);
    System.out.println("Total: " + totalCheck);
    System.out.println();
  }

  public void printNumberPerStep(boolean ratios, int maxGuessCount) {
    for (int i = 0; i < this.numberPerStep.size(); i++) {
      if (i >= maxGuessCount) break;
      int firstDigit = ((i + 1) % 10);
      int headSegLength = 14;
      int numSegLength = 16;
      int ratioNumSegLength = 16;
      String prefix = "" + (i+1);
      if (i+1 == 11 || i+1 == 12 || i+1 == 13) {
        prefix += "th num: ";
      } else if (firstDigit == 1) {
        prefix += "st num: ";
      } else if (firstDigit == 2) {
        prefix += "nd num: ";
      } else if (firstDigit == 3) {
        prefix += "rd num: ";
      } else {
        prefix += "th num: ";
      }
      for(int l = prefix.length(); l < headSegLength; l++) {
        prefix += " ";
      }
      System.out.print(prefix);
      if(ratios) {
        for (int j = 1; (j < this.numberPerStep.get(i).length); j++) {
          String seg = "";
          double percent = ((((double)this.numberPerStep.get(i)[j]) / (double)(this.totalPerNumber.get(i))*100.0));
          seg += (j + "'s: " + String.format("%3.1f", percent) + "%,");
          for(int l = seg.length(); l < ratioNumSegLength; l++) {
            seg += " ";
          }
          System.out.print(seg);
        }
        System.out.print("Total: " + this.totalPerNumber.get(i));
      } else {
        for (int j = 1; j < this.numberPerStep.get(i).length; j++) {
          String seg = "";
          seg += (j + "'s: " + this.numberPerStep.get(i)[j] + ",");
          for(int l = seg.length(); l < numSegLength; l++) {
            seg += " ";
          }
          System.out.print(seg);
        }
      }

      System.out.println();
    }
    System.out.println();
  }

  private String calcLine(int length, int numTimes, double average, double prevAverage) {

    int lineSegmentOneLength = 4;
    int lineSegmentTwoLength = 25;
    int lineSegmentThreeLength = 25;
    int lineSegmentFourLength = 25;
    String lineSegmentOne = "";
    String lineSegmentTwo = "";
    String lineSegmentThree = "";
    String lineSegmentFour = "";
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
    lineSegmentThree += String.format("%8.5f", average);
    for(int i = lineSegmentThree.length(); i < lineSegmentThreeLength; i++) {
      lineSegmentThree += " ";
    }

    // lingSegmentFour
    if (prevAverage != -1) {
      lineSegmentFour+= String.format("%8.5f", average/prevAverage);
      for(int i = lineSegmentFour.length(); i < lineSegmentFourLength; i++) {
        lineSegmentFour += " ";
      }
    }
    return lineSegmentOne + lineSegmentTwo + lineSegmentThree + lineSegmentFour;

  }

	public static void main(String[] args) {
    //setup
    int numInts = 9;
    int startInt = 1;
    int numGames            = (args.length >= 1) ? Integer.parseInt(args[0]) : 1000000;
    boolean printRatios     = (args.length >= 2) ? (args[1].equals("true")) : false;
    boolean printNumPerStep = (args.length >= 3) ? (args[2].equals("true")) : false;
    int maxLength           = (args.length >= 4) ? Integer.parseInt(args[3]) : 100;
    HiLoGame game = new HiLoGame(numInts, startInt);
    game.playGames(numGames);
    game.printResults();
    if(printNumPerStep) {
      game.printNumberPerStep(false, maxLength);
    }
    if(printRatios) {
      game.printNumberPerStep(true, maxLength);
    }
	}

}
