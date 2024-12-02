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
        String[] reportsStringArray = line.split(" ");
        long[] reportsIntegerArray = new long[reportsStringArray.length];
        for (int i = 0; i < reportsIntegerArray.length; i++) {
          reportsIntegerArray[i] = Long.parseLong(reportsStringArray[i]);
        }

        if (isCurrentReportSafe(reportsIntegerArray) || canBeSafeReport(reportsIntegerArray)) {
          answer += 1L;
        }
      }
    }
    System.out.println(answer);
  }

  private static boolean canBeSafeReport(long[] reportsIntegerArray) {

    for (int i = 0; i < reportsIntegerArray.length; i++) {
      long[] reducedLevels = new long[reportsIntegerArray.length - 1];
      int counter = 0;
      for (int j = 0; j < reportsIntegerArray.length; j++) {
        if (j != i) {
          reducedLevels[counter++] = reportsIntegerArray[j];
        }
      }

      if (isCurrentReportSafe(reducedLevels)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isCurrentReportSafe(long[] reportsIntegerArray) {

    boolean isIncreasing = true, isDecreasing = true;

    for (int i = 1; i < reportsIntegerArray.length; i++) {
      long diff = reportsIntegerArray[i] - reportsIntegerArray[i-1];
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
