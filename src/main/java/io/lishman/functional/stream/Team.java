package io.lishman.functional.stream;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
final class Team {

    // TODO change to private, use Team::getName

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
        drawn = Integer.parseInt(col[5]);
        lost = Integer.parseInt(col[6]);
        goalsFor = Integer.parseInt(col[7]);
        goalsAgainst = Integer.parseInt(col[8]);
        goalDifference = Integer.parseInt(col[9]);
        points = Integer.parseInt(col[10]);
    }

}
