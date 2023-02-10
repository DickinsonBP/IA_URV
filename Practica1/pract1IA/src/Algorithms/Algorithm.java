package Algorithms;

import Heuristics.Heuristic;
import States.Position;
import States.State;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Algorithm {
    /*Color for the path printing*/
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String	ANSI_BLACK = "\u001B[30m";
    public Algorithm(){}

    public void test(){
        System.out.println("Hola desde Algoritmo padre");
    }

    public Boolean contiene(State state,ArrayList<State> arr){

        for(int i=0; i < arr.size(); i++){
            if(arr.get(i).getPosition() == state.getPosition()) return true;
        }
        return false;
    }
    public ArrayList<State> succesors(State st, State[][] matriz){

        //pos 0 --> up
        //pos 1 --> down
        //pos 2 --> left
        //pos 3 --> right
        ArrayList<State> states = new ArrayList<>();

        //State position
        int x = st.getPosition().getRow();
        int y = st.getPosition().getColumn();

        State state;

        //first case, the state is in the top row (=0), don't look up
        if((x == 0) && (y < 9) && !fall(matriz[x][y + 1])){
            state = matriz[x][y + 1];
            state.setPrevious(st);
            state.setPath();
            states.add(state); //look down
        }


        //second case, the state is in the bottom row(=9), don't look down
        if((x == 9) && (y > 0) && !fall(matriz[x][y - 1])){
            state = matriz[x][y - 1];
            state.setPrevious(st);
            state.setPath();
            states.add(state); //look up
        }


        //third case, the state is in the left column (=0), don't look left
        if((y == 0) && (x < 9 ) && !fall(matriz[x + 1][y])){
            state = matriz[x + 1][y];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }


        //third case, the state is in the right column (=9), don't look right
        if((y == 9) && (x > 9) && !fall(matriz[x - 1][y])){
            state = matriz[x - 1][y];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }



        //last case, the state is in other position
        if(x < 9 && !fall(matriz[x + 1][y]) && !states.contains(matriz[x + 1][y])){
            state = matriz[x + 1][y];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }
        if(x > 0 && !fall(matriz[x - 1][y]) && !states.contains(matriz[x - 1][y])){
            state = matriz[x - 1][y];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }
        if(y < 9 && !fall(matriz[x][y + 1]) && !states.contains(matriz[x][y+1])){
            state = matriz[x][y + 1];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }
        if(y > 9 && !fall(matriz[x][y - 1]) && !states.contains(matriz[x][y - 1])){
            state = matriz[x][y - 1];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }

        return states;
    }

    private Boolean fall(State st){
        if(st.getHeight() <= -999) return true;
        else return false;
    }

    public static void printResults(String algoritmo, State st, ArrayList<State> treated, Heuristic h, State[][] map, Boolean trobat){
        System.out.println("\n\nAlgoritmo " + algoritmo + " con Heuristica Nº" + h.getType());
        System.out.println("Numero de nodos tratados: " + treated.size());
        System.out.println("Coste: " + st.getTime());
        if(trobat) mostrarCamino(st.getPath(), map);
        else System.out.println("No se ha podidio encontrar el camino");
    }

    public static void mostrarCamino(ArrayList<State> path, State[][] map){
        if(path != null){
            System.out.println("Camino: ");
            String data = "";
            for (int x=0; x < 10; x++) {
                System.out.print("|");
                for (int y=0; y < 10; y++) {

                    int value = map[x][y].getHeight();
                    if(value == -999) data = ANSI_BLACK+"X"+ANSI_RESET;
                    else data = String.valueOf(value);



                    State position = checkPosition(path, map[x][y]);

                    if(x == 0 && y == 0){
                        System.out.print(ANSI_BLUE+data+ANSI_RESET);
                    } else if (position != null) {
                        System.out.print(ANSI_BLUE+data+ANSI_RESET);
                    }else System.out.print(data);

                    if (y!=map[x].length-1) System.out.print("\t");
                }
                System.out.println("|");
            }

        }else System.out.println("No hay camino!");
    }

    public static State checkPosition(ArrayList<State> path, State state){
        State st = null;
        for(int i = 0; i < path.size(); i++){

            if(path.get(i).getPosition() == state.getPosition()){
                st = path.get(i);
                break;
            }
        }
        return st;
    }
}
