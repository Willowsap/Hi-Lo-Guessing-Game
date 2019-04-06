import java.util.ArrayList;
import java.util.Random;

public class BartsVersion {
	public static void main(String[] args) {

    int depth = Integer.parseInt(args[0]);
    ArrayList<Long[]> digitCount = new ArrayList<Long[]>();
    ArrayList<Double[]> digitCountTimesOdd = new ArrayList<Double[]>();
    double[] guessOdds = new double[10];
    guessOdds[1] = 8.0/8.0;
    guessOdds[2] = 7.0/8.0;
    guessOdds[3] = 6.0/8.0;
    guessOdds[4] = 5.0/8.0;
    guessOdds[5] = 4.0/8.0;
    guessOdds[6] = 5.0/8.0;
    guessOdds[7] = 6.0/8.0;
    guessOdds[8] = 7.0/8.0;
    guessOdds[9] = 8.0/8.0;

    // initialize
    digitCount.add(new Long[10]);
    digitCount.get(0)[0] = 9l;
    for (int i = 1; i < digitCount.get(0).length; i++) {
      digitCount.get(0)[i] = 1l;
    }


    for (int i = 1; i < depth; i++) {
      digitCount.add(new Long[10]);
      digitCount.get(i)[0] = 0l;
      digitCount.get(i)[1] = digitCount.get(i-1)[5] + digitCount.get(i-1)[6] + digitCount.get(i-1)[7] + digitCount.get(i-1)[8] + digitCount.get(i-1)[9];
      digitCount.get(i)[2] = digitCount.get(i-1)[1] + digitCount.get(i-1)[5] + digitCount.get(i-1)[6] + digitCount.get(i-1)[7] + digitCount.get(i-1)[8] + digitCount.get(i-1)[9];
      digitCount.get(i)[3] = digitCount.get(i-1)[1] + digitCount.get(i-1)[2] + digitCount.get(i-1)[5] + digitCount.get(i-1)[6] + digitCount.get(i-1)[7] + digitCount.get(i-1)[8] + digitCount.get(i-1)[9];
      digitCount.get(i)[4] = digitCount.get(i-1)[1] + digitCount.get(i-1)[2] + digitCount.get(i-1)[3] + digitCount.get(i-1)[5] + digitCount.get(i-1)[6] + digitCount.get(i-1)[7] + digitCount.get(i-1)[8] + digitCount.get(i-1)[9];
      digitCount.get(i)[5] = digitCount.get(i-1)[1] + digitCount.get(i-1)[2] + digitCount.get(i-1)[3] + digitCount.get(i-1)[4] + digitCount.get(i-1)[6] + digitCount.get(i-1)[7] + digitCount.get(i-1)[8] + digitCount.get(i-1)[9];
      digitCount.get(i)[6] = digitCount.get(i-1)[1] + digitCount.get(i-1)[2] + digitCount.get(i-1)[3] + digitCount.get(i-1)[4] + digitCount.get(i-1)[7] + digitCount.get(i-1)[8] + digitCount.get(i-1)[9];
      digitCount.get(i)[7] = digitCount.get(i-1)[1] + digitCount.get(i-1)[2] + digitCount.get(i-1)[3] + digitCount.get(i-1)[4] + digitCount.get(i-1)[8] + digitCount.get(i-1)[9];
      digitCount.get(i)[8] = digitCount.get(i-1)[1] + digitCount.get(i-1)[2] + digitCount.get(i-1)[3] + digitCount.get(i-1)[4] + digitCount.get(i-1)[9];
      digitCount.get(i)[9] = digitCount.get(i-1)[1] + digitCount.get(i-1)[2] + digitCount.get(i-1)[3] + digitCount.get(i-1)[4];
      for (int j = 1; j < 10; j++) {
        digitCount.get(i)[0] += digitCount.get(i)[j];
      }
    }

    for (int i = 0; i < depth; i++) {
      digitCountTimesOdd.add(new Double[10]);
      digitCountTimesOdd.get(i)[0] = 0.0;
      digitCountTimesOdd.get(i)[1] = ((double)(digitCount.get(i)[1])) * guessOdds[1];
      digitCountTimesOdd.get(i)[2] = ((double)(digitCount.get(i)[2])) * guessOdds[2];
      digitCountTimesOdd.get(i)[3] = ((double)(digitCount.get(i)[3])) * guessOdds[3];
      digitCountTimesOdd.get(i)[4] = ((double)(digitCount.get(i)[4])) * guessOdds[4];
      digitCountTimesOdd.get(i)[5] = ((double)(digitCount.get(i)[5])) * guessOdds[5];
      digitCountTimesOdd.get(i)[6] = ((double)(digitCount.get(i)[6])) * guessOdds[6];
      digitCountTimesOdd.get(i)[7] = ((double)(digitCount.get(i)[7])) * guessOdds[7];
      digitCountTimesOdd.get(i)[8] = ((double)(digitCount.get(i)[8])) * guessOdds[8];
      digitCountTimesOdd.get(i)[9] = ((double)(digitCount.get(i)[9])) * guessOdds[9];
      for (int j = 1; j < 10; j++) {
        digitCountTimesOdd.get(i)[0] += digitCountTimesOdd.get(i)[j];
      }
    }

    int lineLength = 40;
    int headLength = 5;
    for (int i = 0; i < depth; i++) {
      String lineSegOne = "";
      String lineSegTwo = "";
      String lineSegThree = "";
      lineSegOne += (i+ ": ");
      for (int j = lineSegOne.length(); j < headLength; j++) {
        lineSegOne += " ";
      }
      lineSegTwo += ("total: " + digitCountTimesOdd.get(i)[0]);
      for (int j = lineSegTwo.length(); j < lineLength; j++) {
        lineSegTwo += " ";
      }
      lineSegThree += ("survival rate: " + (digitCountTimesOdd.get(i)[0] / digitCount.get(i)[0]));
      for (int j = lineSegThree.length(); j < lineLength; j++) {
        lineSegThree += " ";
      }
      System.out.println(lineSegOne+lineSegTwo+lineSegThree);
    }

	}
}
