package chap14_collections_and_generics;

import java.util.HashMap;
import java.util.Map;

public class C5Map {

    public static void main(String[] args) {
        Map<String, Integer> gfNrMap = new HashMap<>();
        gfNrMap.put("Ross", 12);
        gfNrMap.put("Chandler", 8);
        gfNrMap.put("Chandler2", 50);
        gfNrMap.put("Chandler3", 60);

        int rossNrOfGfs = gfNrMap.get("Ross");
        System.out.println(rossNrOfGfs);

        gfNrMap.put("Chandler", 9);
        gfNrMap.remove("Ross");

        for (Map.Entry<String, Integer> entry : gfNrMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        for (String key : gfNrMap.keySet()) {
            System.out.println(key + ": " + gfNrMap.get(key));
        }

        for (Integer value : gfNrMap.values()) {
            System.out.println(value);
        }
    }
}