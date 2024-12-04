import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Puzzle1 {

  public static void main(String[] args) throws IOException {

    try (InputStream inputStream = Puzzle1.class.getResourceAsStream("day-<number>-input.txt");
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
      }
    }
  }
}
