package Heuristics;

import java.util.Comparator;
import States.*;

public interface Heuristic extends Comparator<State> {


    @Override
    int compare(State o1, State o2);

    @Override
    boolean equals(Object obj);

    double checkStates(State o1, State o2);

    public int getType();
}
