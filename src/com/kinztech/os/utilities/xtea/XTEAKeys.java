package com.kinztech.os.utilities.xtea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class XTEAKeys {

    private static HashMap<Integer,Integer[]> xteaKeysMap = new HashMap<>();

    public static void initialize() {
        try {
            File directory = new File("./resources/xtea/");
            for (File file : directory.listFiles()) {
                int regionID = Integer.valueOf(file.getName().substring(0,file.getName().lastIndexOf('.')));
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = "";
                ArrayList<Integer> xteaList = new ArrayList<>();
                while((line = reader.readLine()) != null) {
                    xteaList.add(Integer.valueOf(line));
                }
                Integer[] xteaArray = xteaList.toArray(new Integer[xteaList.size()]);
                xteaKeysMap.put(regionID, xteaArray);
            }
            System.out.println("Xtea region Count: " + xteaKeysMap.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer[] get(int regionId) {
        return xteaKeysMap.get(regionId);
    }

}
