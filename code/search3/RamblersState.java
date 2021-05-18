import java.util.*;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

//import jdk.jshell.resources.l10n_zh_CN;

public class RamblersState extends SearchState{

    //co-ordinates for this state
    private Coords coords;

    //constructor
    //A* - has estRemCost now
    public RamblersState(Coords coord, int lc){
        coords=coord;
        localCost=lc;
        //estRemCost=rc;
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
        // ArrayList<MapLink> links = map.getLinks(coords);
        ArrayList<SearchState> succs = new ArrayList();

        ArrayList<Coords> links = new ArrayList<Coords>() {
            {
                if ((coords.getx() - 1) > 0) {
                    add(new Coords(coords.gety(), coords.getx() - 1));
                }
                if ((coords.getx() + 1) < tMap.getWidth()) {
                    add(new Coords(coords.gety(), coords.getx() + 1));
                }
                if ((coords.gety() - 1) > 0) {
                    add(new Coords(coords.gety() - 1, coords.getx()));
                }
                if ((coords.gety() + 1) < tMap.getHeight()) {
                    add(new Coords(coords.gety() + 1, coords.getx()));
                }
            }
        }; 
        
        // loop over the links from my city
        for (Coords l: links){
            //System.out.println(l.toString());
            int height = map[coords.gety()][coords.getx()];
            int sHeight = map[l.gety()][l.getx()];
            int cost = sHeight <= height ? 1 : 1 + Math.abs(sHeight - height);

            succs.add(new RamblersState(l, cost));
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



