package jianmin.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Pattern;


public final class Words {
    private Words() {}
    private static ArrayList<String> readAll() throws IOException {
        Pattern pattern = Pattern.compile("[\\s]+");
        ArrayList<String> words = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() == 0) { continue; }
            String[] lineWords = pattern.split(line.trim());
            words.addAll(Arrays.asList(lineWords));
        }
        return words;
    }
    private static HashMap<String, Integer> makeHistogram(ArrayList<String> words) {
        HashMap<String, Integer> histo = new HashMap<>();
        for (String word: words) {
            if (histo.containsKey(word)) {
                histo.put(word, histo.get(word) + 1);
            } else {
                histo.put(word, 1);
            }
        }
        return histo;
    }
    private static void printHistogram(HashMap<String, Integer> histo) {
        ArrayList<String> keys = new ArrayList<>(histo.keySet());
        Collections.sort(keys);
        for (String word: keys) {
            System.out.printf("%s %d\n", word, histo.get(word));
        }
    }
    public static void main(String[] args) throws IOException {
        ArrayList<String> words = readAll();
        HashMap<String, Integer> histo = makeHistogram(words);
        printHistogram(histo);
    }
}