package Algorithms;

import States.*;
import Heuristics.*;

import javax.swing.undo.StateEdit;
import java.util.*;

public class BestFirst extends Algorithm {


    public void bestFirst(State[][] map, State ini, State end, Heuristic h){
        ArrayList<State> pending = new ArrayList<State>();
        ArrayList<State> treated = new ArrayList<State>();

        pending.add(ini);

        Boolean trobat = false;

        while(!trobat && !pending.isEmpty()){

            State st = pending.get(0); //pick and remove the head
            pending.remove(0);
            //treated.add(st);

            /*End condition*/
            if(st.getPosition().cmp(end.getPosition())){
                trobat = true;
                st.setTime();
                printResults("Best First",st,treated,h,map,trobat);
                st.resetTime();
            }else{
                ArrayList<State> neighbours = succesors(st,map);
                for(int i = 0; i < neighbours.size(); i++){
                    if(!contiene(neighbours.get(i), pending) && !contiene(neighbours.get(i),treated)){
                        pending.add(neighbours.get(i));
                        Collections.sort(pending,h);
                    }
                }
                treated.add(st);

            }
        }
        printResults("Best First",ini,treated,h,map,trobat);
    }

}
