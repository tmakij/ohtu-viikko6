package statistics.matcher;

public final class Not extends Conditional {

    public Not(Matcher... matchers) {
        super(matchers);
    }

    @Override
    protected boolean match() {
        return true;
    }

    @Override
    protected boolean returnValue() {
        return false;
    }
}
