package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Log {

    private static HashMap<String, Integer> frequency;
    private static HashMap<Integer, String> scars;
    private static HashMap<String, Integer> indexes;
    private static HashMap<String, Integer> cellCount;
    private static ArrayList<String> edges1;
    private static ArrayList<String> edges2;
    private static int number;

    private static final String filePath = "./data/";

    public static void init(String filename) {
        final String path = filePath + filename;
        if (frequency == null && scars == null && edges1 == null && edges2 == null && indexes == null && cellCount == null) {
            number = 0;
            frequency = new HashMap<>();
            scars = new HashMap<>();
            cellCount = new HashMap<>();
            indexes = new HashMap<>();
            edges1 = new ArrayList<>();
            edges2 = new ArrayList<>();
            read(path);
        }
    }

    public static int getNumber() {
        return number;
    }

    public static int getCellCount(int id) {
        return cellCount.get(scars.get(id));
    }

    public static ArrayList<String> getEdges1() {
        return edges1;
    }

    public static ArrayList<String> getEdges2() {
        return edges2;
    }

    public static String getScar(int index) {
        return scars.get(index);
    }

    public static int getId(String scar) {
        return indexes.get(scar);
    }

    public static void clear() {
        scars = null;
        indexes = null;
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
                    if (parameters.length == 5) {
                        String scar1 = parameters[1];
                        String scar2 = parameters[2];
                        int count1 = Integer.parseInt(parameters[3].trim());
                        int count2 = Integer.parseInt(parameters[4].trim());

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

                        cellCount.put(scar1, count1);
                        cellCount.put(scar2, count2);

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
            scars.put(number, id);
            indexes.put(id, number);
            number++;
        }
    }
}
