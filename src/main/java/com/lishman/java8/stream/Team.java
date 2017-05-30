package com.lishman.java8.stream;

import lombok.Builder;
import lombok.ToString;

@ToString
final class Team {

    final public String name;
    final public String group;
    final public int position;
    final public int played;
    final public int won;
    final public int drawn;
    final public int lost;
    final public int goalsFor;
    final public int goalsAgainst;
    final public int goalDifference;
    final public int points;

    @Builder
    Team(final String teamCsv) {
        String[] col = teamCsv.split(",");
        group = col[0];
        position = Integer.parseInt(col[1]);
        name = col[2];
        played = Integer.parseInt(col[3]);
        won = Integer.parseInt(col[4]);
        drawn = Integer.parseInt(col[4]);
        lost = Integer.parseInt(col[5]);
        goalsFor = Integer.parseInt(col[6]);
        goalsAgainst = Integer.parseInt(col[7]);
        goalDifference = Integer.parseInt(col[8]);
        points = Integer.parseInt(col[9]);
    }

}
