import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Trebuchet {
  public static final Map<String, String> DIGITS = Map.of(
      "one", "1",
      "two", "2",
      "three", "3",
      "four", "4",
      "five", "5",
      "six", "6",
      "seven", "7",
      "eight", "8",
      "nine", "9");

  public static void main(String[] args) {
    try {
      System.out.println("Part 1 answer: %d".formatted(part1("./part1_input.txt")));
      System.out.println("Part 2 answer: %d".formatted(part2("./part2_input.txt")));
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  private static int part1(String filePath) throws IOException {
    int sum = 0;
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line = reader.readLine();

    while (line != null ){
      String digit = "";

      for (int i = 0; i < line.length(); i++) {
        if (Character.isDigit(line.charAt(i))) {
          digit += line.charAt(i);
          break;
        }
      }

      for (int i = line.length() - 1; i >= 0; i--) {
        if (Character.isDigit(line.charAt(i))) {
          digit += line.charAt(i);
          break;
        }
      }

      sum += Integer.parseInt(digit);
      line = reader.readLine();
    }

    return sum;
  }

  private static int part2(String filePath) throws IOException {
    int sum = 0;
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line = reader.readLine();

    while (line != null ){
      String digit = "";

      outerloop:
      for (int i = 0; i < line.length(); i++) {
        if (Character.isDigit(line.charAt(i))) {
          digit += line.charAt(i);
          break;
        }

        for (Map.Entry<String, String> entry : DIGITS.entrySet()) {
          String digitWord = entry.getKey();
          int startIdx = i;
          int endIdx = Math.min(line.length() - 1, i + digitWord.length());
          String target = line.substring(startIdx, endIdx);

          if (target.equals(digitWord)) {
            digit += entry.getValue();
            break outerloop;
          }
        }
      }

      outerloop:
      for (int i = line.length() - 1; i >= 0; i--) {
        if (Character.isDigit(line.charAt(i))) {
          digit += line.charAt(i);
          break;
        }

        for (Map.Entry<String, String> entry : DIGITS.entrySet()) {
          String digitWord = entry.getKey();
          int endIdx = i + 1;
          int startIdx = Math.max(0, endIdx - digitWord.length());
          String target = line.substring(startIdx, endIdx);

          if (target.equals(digitWord)) {
            digit += entry.getValue();
            break outerloop;
          }
        }
      }

      sum += Integer.parseInt(digit);
      line = reader.readLine();
    }

    return sum;
  }
}
