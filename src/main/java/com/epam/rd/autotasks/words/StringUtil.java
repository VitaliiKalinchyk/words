package com.epam.rd.autotasks.words;

import java.util.Arrays;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {

        if (sample==null || words==null || words.length==0)
            return 0;

        int count=0;
        for (String s:words) {
            if (s.trim().equalsIgnoreCase(sample.trim()))
                count++;
        }

        return count;
    }

    public static String[] splitWords(String text) {

        if (text==null || text.length()==0 || text.matches("\\W*"))
            return null;

        return text.replaceAll("\\W"," ").trim().split("\\s+");
    }

    public static String convertPath(String path, boolean toWin) {

        if (returnNull(path)) {
            System.out.println("check");
            return null;
        }

        if (path.matches("\\w*[.]\\w*")
                || (path.contains("/") && (!toWin))
                || path.contains("\\") && (toWin))
            return path;

        if (toWin) {
            if (path.equals("/"))
                return ("C:\\");
            return path.replace("/root","C:\\root")
                    .replace("~", "C:\\User")
                    .replace("/", "\\");
        }

        if (path.equals("C:\\"))
            return ("/");

        return path.replace("C:\\User","~")
                .replace("User","~")
                .replace("C:\\root","/root")
                .replace("C:","/root")
                .replace("\\","/");
    }

    private static boolean returnNull(String path){

        return path == null || path.length()==0
                || (path.contains("/") || path.contains("~")) && (path.contains("\\") || path.contains("C:"))
                || path.length()>1 && path.substring(1).contains("~")
                || path.length()>2 && path.substring(2).contains("C:");
    }

    public static String joinWords(String[] words) {

        if (words==null || words.length==0 || String.join(" ", words).trim().length()==0)
            return null;
        StringBuilder result = new StringBuilder("[");

        for (String s:words) {
            if (s==null || s.length()==0)
                continue;
            result.append(s).append(", ");
        }

        return result.substring(0,result.length()-2)+"]";
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}