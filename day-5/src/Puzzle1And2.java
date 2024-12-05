import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Puzzle1And2 {

  public static void main(String[] args) throws IOException {

    try (InputStream inputStream = Puzzle1And2.class.getResourceAsStream("day-5-input.txt");
        Scanner scanner = new Scanner(inputStream)) {
      HashMap<Integer, List<Integer>> pageOrderingRules = new HashMap<>();
      List<int[]> updatesList = new ArrayList<>();
      long p1Answer = 0L;
      long p2Answer = 0L;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();

        if (line.contains("|")) {
          String[] pageOrderingRule = line.split("\\|");
          if (pageOrderingRules.containsKey(Integer.parseInt(pageOrderingRule[0]))) {
            pageOrderingRules
                .get(Integer.parseInt(pageOrderingRule[0]))
                .add(Integer.parseInt(pageOrderingRule[1]));
          } else {
            pageOrderingRules.put(
                Integer.parseInt(pageOrderingRule[0]),
                new ArrayList<>(Arrays.asList(Integer.parseInt(pageOrderingRule[1]))));
          }
        }

        if (line.contains(",")) {
          String[] updateStringArray = line.split(",");
          int[] updateIntegerArray = new int[updateStringArray.length];
          for (int i = 0; i < updateStringArray.length; i++) {
            updateIntegerArray[i] = Integer.parseInt(updateStringArray[i]);
          }
          updatesList.add(updateIntegerArray);
        }
      }

      System.out.println(pageOrderingRules);

      for (int[] updates : updatesList) {
        if (isValidUpdate(updates, pageOrderingRules)) {
          p1Answer += updates[updates.length / 2];
        } else {
          int[] invalidUpdatesCorrected = correctUpdates(updates, pageOrderingRules);
          p2Answer += invalidUpdatesCorrected[invalidUpdatesCorrected.length / 2];
        }
      }

      System.out.println(p1Answer);
      System.out.println(p2Answer);
    }
  }

  private static int[] correctUpdates(
      int[] updates, HashMap<Integer, List<Integer>> pageOrderingRules) {

    for (int i = 0; i < updates.length - 1; i++) {
      if (pageOrderingRules.containsKey(updates[i])) {
        for (int j = i + 1; j < updates.length; j++) {
          if (pageOrderingRules.get(updates[i]).contains(updates[j])) {
            int temp = updates[i];
            updates[i] = updates[j];
            updates[j] = temp;
          }
        }
      }
    }
    return updates;
  }

  private static boolean isValidUpdate(
      int[] updates, HashMap<Integer, List<Integer>> pageOrderingRules) {

    for (int i = 1; i < updates.length; i++) {
      if (pageOrderingRules.containsKey(updates[i])) {
        for (int j = 0; j < i; j++) {
          if (pageOrderingRules.get(updates[i]).contains(updates[j])) {
            return false;
          }
        }
      }
    }

    return true;
  }
}
