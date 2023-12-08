import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    private static List<char[]> matrix = new ArrayList<>();
    private static int[] dirX = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dirY = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {
        String filePath = "inputPuzzle\\day3";
//        String filePath = "inputPuzzle\\day3Test";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                matrix.add(line.toCharArray());
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        System.out.println(logic());
    }

    private static int logic() {
        int res = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).length; j++) {
                char c = matrix.get(i)[j];
//                if (!Character.isDigit(c) && c != 46) {
                if (c == 42) {
                    // found a special character
                    // will travers all directions to get the sum
                    res += getTheSumOfPosition(i, j);
                }
            }
        }
        return res;
    }

    private static int getTheSumOfPosition(int x, int y) {
        List<Integer> gear = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < dirX.length; i++) {
            int neighI = x + dirX[i];
            int neighJ = y + dirY[i];
            if (neighI >= 0 && neighI < matrix.size()
                    && neighJ >= 0 && neighJ < matrix.get(x).length) {
                char c = matrix.get(neighI)[neighJ];
                if (Character.isDigit(c)) {
                    // need to get the number on the line
                    // and sum it to res
                    gear.add(getNumber(neighI, neighJ));
//                    res += getNumber(neighI, neighJ);
                }
            }
        }
        if (gear.size() == 2) {
            return gear.get(0) * gear.get(1);
        }
        return res;
    }

    private static int getNumber(int x, int y) {
        StringBuilder sb = new StringBuilder();
        for (int i = y; i < matrix.get(x).length; i++) {
            char c = matrix.get(x)[i];
            if (!Character.isDigit(c)) {
                break;
            }
            sb.append(c);
            matrix.get(x)[i] = '.';
        }
        for (int i = y - 1; i >= 0; i--) {
            char c = matrix.get(x)[i];
            if (!Character.isDigit(c)) {
                break;
            }
            sb.insert(0, c);
            matrix.get(x)[i] = '.';
        }
        return Integer.parseInt(sb.toString());
    }
}
