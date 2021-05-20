import java.util.*;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

//import jdk.jshell.resources.l10n_zh_CN;

public class RamblersState extends SearchState{

    //co-ordinates for this state
    private Coords coords;

    //constructor
    //A* - has estRemCost now
    public RamblersState(Coords coord, int lc, int rc){
        coords=coord;
        localCost=lc;
        estRemCost=rc;
    }
    //accessors
    public Coords getCoords(){
        return coords;
    }


    // goalPredicate
    public boolean goalPredicate(Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch) searcher;
        Coords tar=rsearcher.getGoal(); // get target coord
        // return true if equal
        return (coords.gety() == tar.gety() && coords.getx()== tar.getx());
    }

    // getSuccessors
    public ArrayList<SearchState> getSuccessors (Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch) searcher;
        TerrainMap tMap = rsearcher.getMap();
        int[][] map = rsearcher.getMap().getTmap();
        Coords goal = rsearcher.getGoal();
        // ArrayList<MapLink> links = map.getLinks(coords);
        ArrayList<SearchState> succs = new ArrayList();

        ArrayList<Coords> links = new ArrayList<Coords>() {
            {
                if ((coords.getx() - 1) >= 0) {
                    add(new Coords(coords.gety(), coords.getx() - 1));
                }
                if ((coords.getx() + 1) < tMap.getWidth()) {
                    add(new Coords(coords.gety(), coords.getx() + 1));
                }
                if ((coords.gety() - 1) >= 0) {
                    add(new Coords(coords.gety() - 1, coords.getx()));
                }
                if ((coords.gety() + 1) < tMap.getDepth()) {
                    add(new Coords(coords.gety() + 1, coords.getx()));
                }
            }
        }; 
      
        // loop over the possible moves
        for (Coords l: links){
            // Local Cost
            int height = map[coords.gety()][coords.getx()];
            int sHeight = map[l.gety()][l.getx()];
            int cost = sHeight <= height ? 1 : 1 + Math.abs(sHeight - height);
            
            // A* Calculations
            double deltaX = Math.abs(l.getx() - goal.getx());
            double deltaY = Math.abs(l.gety() - goal.gety());
            double deltaH = Math.abs(map[l.gety()][l.getx()] - map[goal.gety()][goal.getx()]);
            double estRemCostMan = deltaX + deltaY + deltaH; // Manhattan
            // double estRemCostEuc = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaH*deltaH); // Euclidean
            // double noDiagSteps = Math.min(deltaX, Math.min(deltaY, deltaH));
            // double noStrtSteps = estRemCostMan - 2*noDiagSteps;
            // double estRemCost = noStrtSteps + Math.sqrt(3) * noDiagSteps;
            
            succs.add(new RamblersState(l, cost, (int)(cost * estRemCostMan)));
        }
        return succs;
    }

    // sameState
    public boolean sameState(SearchState s2) {
        RamblersState ms2= (RamblersState) s2;
        return (coords.gety() == ms2.getCoords().gety() && coords.getx()== ms2.getCoords().getx());
    }

    // toString
    public String toString () {
        return ("Ramblers State: "+ coords);
    }
}



