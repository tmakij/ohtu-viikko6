package ohtu;

import java.util.HashMap;
import java.util.Map;

public final class TennisScore {

    private static final Map<Integer, String> scores = new HashMap<>();

    static {
        scores.put(0, "Love");
        scores.put(1, "Fifteen");
        scores.put(2, "Thirty");
        scores.put(3, "Forty");
    }

    private int score = 0;

    public void increment() {
        score++;
    }

    public int value() {
        return score;
    }

    public int compare(TennisScore other) {
        return value() - other.value();
    }

    public boolean equals(TennisScore other) {
        return compare(other) == 0;
    }

    public String toStringEven() {
        if (scores.containsKey(value())) {
            return scores.get(value()) + "-All";
        }
        return "Deuce";
    }

    public String toStringUneven() {
        return scores.get(value());
    }

    public boolean isGame() {
        return value() >= 4;
    }
}
