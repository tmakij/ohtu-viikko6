package statistics;

import statistics.matcher.Matcher;
import statistics.matcher.QueryBuilder;

final class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();
        Matcher m = query.oneOf(
                query.playsIn("PHI")
                .hasAtLeast(10, "goals")
                .hasFewerThan(15, "assists").build(),
                query.playsIn("EDM")
                .hasAtLeast(50, "points").build()
        ).build();

        stats.matches(m).stream().forEach((player) -> {
            System.out.println(player);
        });
    }
}
