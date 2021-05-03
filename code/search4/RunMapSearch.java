
/**
  * RunMapSearch.java
  *
  * Phil Green 2013 version
  * Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
  * 
  * run a map traversal
 */

import java.util.*;

public class RunMapSearch {
  public static void main(String[] arg) {

    Carta map1 = new Carta();
    map1.mapFromFile("map1.csv");
    map1.estsFromFile("map1_ests.csv"); // get the A* estimates
    // System.out.println(map1.toString());
    // System.out.println(map1.getLinks("Start"));

    MapSearch searcher = new MapSearch(map1, "Goal");
    SearchState initState = (SearchState) new MapState("Start", 0, 0);

    // change from search1 - specify strategy

     //System.out.println(res_df);
    // String res_bf = searcher.runSearch(initState, "depthFirst");
    // System.out.println(res_bf);
    String res_bb = searcher.runSearch(initState, "branchAndBound");
    System.out.println(res_bb);

    // String res_astar = searcher.runSearch(initState, "AStar");
    // System.out.println(res_astar);
  }
}
