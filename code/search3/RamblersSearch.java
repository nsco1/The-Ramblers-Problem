import java.util.*;

public class RamblersSearch extends Search {

    private TerrainMap tm = new TerrainMap("tmc.pgm"); //map we're searching
    private Coords goal; //goal coords

    public TerrainMap getMap(){
        return tm;
    }
    public Coords getGoal(){
        return goal;
    }

    public RamblersSearch(TerrainMap m, Coords g){
        tm=m;
        goal=g;
    }
}




