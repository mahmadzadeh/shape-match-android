package com.shapematchandroid;


public class Score {

   private int points;

    public Score(int points) {
        this.points = points;
    }

    public Score add(int pts) {
        return new Score(points += pts);
    }

    public Score deduct(int pts ) {
        return new Score(points -= pts);
    }

    public int points() {
        return points;
    }

}
