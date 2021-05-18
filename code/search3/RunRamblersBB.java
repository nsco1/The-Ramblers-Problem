
/**
  * RunRamblersBB.java
**/

import java.util.*;

public class RunRamblersBB {

  public static void main(String[] arg) {

    TerrainMap tm = new TerrainMap("tmc.pgm"); //map we're searching
    Coords goal = new Coords(12, 11);

    RamblersSearch searcher = new RamblersSearch(tm, goal);
    SearchState initState = (SearchState) new RamblersState(new Coords(13, 5), 0);

    // change from search1 - specify strategy
    // String res_df = searcher.runSearch(initState, "breadthFirst");
    // System.out.println(res_df);
    // String res_bf = searcher.runSearch(initState, "depthFirst");
    // System.out.println(res_bf);

    String res_bb = searcher.runSearch(initState, "branchAndBound");
    System.out.println(res_bb);
  }
}
