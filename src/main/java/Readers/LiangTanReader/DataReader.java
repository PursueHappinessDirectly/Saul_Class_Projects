package com.company;

import java.io.*;
import java.util.*;

/**
 * Created by LiangTan on 10/3/16.
 */
public class DataReader {
    public String[][] dataArray;
    public HashMap<String, Integer> hmap = new HashMap<String, Integer>();
    public String[][] matchArray;
    public void WinRateReader (String dataFile) {
        File file = new File("/Users/LiangTan/Documents/Machine Learning/" + dataFile);
        dataArray = new String[113][113];
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\"");
            String word;
            for (int i = 0; i < 113; i++) {
                int j = 0;
                while (scanner.hasNext() && j < 226) {
                    word = scanner.next();
                    if (j%2 == 0) {
                        dataArray[i][j / 2] = word;
                    }
                    j++;
                }

            }
        }
        catch (Exception e) {
            System.out.print("Error 1 found");
        }

        for (int i = 1; i < 113; i++) {
            hmap.put(dataArray[0][i], i);
        }


        return;
    }

    public void MatchReader (String dataFile) {
        File file = new File("/Users/LiangTan/Documents/Machine Learning/" + dataFile);
        matchArray = new String[296][11];
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            String word;

            for (int i = 0; i < 12; i++) {
                word = scanner.next();
            }

            int j = 0;
            while (scanner.hasNext()) {
                word = scanner.next();
                if (j%12 > 0) {
                    matchArray[j/12][j%12 - 1] = word;
                }
                j++;
            }
        }
        catch (Exception e) {
            System.out.print("Error 2 found");
        }

    }
}
