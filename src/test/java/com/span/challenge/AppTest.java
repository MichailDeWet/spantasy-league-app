package com.span.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

/**
 * Unit tests for App.java
 */
public class AppTest {
    /**
     * Simple test.
     */
    @Test
    public void testApp() {
        assertEquals(1, 1);
    }

    /**
     * Make sure points system is functioning.
     */
    @Test
    public void testAddPoints_HomeWin() {
        HashMap<String, Team> teams = new HashMap<String, Team>();
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        teams.put("Team A", teamA);
        teams.put("Team B", teamB);
        String[] home = {"Team A", "1"};
        String[] away = {"Team B", "0"};

        teams = App.addPoints(home, away, teams);
        assertEquals(teamA.getPoints(), 3);
        assertEquals(teamB.getPoints(), 0);
    }

    @Test
    public void testAddPoints_Draw() {
        HashMap<String, Team> teams = new HashMap<String, Team>();
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        teams.put("Team A", teamA);
        teams.put("Team B", teamB);
        String[] home = {"Team A", "1"};
        String[] away = {"Team B", "1"};

        teams = App.addPoints(home, away, teams);
        assertEquals(teamA.getPoints(), 1);
        assertEquals(teamB.getPoints(), 1);
    }

    @Test
    public void testAddPoints_HomeLoss() {
        HashMap<String, Team> teams = new HashMap<String, Team>();
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        teams.put("Team A", teamA);
        teams.put("Team B", teamB);
        String[] home = {"Team A", "1"};
        String[] away = {"Team B", "2"};

        teams = App.addPoints(home, away, teams);
        assertEquals(teamA.getPoints(), 0);
        assertEquals(teamB.getPoints(), 3);
    }

    /**
     * Make sure result is broken down correctly.
     */
    @Test
    public void testTeamAndResult_isHome() {
        Boolean isHome = true;
        String result = "Team A 1, Team B 0";
        String[] home = App.teamAndResult(isHome, result);

        assertEquals(home[0], "Team A");
        assertEquals(home[1], "1");
    }

    @Test
    public void testTeamAndResult_isNotHome() {
        Boolean isHome = false;
        String result = "Team A 1, Team B 0";
        String[] away = App.teamAndResult(isHome, result);

        assertEquals(away[0], "Team B");
        assertEquals(away[1], "0");
    }

    /**
     * Full test for sortTable, postionTable, and toString() method.
     */
    @Test
    public void testSortAndPostionTable_EqualPoints() {
        List<Team> table = new ArrayList<Team>();
        HashMap<String, Team> teams = new HashMap<String, Team>();
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        teams.put("Team A", teamA);
        teams.put("Team B", teamB);
        teamA.draw();
        teamB.draw();
        table = App.sortTable(teams, table);

        /** Check size. */
        assertEquals(table.size(), 2);

        /** Check both teams in table. */
        assertTrue(table.contains(teamA));
        assertTrue(table.contains(teamB));

        /** Check teamA (equal points, alphabetic preference) is first. */
        assertEquals(table.get(0), teamA);

        table = App.positionTable(table);

        /** Check for correct position and toString() method. */
        assertEquals(table.get(0).toString(), "1. Team A, 1 pt");
        assertEquals(table.get(1).toString(), "1. Team B, 1 pt");
    }

    @Test
    public void testSortAndPostionTable_NotEqualPoints() {
        List<Team> table = new ArrayList<Team>();
        HashMap<String, Team> teams = new HashMap<String, Team>();
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        teams.put("Team A", teamA);
        teams.put("Team B", teamB);
        teamB.win();
        table = App.sortTable(teams, table);

        /** Check size. */
        assertEquals(table.size(), 2);

        /** Check both teams in table. */
        assertTrue(table.contains(teamA));
        assertTrue(table.contains(teamB));

        /** Check teamB (winner) is first. */
        assertEquals(table.get(0), teamB);

        table = App.positionTable(table);

        /** Check for correct position and toString() method. */
        assertEquals(table.get(0).toString(), "1. Team B, 3 pts");
        assertEquals(table.get(1).toString(), "2. Team A, 0 pts");
    }
}
