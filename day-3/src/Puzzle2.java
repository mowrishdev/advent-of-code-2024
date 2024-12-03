import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle2 {

  public static void main(String[] args) throws IOException {

    try (InputStream inputStream = Puzzle1.class.getResourceAsStream("day-3-input.txt");
        Scanner scanner = new Scanner(inputStream)) {

      long answer = 0L;

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();

        line = line.replaceAll("don't\\(\\).*?do\\(\\)", "");
        line = line.replaceAll("don't\\(\\).*", "");

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
          long leftNumber = Long.parseLong(matcher.group(1));
          long rightNumber = Long.parseLong(matcher.group(2));
          answer += leftNumber * rightNumber;
        }
      }

      System.out.println(answer);
    }
  }
}
