import Algorithms.*;
import Heuristics.*;
import States.*;

import java.io.*;
import java.util.ArrayList;


/*
*  Practica 1 Inteligencia Artificial - URV
*   Dickinson Bedoya Perez 10/2022
* */

public class Main {

    private static int MIN_VALUE = -999;

    private static long inicio, fin;
    private static double tiempo;

    /*Color for the path printing*/
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String	ANSI_BLACK = "\u001B[30m";


    public static void main(String[] args) throws Exception {

        State ini = new State(1, new Position(0,0));
        State end = new State(5, new Position(9,9));

        /*Cargar y mostrar matriz formateada*/
        String filepath = "tableros\\tablero3.txt";
        State[][] map = cargarMapaState(filepath);
        mostrarMatriz(map);


        BestFirst bf = new BestFirst();
        bf.bestFirst(map,ini, end, new Heuristic1());

        bf.bestFirst(map,ini, end, new Heuristic2());

        bf.bestFirst(map,ini, end, new Heuristic3());


        Astar as = new Astar();
        as.astar(map,ini, end, new Heuristic1());

        as.astar(map,ini, end, new Heuristic2());

        as.astar(map,ini, end, new Heuristic3());

    }

    private static void mostrarPosiciones(ArrayList<State> path) {
        for(int i = 0; i < path.size(); i++)
            System.out.println(path.get(i).getPosition());
    }

    public static void mostrarMatriz(State[][]map){
        System.out.println("MAPA BASE");
        String data = "";
        for (int x=0; x < 10; x++) {
            System.out.print("|");
            for (int y=0; y < 10; y++) {
                int value = map[x][y].getHeight();
                if(value == MIN_VALUE) data = ANSI_BLACK+"X"+ANSI_RESET;
                else data = String.valueOf(value);
                System.out.print(data);
                if (y!=map[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }

    }
    public static State[][] cargarMapaState(String filepath) throws IOException{
        State[][] map;
        // File path is passed as parameter
        File file = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String[] line;
        map = new State[10][10];
        State state;
        int j = 0;
        while ((st = br.readLine()) != null) {
            line = st.split(" ");
            for(int i = 0; i < line.length; i++){
                int value = Integer.valueOf(line[i]);
                if(value == -1)value = MIN_VALUE;
                state = new State(value, new Position(j,i));
                map[j][i] = state;
            }
            j++;
        }
        return map;
    }
}

