import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class textAnalysis {
    public static void main(String[] args) throws IOException {
        String filename = "D:/Java_laba/lenin_laba/lenin.txt";
        List<String> words = getWords(filename);
        LinkedHashSet<String> x = new First().withoutCapital(words);
        new First().decreasingLength(x);
        new Second().countUniqueWords(words);
    }
    private static List<String> getWords(String filename) throws IOException{
        var splitter = Pattern.compile("[\\p{Punct}\\d\\s«…»–]+");
        return Files.lines(Path.of(filename))
                    .flatMap(splitter::splitAsStream)
                    .filter(w -> ! w.isEmpty())
                    .collect(Collectors.toList());
    }
    static class First {
        LinkedHashSet<String> withoutCapital(List<String> words) throws IOException {
            LinkedHashSet<String> strings = new LinkedHashSet<>();
            //String file_output = "D:/Java_laba/lenin_laba/out.txt";
            for (String a : words) {
                String end = a.toLowerCase(Locale.getDefault());
                strings.add(end);
            }
            //System.out.println(strings); выводить в файл
            return strings;
        }

        void decreasingLength(LinkedHashSet<String> strings) {
            //SortedSet<String> sortedSet = new TreeSet<>(String::length);
            SortedSet<String> sortedSet = new TreeSet<>(String::compareToIgnoreCase);//компаратор который соритрует в лексикограф.порядке
            sortedSet.addAll(strings);
            //вывести в файл
            System.out.println(sortedSet);
        }
        void decreasingLengthLimited(SortedSet<String> sortedSet){
            TreeSet<String> sortedSet2 = new TreeSet<>();
            //TreeSet<String> sortedSet1 = new TreeSet<String>(Comparator.comparing(String::length));
            for (String i : sortedSet){
                if (i.length()>=4&&i.length()<=7) sortedSet2.add(i);
            }
        }
    }
}
class Second{
    void countUniqueWords(List<String> words){

    }
}


