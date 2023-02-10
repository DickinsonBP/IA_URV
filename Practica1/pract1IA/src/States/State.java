package States;

import Heuristics.Heuristic;

import java.util.ArrayList;

public class State {

    private int height;
    private Position position;
    private ArrayList<State> path;
    private State previous;

    private double f,g;

    private double time;


    /*First State of the map*/
    public State(int height, Position pos){
        this.height = height;
        this.position = pos;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ArrayList<State> getPath() {
        return path;
    }

    //@Override
    /*public String toString() {
        return "State{" +
                "height=" + height +
                ", position=" + position.toString() +
                ", previous=" + previous +
                '}';
    }*/

    public void setPath() {
        if(this.previous.getPath() != null){
            this.path = new ArrayList<>();
            this.path.addAll(this.previous.getPath());
            this.path.add(this.previous);
        }else{
            this.path = new ArrayList<>();
            this.path.add(this.previous);
        }
    }

    public void addStateToPath(State st){
        if(this.path != null){
            this.path.add(st);
        }else{
            this.path = new ArrayList<>();
            this.path.add(this.previous);
        }
    }

    public State getPrevious() {
        return previous;
    }

    public void setPrevious(State previous) {
        this.previous = previous;
    }

    public void calculateG(State ini){
        this.g = (ini.getPosition().getRow() - this.getPosition().getRow()) + (ini.getPosition().getColumn() - this.getPosition().getColumn());
    }

    public double getF(){
        return this.f;
    }
    public void setFirstF(){
        this.f = this.getHeight();
    }
    public void setF(double g, Heuristic h, State end){
        this.f = g + h.checkStates(this, end);
    }

    public void setTime(){
        double diferencia = 0;
        if(this.path.size() == 1){
            diferencia = this.path.get(0).getHeight() - this.height;
            if(diferencia >= 0) this.time = this.time + 1 + diferencia;
            if(diferencia < 0) this.time = this.time + 0.5;
        }
        for(int i = 0; i < this.path.size()-1; i++){
            diferencia = this.path.get(i + 1).getHeight() - this.path.get(i).getHeight();
            if(diferencia >= 0) this.time = this.time + 1 + diferencia;
            if(diferencia < 0) this.time = this.time + 0.5;
        }
    }

    public double getTime(){ return this.time; }

    public void resetTime(){
        this.time = 0;
    }
}
