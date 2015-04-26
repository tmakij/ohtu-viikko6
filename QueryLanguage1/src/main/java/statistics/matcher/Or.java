package statistics.matcher;

public final class Or extends Conditional {

    public Or(Matcher... matchers) {
        super(matchers);
    }

    @Override
    protected boolean match() {
        return true;
    }

    @Override
    protected boolean returnValue() {
        return true;
    }
}
