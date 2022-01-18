package com.span.challenge;

import java.util.Comparator;

final class TeamComparator implements Comparator<Team> {
    /**
     * Comparison format follows:
     * 1) Primary measure   -> Points.
     *  1.1) Decending order.
     *  1.2) If equal, (2) Takes preference.
     * 2) Secondary measure -> Team name.
     *  2.1) Ascending order.
     */
    @Override
    public int compare(Team t1, Team t2) {
        if (t1.getPoints() == t2.getPoints()) {
            return t1.getName().compareTo(t2.getName());
        } else if (t1.getPoints() > t2.getPoints()) {
            return -1;
        }
        
        return 1;
    }
}
