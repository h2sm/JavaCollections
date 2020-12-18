import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class textAnalysis {
    public static void main(String[] args) throws IOException {
        new Lenin();//а где старый
        List<String> words = Lenin.getWords();
        LinkedHashSet<String> x = new First().withoutCapital(words);
        //new First().decreasingLength(x);
        new Second();
        TreeMap<String,Integer> treeMap = Second.countUniqueWordsButMap(words);
        TreeMap<String, Integer> trmap = Second.countUniqueWordsButMapLimited(treeMap);
        Second.countUniqueWordsButMapLimitedLexicographical(trmap);
    }
}
class First {
    LinkedHashSet<String> withoutCapital(List<String> words) throws IOException {
        LinkedHashSet<String> strings = new LinkedHashSet<>();
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
        TreeSet<String> sortedSet1 = new TreeSet<String>(Comparator.comparing(as -> as.length()));
        for (String i : sortedSet){
            if (i.length()>=4&&i.length()<=7) sortedSet2.add(i);
        }
    }
}

class Second{
    void countUniqueWords(List<String> words){
        Set<String> wordsSet = new HashSet<String>(words);
        for(String s: wordsSet){
          System.out.println(s + " " + Collections.frequency(wordsSet,s));
        }
    }
    static TreeMap<String,Integer> countUniqueWordsButMap(List<String> words){//2а
        TreeMap<String,Integer> treeMap = new TreeMap<>();//слово - количество использований, treeset!!
        for (String s:words){
            if (!treeMap.containsKey(s)) treeMap.put(s,Collections.frequency(words,s));
            //System.out.println(treeMap);
        }
        return treeMap;
    }
    static TreeMap<String, Integer> countUniqueWordsButMapLimited(TreeMap<String,Integer>treeMap){//от 4 до 7
        TreeMap<String,Integer> treeMap1 = new TreeMap<>();
        for (int i = 0; i <treeMap.size() ; i++) {
            String key = (String) treeMap.keySet().toArray()[i];
            Integer value = treeMap1.get(i);
            if (key.length()>=4 &&key.length()<=7) treeMap1.put(key,value);
        }
        return treeMap1;
    }
    static void countUniqueWordsButMapLimitedLexicographical(TreeMap<String,Integer>treeMap){
        //SortedMap<String, Integer> sortedMap = new SortedMap<String, Integer>();
        TreeMap<String,Integer> treeMap1 = new TreeMap<>(String::compareToIgnoreCase);
    }
}
class Three{
    void mapping(List<String> words){
        
    }
}
class Lenin {

    static List<String> getWords() throws IOException {
        String filename = "D:/Java_laba/lenin_laba/lenin.txt";
        var splitter = Pattern.compile("[\\p{Punct}\\d\\s«…»–]+");
        return Files.lines(Path.of(filename))
                .flatMap(splitter::splitAsStream)
                .filter(w -> ! w.isEmpty())
                .collect(Collectors.toList());
    }

    static void writeResultToFile(String filename, List<String> lines) throws IOException {
        Files.write(Path.of(filename), lines);
    }
}


