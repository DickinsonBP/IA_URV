package Heuristics;
import States.*;
import static java.lang.Math.*;

/*
* Heuristic 1:
*   Look at which of the two states has less weight
* */
public class Heuristic1 implements Heuristic {
    private int type = 1;
    @Override
    public int compare(State st1, State st2){

        if(st1.getHeight() > st2.getHeight()){
            return 1;
        }
        if(st1.getHeight() < st2.getHeight()){
            return -1;
        }
        return 0;
    }

    @Override
    public double checkStates(State o1, State o2) {
        return o1.getHeight();
    }

    @Override
    public int getType() {
        return 1;
    }


}
