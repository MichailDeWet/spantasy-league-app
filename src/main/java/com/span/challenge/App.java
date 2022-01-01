package com.span.challenge;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    /**
     * Says who finishes where in the league.
     * Scanner will seek user input for a specified output.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        HashMap<String, Team> teams = new HashMap<String, Team>();
        List<Team> table = new ArrayList<Team>();
        Scanner input = new Scanner(System.in);

        printIntro();
        input.nextLine();
        flushTerminal();

        System.out.println("At any stage type \"exit\" to quit program.\n");
        System.out.println("Enter how you would like to input scores:");
        System.out.println("1) Manually");
        System.out.println("2) File");
        System.out.print("\nInput: ");

        int method = input.nextInt();

        try (Scanner scan = inputMethod(input, method)) {
            teams = readResults(scan, teams);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        table = sortTable(teams, table);
        table = positionTable(table);
        flushTerminal();
        printTable(table);
        input.close();
    }

    /**
     * Prints the final table (ordered and positioned).
     * @param table
     */
    private static void printTable(List<Team> table) {
        System.out.println("Final table\n");

        for (Team t : table) {
            System.out.println(t.toString());
        }
    }

    /**
     * User specifies input method;
     * If 1: (Scanner) input is returned.
     * Else If 2: User inputs a file, a new scanner "results" is created. (Scanner) results is returned.
     * Else (Neither 1 nor 2): Method returns null.
     * @param input (Scanner)
     * @param i (int)
     * @return null / Scanner input/results
     * @throws FileNotFoundException
     */
    private static Scanner inputMethod(Scanner input, int i) throws FileNotFoundException {
        switch (i) {
            case 1:
                flushTerminal();
                System.out.println("Please enter scores (type \"end\" to indicate last score):\n");
                return input;
            case 2:
                flushTerminal();
                System.out.println("Please Enter Filename:");
                System.out.print("\nInput (.txt): ");
                InputStream is = App.class.getResourceAsStream("/" +  input.next());
                Scanner results = new Scanner(is);
                return results;
            default:
                return null;
        }
    }

    private static void printIntro() {
        flushTerminal();
        System.out.println("This is a simple implementation of the Coding Challenge set out\n"+
                           "by Span Digital. It is assumed that input is properly formatted,\n"+
                           "set out by the guidelines.\n"+
                           "@Author: Michail Leon De Wet.");
        System.out.print("\nPress enter to continue: ");
    }

    /**
     * Positions final league table formatted according to guidelines.
     * In case of teams tied on points, they take the same position
     * and are arranged in decending order in terms of thier names.
     * Subsequent teams take their position defined by rank (and not
     * the next index).
     * @param table (List<Team>)
     * @return (List<Team>) table
     */
    protected static List<Team> positionTable(List<Team> table) {
        int position = 1;
        int step = 0;
        Team temp = table.get(0);

        for (Team t : table) {
            /** Logic for position numbering. */
            if (t.getPoints() < temp.getPoints()) {
                position += step;
                step = 1;
            } else {
                step++;
            }

            /** Set the position of each team, which is needed in toString() method. */
            t.setPosition(position);
            temp = t;
        }

        return table;
    }

    /**
     * Populates a list with the participating teams.
     * Sorts the list in decending order in term of points earned.
     * @param table (HashMap<String, Team>)
     * @param teams (List<Team>)
     * @return (List<Team>) table
     */
    protected static List<Team> sortTable(HashMap<String, Team> teams, List<Team> table) {
        table.addAll(teams.values());
        table.sort(new TeamComparator());
        return table;
    }

    /**
     * This method is responsible for reading input, 
     * adding teams to the league and interpreting
     * the result of games.
     * @param teams (HashMap<String, Team>)
     * @param input (Scanner)
     * @return HashMap<String, Team> teams
     */
    protected static HashMap<String, Team> readResults(Scanner input, HashMap<String, Team> teams) {
        while (true) {
            /** Break condition (File fully read). */
            if (!input.hasNextLine()) break;

            String s = input.nextLine().trim();

            if (s.equals("")) s = input.nextLine();

            /** Break condition (User finished enterng results). */
            if ("end".equalsIgnoreCase(s)) break;
            /** Terminate condition (User wants to close application). */
            else if("exit".equalsIgnoreCase(s)) {
                flushTerminal();
                System.exit(0);
            }

            String[] home = teamAndResult(true, s);
            String[] away = teamAndResult(false, s);

            /** Add new teams to the league */
            if (!teams.containsKey(home[0])) teams.put(home[0], new Team(home[0]));
            if (!teams.containsKey(away[0])) teams.put(away[0], new Team(away[0]));

            /** Add new teams to the league */
            teams = addPoints(home, away, teams);
        }

        return teams;
    }

    /**
     * Point system:
     *  Win  = 3 points,
     *  Draw = 1 point,
     *  Loss = 0 points
     * @param home (String[]) team and score.
     * @param away (String[]) team and score.
     * @param teams HashMap<String, Team> teams.
     * @return HashMap<String, Team> teams.
     */
    protected static HashMap<String, Team> addPoints(String[] home, String[] away, HashMap<String, Team> teams) {
        if (home[1].compareTo(away[1]) > 0) teams.get(home[0]).win();
        else if (home[1].compareTo(away[1]) == 0) {
            teams.get(home[0]).draw();
            teams.get(away[0]).draw();
        } else teams.get(away[0]).win();

        return teams;
    }

    /**
     * This method takes in a string "home"/"away" the result of their game,
     * and will break the result into elements; Team name and game score.
     * @param side (String) "home"/"away"
     * @param s (String) 
     * @return
     */
    protected static String[] teamAndResult(Boolean isHome, String result) {
        String[] fixture = result.split(", ");;
        String[] array = new String[2];

        if (isHome) Arrays.parallelSetAll(array, (i) -> fixture[0].split("(?<=\\D)(?=\\d)")[i].trim());
        else Arrays.parallelSetAll(array, (i) -> fixture[1].split("(?<=\\D)(?=\\d)")[i].trim());

        return array;
    }

    /**
     * Clears terminal frame. Print a header.
     */
    private static void flushTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.println("" +
        "────────────────────────────────────────────────────────\n" +
        "█▀ █▀█ ▄▀█ █▄░█ ▀█▀ ▄▀█ █▀ █▄█   █░░ █▀▀ ▄▀█ █▀▀ █░█ █▀▀\n" +
        "▄█ █▀▀ █▀█ █░▀█ ░█░ █▀█ ▄█ ░█░   █▄▄ ██▄ █▀█ █▄█ █▄█ ██▄\n" +
        "────────────────────────────────────────────────────────\n");
        System.out.flush();
    }
}