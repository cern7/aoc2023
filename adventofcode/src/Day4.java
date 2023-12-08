import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day4 {
    private static int result = 0;

    public static void main(String[] args) {
        String filePath = "inputPuzzle\\day4";
//        String filePath = "inputPuzzle\\day4Test";
        String line;


        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int[] cardCopies = new int[196];
            Arrays.fill(cardCopies, 1);
            while ((line = br.readLine()) != null) {
                int cardNumber = Integer.parseInt(
                        line.split(":")[0].split("Card")[1].trim());
                boolean[] winningNumbers = new boolean[101];
                Arrays.fill(winningNumbers, false);
                Arrays.stream(line.split(":")[1]
                                .split("\\|")[0]
                                .split(" "))
                        .filter(str -> str.matches("\\d+"))
//                        .toArray(String[]::new);
                        .forEach(x -> winningNumbers[Integer.parseInt(x)] = true);

                String[] haveNumbers = Arrays.stream(line.split(":")[1]
                                .split("\\|")[1]
                                .split(" "))
                        .filter(str -> str.matches("\\d+"))
                        .toArray(String[]::new);
                int matches = 0;
                for (int i = 0; i < haveNumbers.length; i++) {
                    if (winningNumbers[Integer.parseInt(haveNumbers[i])]) {
                        matches++;
                    }
                }

                if (matches > 0) {
                    for (int i = 0; i < cardCopies[cardNumber - 1]; i++) {
                        for (int j = 0; j < matches; j++) {
                            if (cardNumber + j < 197) {
                                cardCopies[cardNumber + j]++;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            result = Arrays.stream(cardCopies)
                    .reduce(0, Integer::sum);
//                        .reduce(0, (a, b) -> a + b);
            System.out.println(result);

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
