import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle1 {

  public static void main(String[] args) throws IOException {

    List<Long> leftList = new ArrayList<>();
    List<Long> rightList = new ArrayList<>();
    long answer = 0L;

    try (InputStream inputStream = Puzzle1.class.getResourceAsStream("day-1-input.txt");
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        String[] numbers = line.split(" {3}");
        leftList.add(Long.parseLong(numbers[0]));
        rightList.add(Long.parseLong(numbers[1]));
      }
    }

    leftList.sort(Long::compareTo);
    rightList.sort(Long::compareTo);
    assert leftList.size() == rightList.size();

    for (int i = 0; i < leftList.size(); i++) {
      answer += Math.abs(leftList.get(i) - rightList.get(i));
    }

    System.out.println(answer);
  }
}
