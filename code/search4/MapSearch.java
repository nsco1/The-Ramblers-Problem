/**
 * MapSearch.java
 * Phil Green 2013 version
 * Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
 * 
 * search for map traversal
*/

import java.util.*;

public class MapSearch extends Search {

  private Carta map; //map we're searching
  private String goal; //goal city

  public Carta getMap(){
    return map;
  }
  public String getGoal(){
    return goal;
  }

  public MapSearch(Carta m, String g){
    map=m;
    goal=g;
  }
}










