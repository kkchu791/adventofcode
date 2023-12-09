import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trebuchet {
  public static void main(String[] args) {
    String input_file_path="./part1_input.txt";

    try {
      BufferedReader reader = new BufferedReader(
        new FileReader(input_file_path)
      );

      String line = reader.readLine();
      int sum = 0;

      while (line != null ){
        System.out.println(line);
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
        System.out.println(digit);

        sum += Integer.parseInt(digit);
    
        line = reader.readLine();
      }

      System.out.println(sum);
    } catch(IOException error) {
      error.printStackTrace();
    }   
  }
}
