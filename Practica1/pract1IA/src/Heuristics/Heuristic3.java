package Heuristics;

import States.Position;
import States.State;


/*
* Heuristic 3:
*
*   distance = sqrt(((x2 - x1)^2) + ((y2 - y1)^2))) + height
* */

public class Heuristic3 implements Heuristic{
    @Override
    public int compare(State o1, State o2) {

        Position end = new Position(9,9);

        double dist1 = Math.sqrt(Math.pow(end.getRow() - o1.getPosition().getRow(),2) + Math.pow(end.getColumn() - o1.getPosition().getColumn(),2)) + o1.getHeight();
        double dist2 = Math.sqrt(Math.pow(end.getRow() - o2.getPosition().getRow(),2) + Math.pow(end.getColumn() - o2.getPosition().getColumn(),2)) + o2.getHeight();

        if(dist1 > dist2){
            return 1;
        }
        if(dist1 < dist2){
            return -1;
        }
        return 0;
    }

    @Override
    public double checkStates(State o1, State o2) {
        return Math.sqrt(Math.pow(o2.getPosition().getRow() - o1.getPosition().getRow(),2) + Math.pow(o2.getPosition().getColumn() - o1.getPosition().getColumn(),2)) + o1.getHeight();
    }
    @Override
    public int getType() {
        return 3;
    }
}
