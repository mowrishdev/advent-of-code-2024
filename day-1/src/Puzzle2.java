import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Puzzle2 {

  public static void main(String[] args) throws IOException {

    Set<Long> leftSet = new HashSet<>();
    Map<Long, Long> rightMap = new HashMap<>();
    long answer = 0L;

    try (InputStream inputStream = Puzzle2.class.getResourceAsStream("day-1-input.txt");
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        String[] numbers = line.split(" {3}");
        leftSet.add(Long.parseLong(numbers[0]));
        rightMap.put(
            Long.parseLong(numbers[1]), rightMap.getOrDefault(Long.parseLong(numbers[1]), 0L) + 1L);
      }
    }

    for (Long leftNumber : leftSet) {
      answer += leftNumber * rightMap.getOrDefault(leftNumber, 0L);
    }

    System.out.println(answer);
  }
}
