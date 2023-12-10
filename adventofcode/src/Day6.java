import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Day6 {
    public static void main(String[] args) {
        //        String filePath = "inputPuzzle\\day6";
        String filePath = "inputPuzzle\\day6Test";
        String line;
        AtomicLong beatRecord = new AtomicLong(1);
        Map<Long, Long> timeDistance = new HashMap<>();
//        timeDistance.put(7, 9);
//        timeDistance.put(15, 40);
//        timeDistance.put(30, 200);

        List<Integer> res = new ArrayList<>();
        timeDistance.put(57726992L, 291117211762026L);
//        timeDistance.put(72,1172);
//        timeDistance.put(69,1176);
//        timeDistance.put(92,2026);
        timeDistance.forEach((time, distance) -> {
            int tempRecord = 0;
            // (distance - i) * i <= distance
            for (long holdTime = time / 2; distance < (time - holdTime) * holdTime; holdTime--) {
                long travelTime = time - holdTime;
                long travelDist = holdTime * travelTime;
                if (travelDist > distance) {
                    tempRecord++;
                }
            }
            tempRecord *= 2;
            if (time % 2 == 0) {
                tempRecord -= 1;
            }
            res.add(tempRecord);
        });

        System.out.println(res.stream()
                .reduce(1, (a, b) -> a * b));
    }
}
