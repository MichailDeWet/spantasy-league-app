package com.span.challenge;

import java.util.Comparator;

public class TeamComparator implements Comparator {
    /**
     * Comparison format follows:
     * 1) Primary measure   -> Points.
     *  1.1) Decending order.
     *  1.2) If equal, (2) Takes preference.
     * 2) Secondary measure -> Team name.
     *  2.1) Ascending order.
     */
    @Override
    public int compare(Object o1, Object o2) {
        Team t1 = (Team) o2;  
        Team t2 = (Team) o1;  
  
        if(t1.getPoints() == t2.getPoints()) return t2.getName().compareTo(t1.getName());
        else if(t1.getPoints() > t2.getPoints()) return 1;  
        else return -1;
    }
}
