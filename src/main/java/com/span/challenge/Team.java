package com.span.challenge;

final class Team {
    private String name;
    private int points;
    private int position;

    Team(String name) {
        this.name = name;
        this.points = 0;
        this.position = 0;
    }

    void win() {
        this.points += 3;
    }

    void draw() {
        this.points += 1;
    }

    int getPoints() {
        return points;
    }

    String getName() {
        return name;
    }

    void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        String format = String.format("%d. %s, %d pt", position, name, points);

        if (points != 1) {
            return format += "s";
        }

        return format;
    }
}
