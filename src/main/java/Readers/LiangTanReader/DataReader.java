package Readers.LiangTanReader;
import java.io.*;
import java.util.*;
import java.util.List;


public class DataReader {
    public String[][] dataArray;
    public HashMap<String, Integer> hmap;
    public String[][] matchArray;
    public Double[][] matchArrayData;
    public List<Match> matches;

    public void WinRateReader (String dataFile) {
        File file = new File(dataFile);
        dataArray = new String[113][113];
        hmap = new HashMap<>();
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
    }

    public void MatchReader (String dataFile, int lines) {
        File file = new File(dataFile);
        matchArray = new String[lines][11];
        matchArrayData = new Double[lines][51];
        matches = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            String word;
            for (int i = 0; i < 12; i++) {   //skip the first title row
                scanner.next();
            }

            int j = 0;
            int endIndex;
            while (scanner.hasNext()) {
                word = scanner.next();
                endIndex = word.length() - 1;
                for (int i = word.length() - 1; i >= 0; i--) {
                    if (word.charAt(i) != ' ') {
                        endIndex = i;
                        break;
                    }
                }
                word = word.substring(0, endIndex + 1);   // make sure the last char in the string is not empty space
                if (j%12 > 0) {
                    matchArray[j/12][j%12 - 1] = word;
                }

                j++;
            }
        }
        catch (Exception e) {
            System.out.print("Error 2 found");
        }

        int rowIndex;
        int colIndex;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < 10; j++) {       //check if all keys can be find in hmap to avoid mistyping in the raw data
                if (hmap.containsKey(matchArray[i][j])) {
                }
                else {
                    System.out.print("Error with " + matchArray[i][j]) ;
                }
            }

            for (int j = 0; j < 5; j++) {
                for (int k = 5; k < 10; k++) {
                    rowIndex = hmap.get(matchArray[i][k]);
                    colIndex = hmap.get(matchArray[i][j]);
                    matchArrayData[i][j * 5 + k - 5] = Double.parseDouble(dataArray[rowIndex][colIndex]);
                }
            }

            for (int j = 5; j < 10; j++) {
                for (int k = 0; k < 5; k++) {
                    rowIndex = hmap.get(matchArray[i][k]);
                    colIndex = hmap.get(matchArray[i][j]);
                    matchArrayData[i][j * 5 + k] = Double.parseDouble(dataArray[rowIndex][colIndex]);
                }
            }

            if (matchArray[i][10].equals("Radiant")) {
                matchArrayData[i][50] = 0.0;
            }
            else {
                matchArrayData[i][50] = 1.0;
            }
        }

        for (int i = 0; i < lines; i++) {
            matches.add(new Match(matchArrayData[i]));
        }

        /*for(int i = 0; i < matches.size(); i++){
            for (int j = 0; j < matches.get(i).getMatch().size(); j++) {
                System.out.print(matches.get(i).getMatch().get(j) + " ");
            }
            System.out.print(matches.get(i).getLabel() + " ");
            System.out.println("\n");
        }*/
    }
}
