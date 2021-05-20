
/**
  * RunRamblersAstart.java
**/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RunRamblersAstart {

  public static void main(String[] arg) throws java.io.IOException {

    TerrainMap tm = new TerrainMap("diablo.pgm"); //map we're searching
    // Coords goal = new Coords(8, 0);

    // RamblersSearch searcher = new RamblersSearch(tm, goal);
    // SearchState initState = (SearchState) new RamblersState(new Coords(2, 15), 0, 0);

    // change from search1 - specify strategy
    // String res_df = searcher.runSearch(initState, "breadthFirst");
    // System.out.println(res_df);
    // String res_bf = searcher.runSearch(initState, "depthFirst");
    // System.out.println(res_bf);

    //String res_bb = searcher.runSearch(initState, "AStar");
    //System.out.println(res_bb);

    ArrayList<Coords> starts = new ArrayList<Coords>(); 
    for (int i = 0; i < 50; i++) {
      int randomX = (int) (Math.random() * tm.getWidth());
      int randomY = (int) (Math.random() * tm.getDepth());
      starts.add(new Coords(randomX, randomY));
    }

    ArrayList<Coords> goals = new ArrayList<Coords>(); 
    for (int i = 0; i < 50; i++) {
      int randomX = (int) (Math.random() * tm.getWidth());
      int randomY = (int) (Math.random() * tm.getDepth());
      goals.add(new Coords(randomX, randomY));
    }
  
    Coords[][] startsAndGoals = new Coords[50][2];
    for (int i = 0; i < 50; i++) {
      for (int j = 0; j < 2; j++) {
        startsAndGoals[i][j] = j % 2 == 0 ? starts.get(i) : goals.get(i);
      }
    }
    startsAndGoals = readstartsAndGoals("coords.txt");
    //writestartsAndGoals("coords.txt", startsAndGoals);

    ArrayList<Float> effciency = new ArrayList<Float>(); 
    for (int i = 0; i < 50; i++) {
      RamblersSearch searcher = new RamblersSearch(tm, startsAndGoals[i][0]);
      SearchState initState = (SearchState) new RamblersState(startsAndGoals[i][1], 0, 0);
      effciency.add(searcher.runSearchE(initState, "AStar"));
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

  private static void writestartsAndGoals(String filename, Coords[][] coords) throws java.io.IOException {
    FileWriter writer = new FileWriter(filename, true);

    // write pixels
    for (int i = 0; i < 50; ++i) {
      for (int j = 0; j < 2; ++j) {
        writer.write(coords[i][j].gety() + " ");
        writer.write(coords[i][j].getx() + "\n");
        System.out.println("Wrote coords: " + coords[i][j].toString() + " to file");
      }
    }
   writer.close();
  }
}
