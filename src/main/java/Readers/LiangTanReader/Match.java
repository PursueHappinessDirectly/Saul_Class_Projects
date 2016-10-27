package Readers.LiangTanReader;

/**
 * Created by LiangTan on 10/24/16.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by LiangTan on 10/20/16.
 */
public class Match {
    private String result;
    private List<Double> match;

    public Match(Double[] matchArrayData) {
        match = new ArrayList<>();
        for (int i = 0; i < matchArrayData.length - 1; i++) {
            match.add(matchArrayData[i]);
        }
        if (matchArrayData[matchArrayData.length - 1] == 0.0) {
            result = "Radiant";
        }
        else {
            result = "Dire";
        }
    }

    public String getLabel() {
        return result;
    }

    public List<Double> getMatch() {
        return Collections.unmodifiableList(match);
    }



}
