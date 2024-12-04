import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle1 {

  public static void main(String[] args) throws IOException {

    try (InputStream inputStream = Puzzle1.class.getResourceAsStream("day-4-input.txt");
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

      int[][] directionsXYAxis =
          new int[][] {{1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}};
      long answer = 0L;

      for (int i = 0; i < input2dList.size(); i++) {
        for (int j = 0; j < input2dList.get(i).length; j++) {
          for (int[] directionXYAxis : directionsXYAxis) {
            if (isXmas(input2dList, i, j, directionXYAxis)) {
              answer++;
            }
          }
        }
      }

      System.out.println(answer);
    }
  }

  private static boolean isXmas(List<char[]> input2dList, int i, int j, int[] direction) {

    for (int k = 0; k < "XMAS".length(); k++) {
      int currentCharacter = i + k * direction[0];
      int nextCharacterInTheDirection = j + k * direction[1];

      if (currentCharacter < 0
          || nextCharacterInTheDirection < 0
          || currentCharacter >= input2dList.size()
          || nextCharacterInTheDirection >= input2dList.get(i).length
          || input2dList.get(currentCharacter)[nextCharacterInTheDirection] != "XMAS".charAt(k)) {
        return false;
      }
    }
    return true;
  }
}
