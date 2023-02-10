package Algorithms;

import Heuristics.Heuristic;
import States.State;

import java.util.ArrayList;
import java.util.Collections;

public class Astar extends Algorithm{

    public void astar(State[][] map, State ini, State end, Heuristic h){
        ArrayList<State> pending = new ArrayList<State>();
        ArrayList<State> treated = new ArrayList<State>();

        ini.setFirstF(); //start with F == 0
        pending.add(ini); //list ordered by F

        Boolean trobat = false;
        while(!trobat && !pending.isEmpty()){
            State st = pending.get(0); //pick and remove the head
            pending.remove(0);
            //treated.add(st);

            /*End condition*/
            if(st.getPosition().cmp(end.getPosition())){
                trobat = true;
                //st.setTime();
                printResults("A*",st,treated,h,map,trobat);
                st.resetTime();
            }else{
                ArrayList<State> neighbours = succesors(st,map);
                for(int i = 0; i < neighbours.size(); i++){

                    State x = neighbours.get(i);

                    if(!contiene(x, pending) && !contiene(x,treated)){
                        //calculate F
                        x.setTime();
                        x.setF(x.getTime(), h, end);
                        pending.add(x);
                        //Collections.sort(pending,h);
                        pending.sort((obj1, obj2) -> {
                            if(obj1.getF() > obj2.getF()) return 1;
                            if(obj1.getF() < obj2.getF()) return -1;
                            return 0;
                        });
                    }
                }
                treated.add(st);

            }
        }
        printResults("A*",ini,treated,h,map,trobat);
    }
}
