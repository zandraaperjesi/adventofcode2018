import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File inp = new File("src/input.txt");
        Set<Integer> sums = new HashSet<>();
//        int sum = 0;
//        try {
//            while(true) {
//                Scanner scanner = new Scanner(inp);
//                while (scanner.hasNext()) {
//                    Integer i = scanner.nextInt();
//                    sum += i;
//                    if (!sums.add(sum)) {
//                        System.out.println("Found: " + sum);
//                        break;
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Day4 day4 = new Day4();

        Day5 day5 = new Day5();

        day5.second();

    }
}
