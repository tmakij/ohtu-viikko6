package statistics.matcher;

import java.util.ArrayList;
import java.util.List;

public final class QueryBuilder {

    private List<Matcher> mathcers = new ArrayList<>();

    public QueryBuilder playsIn(String team) {
        mathcers.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String key) {
        mathcers.add(new HasAtLeast(value, key));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String key) {
        mathcers.add(new HasFewerThan(value, key));
        return this;
    }

    public QueryBuilder playsIn(int value, String key) {
        mathcers.add(new HasFewerThan(value, key));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        mathcers.add(new Or(m1, m2));
        return this;
    }

    public Matcher build() {
        Matcher result = new And(mathcers.toArray(new Matcher[mathcers.size()]));
        mathcers = new ArrayList<>();
        return result;
    }
}
