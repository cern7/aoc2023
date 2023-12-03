import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    private static final String[] VALUES = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "inputPuzzle\\day1";
//        String filePath = "inputPuzzle\\day1Test";
        int res = 0;
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                int firstDigit = -1;
                int lastDigit = -1;

                for (int i = 0; i < line.length(); i++) {
                    firstDigit = getNumber(line, i);
                    if (firstDigit != -1) {
                        break;
                    }
                }
                for (int i = line.length() - 1; i > -1; i--) {
                    lastDigit = getNumber(line, i);
                    if (lastDigit != -1) {
                        break;
                    }
                }

                res += (firstDigit * 10) + lastDigit;
                System.out.println(res);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }

    private static int getNumber(String line, int pos) {
        char c = line.charAt(pos);
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        for (int i = 0; i < VALUES.length; i++) {
            String value = VALUES[i];
            if (line.length() >= value.length() + pos
                    && line.startsWith(value, pos)) {
                return i + 1;
            }
        }
        return -1;
    }
}
