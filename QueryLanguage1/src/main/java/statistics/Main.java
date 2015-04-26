package statistics;

import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Not;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

final class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        Matcher m = new And(new HasAtLeast(10, "goals"),
                new HasAtLeast(10, "assists"),
                new Or(new PlaysIn("PHI"), new And(new PlaysIn("ANA"), new Not(new PlaysIn("WSH")))),
                new Or(new HasFewerThan(25, "assists"), new HasAtLeast(25, "goals"))
        );

        stats.matches(m).stream().forEach((player) -> {
            System.out.println(player);
        });
    }
}
