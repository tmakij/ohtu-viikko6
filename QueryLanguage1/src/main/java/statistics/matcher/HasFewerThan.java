package statistics.matcher;

public final class HasFewerThan extends HasCertainAmount {

    public HasFewerThan(int value, String category) {
        super(value, category, (first, second) -> !HasAtLeast.isLargerOrEqual.compareFirstToSecond(first, second));
    }
}
