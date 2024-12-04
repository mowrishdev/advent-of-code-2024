import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Puzzle1 {

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

        if (isLevelsSafe(levels)) {
          answer += 1L;
        }
      }
    }
    System.out.println(answer);
  }

  private static boolean isLevelsSafe(long[] levels) {

    boolean isIncreasing = true, isDecreasing = true;

    for (int i = 1; i < levels.length; i++) {
      long difference = levels[i] - levels[i - 1];
      if (Math.abs(difference) < 1 || Math.abs(difference) > 3) {
        return false;
      }
      if (difference < 0) {
        isIncreasing = false;
      } else if (difference > 0) {
        isDecreasing = false;
      }
    }

    return isIncreasing || isDecreasing;
  }
}
