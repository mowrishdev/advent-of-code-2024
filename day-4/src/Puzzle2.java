import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Puzzle2 {

  public static void main(String[] args) throws IOException {

    try (InputStream inputStream = Puzzle2.class.getResourceAsStream("day-4-input.txt");
        Scanner scanner = new Scanner(inputStream)) {

      List<char[]> input2dList = new ArrayList<>();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();

        char[] inputArray = new char[line.length()];
        for (int i = 0; i < inputArray.length; i++) {
          inputArray[i] = line.charAt(i);
        }
        input2dList.add(inputArray);
      }

      long answer = 0L;

      for (int i = 0; i < input2dList.size(); i++) {
        for (int j = 0; j < input2dList.get(i).length; j++) {
          if (input2dList.get(i)[j] == 'A' && isValidX(input2dList, i, j)) {
            answer++;
          }
        }
      }

      System.out.println(answer);
    }
  }

  private static boolean isValidX(List<char[]> input2dList, int i, int j) {

    List<String> validXList = Arrays.asList("MSMS", "MSSM", "SMMS", "SMSM");

    if (i - 1 < 0
        || j - 1 < 0
        || i + 1 >= input2dList.size()
        || j + 1 >= input2dList.get(i).length) {
      return false;
    }

    String X =
        String.valueOf(
            new char[] {
              input2dList.get(i - 1)[j + 1],
              input2dList.get(i + 1)[j - 1],
              input2dList.get(i - 1)[j - 1],
              input2dList.get(i + 1)[j + 1]
            });

    return validXList.contains(X);
  }
}
