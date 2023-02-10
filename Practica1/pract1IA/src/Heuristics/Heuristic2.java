package Heuristics;

import States.Position;
import States.State;

/*
 * Heuristic 1:
 *   distance = (x2 - x1) + (y2 - y1) + height
 **/
public class Heuristic2 implements Heuristic{
    private int type = 2;
    @Override
    public int compare(State st1, State st2){

        Position end = new Position(9,9);

        int dist1 = (end.getRow() - st1.getPosition().getRow()) + (end.getColumn() - st1.getPosition().getColumn()) + st1.getHeight();
        int dist2 = (end.getRow() - st2.getPosition().getRow()) + (end.getColumn() - st2.getPosition().getColumn()) + st2.getHeight();

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
        return (o2.getPosition().getRow() - o1.getPosition().getRow()) + (o2.getPosition().getColumn() - o1.getPosition().getColumn()) + o1.getHeight();
    }

    @Override
    public int getType() {
        return 2;
    }
}
