package statistics.matcher;

public final class HasAtLeast extends HasCertainAmount {

    static final IntegerComparator isLargerOrEqual = (first, second) -> first >= second;

    public HasAtLeast(int value, String category) {
        super(value, category, isLargerOrEqual);
    }
}
