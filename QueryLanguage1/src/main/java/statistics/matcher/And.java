package statistics.matcher;

public final class And extends Conditional {

    public And(Matcher... matchers) {
        super(matchers);
    }

    @Override
    protected boolean match() {
        return false;
    }

    @Override
    protected boolean returnValue() {
        return false;
    }
}
