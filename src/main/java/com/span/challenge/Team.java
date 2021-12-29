package com.span.challenge;

public class Team {
    private String name;
    private int points;
    private int position;

    public Team(String name) {
        this.name = name;
        this.points = 0;
        this.position = 0;
    }

    public void win() {
        this.points += 3;
    }

    public void draw() {
        this.points += 1;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        if (points == 1) {
            return String.format("%d. %s, %d pt", position, name, points);
        } else {
            return String.format("%d. %s, %d pts", position, name, points);
        }
    }
}
