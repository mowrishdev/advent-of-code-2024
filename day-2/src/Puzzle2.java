import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Puzzle2 {

  public static void main(String[] args) throws IOException {

    long answer = 0L;

    try (InputStream inputStream = Puzzle1.class.getResourceAsStream("day-2-input.txt");
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        String[] levelsStringArray = line.split(" ");
        long[] levels = new long[levelsStringArray.length];
        for (int i = 0; i < levels.length; i++) {
          levels[i] = Long.parseLong(levelsStringArray[i]);
        }

        if (isCurrentLevelsSafe(levels) || canLevelsBeSafe(levels)) {
          answer += 1L;
        }
      }
    }
    System.out.println(answer);
  }

  private static boolean canLevelsBeSafe(long[] levels) {

    for (int i = 0; i < levels.length; i++) {
      long[] reducedLevels = new long[levels.length - 1];
      int counter = 0;
      for (int j = 0; j < levels.length; j++) {
        if (j != i) {
          reducedLevels[counter++] = levels[j];
        }
      }

      if (isCurrentLevelsSafe(reducedLevels)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isCurrentLevelsSafe(long[] levels) {

    boolean isIncreasing = true, isDecreasing = true;

    for (int i = 1; i < levels.length; i++) {
      long diff = levels[i] - levels[i - 1];
      if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
        return false;
      }
      if (diff < 0) {
        isIncreasing = false;
      } else if (diff > 0) {
        isDecreasing = false;
      }
    }

    return isIncreasing || isDecreasing;
  }
}
