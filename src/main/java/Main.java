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

        Day6 day6 = new Day6();

        Day7 day7 = new Day7();

        Day8 day8 = new Day8();

        Day9 day9 = new Day9();

        Day10 day10 = new Day10();

        Day11 day11 = new Day11();

        Day12 day12 = new Day12();
        day12.applyPattern();

    }
}
