package model;

import com.sun.org.apache.xerces.internal.xs.ShortList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Log {

    private static HashMap<String, Integer> frequency;
    private static HashMap<Integer, String> log1;
    private static HashMap<String, Integer> log2;
    private static ArrayList<String> edges1;
    private static ArrayList<String> edges2;
    private static int number;

    private static final String filePath = "./data/";

    public static void init(String filename) {
        final String path = filePath + filename;
        if (frequency == null && log1 == null && edges1 == null && edges2 == null && log2 == null) {
            number = 0;
            frequency = new HashMap<>();
            log1 = new HashMap<>();
            log2 = new HashMap<>();
            edges1 = new ArrayList<>();
            edges2 = new ArrayList<>();
            read(path);
        }
    }

    public static int getNumber() {
        return number;
    }

    public static ArrayList<String> getEdges1() {
        return edges1;
    }

    public static ArrayList<String> getEdges2() {
        return edges2;
    }

    public static int getRoot() {
        int count = 0;
        String current = "";
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > count) {
                count = entry.getValue();
                current = entry.getKey();
            }
        }

        if (!current.equals("")) {
            System.out.println("Root: " + current + " with count " + count);
            return log2.get(current);
        }

        return -1;
    }

    public static String getScar(int index) {
        return log1.get(index);
    }
    public static int getId(String scar) {
        return log2.get(scar);
    }

    public static void clear() {
        log1 = null;
        log2 = null;
        frequency = null;
        edges1 = null;
        edges2 = null;
    }


    private static void read(String path) {
        HashSet<String> hashSet = new HashSet<>();
        try {
            File f = new File(path);
            Scanner s = new Scanner(f);
            s.nextLine(); // Skip headers
            while (s.hasNext()) {
                String input = s.nextLine();
                if (!input.isEmpty()) {
                    String[] parameters = input.split(",");
                    if (parameters.length == 3) {
                        String scar1 = parameters[1];
                        String scar2 = parameters[2];

                        if (frequency.get(scar1) == null) {
                            frequency.put(scar1, 1);
                        }

                        if (frequency.get(scar2) == null) {
                            frequency.put(scar2, 1);
                        }

                        if (frequency.get(scar1) != null) {
                            int f1 = frequency.get(scar1);
                            frequency.put(scar1, f1 + 1);
                        }

                        if (frequency.get(scar2) != null) {
                            int f2 = frequency.get(scar2);
                            frequency.put(scar2, f2 + 1);
                        }

                        hashSet.add(scar1);
                        hashSet.add(scar2);

                        edges1.add(scar1);
                        edges2.add(scar2);
                    }
                }
            }
            s.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (String id : hashSet) {
            log1.put(number, id);
            log2.put(id, number);
            number++;
        }
    }
}
