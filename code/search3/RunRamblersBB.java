
/**
  * RunRamblersBB.java
**/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RunRamblersBB {

  public static void main(String[] arg) throws java.io.IOException {

    TerrainMap tm = new TerrainMap("tmc.pgm"); //map we're searching
    // Coords goal = new Coords(1, 3);

    // RamblersSearch searcher = new RamblersSearch(tm, goal);
    // SearchState initState = (SearchState) new RamblersState(new Coords(14, 9), 0);

    // change from search1 - specify strategy
    // String res_df = searcher.runSearch(initState, "breadthFirst");
    // System.out.println(res_df);
    // String res_bf = searcher.runSearch(initState, "depthFirst");
    // System.out.println(res_bf);

    //String res_bb = searcher.runSearch(initState, "branchAndBound");
    //System.out.println(res_bb);
    Coords[][] startsAndGoals = new Coords[50][2];
    startsAndGoals = readstartsAndGoals("coords16.txt");
  

    ArrayList<Float> effciency = new ArrayList<Float>(); 
    for (int i = 0; i < 50; i++) {
      RamblersSearch searcher = new RamblersSearch(tm, startsAndGoals[i][0]);
      SearchState initState = (SearchState) new RamblersState(startsAndGoals[i][1], 0);
      effciency.add(searcher.runSearchE(initState, "branchAndBound"));
    }

    for (int i = 0; i < 50; i++) {
      System.out.println(startsAndGoals[i][0] +"->"+ startsAndGoals[i][1] +"="+ effciency.get(i));
    }
    System.out.println("Mean: " + effciency.stream().mapToDouble(a->a).average().toString());
  }

  private static Coords[][] readstartsAndGoals(String filename) throws java.io.IOException {
    Coords[][] coords = new Coords[50][2];
   
    Scanner scanner = new Scanner(new File(filename));

    // read pixels
    for (int i = 0; i < 50; ++i) {
      for (int j = 0; j < 2; ++j) {
        int y = (int) scanner.nextInt();
        int x = (int) scanner.nextInt();
        coords[i][j] = new Coords(y, x);
      }
    }
   
    return coords;
  }
}
