package statistics.matcher;

import statistics.Player;

abstract class Conditional implements Matcher {

    protected final Matcher[] matchers;

    protected Conditional(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public final boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            if (matcher.matches(p) == match()) {
                return returnValue();
            }
        }
        return !returnValue();
    }

    protected abstract boolean match();

    protected abstract boolean returnValue();
}
