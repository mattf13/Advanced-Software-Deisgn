package videostore;

public abstract class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;


    protected Movie(String title) {
        _title = title;
    }

    public static Movie create(String title, int priceCode) {
        if (priceCode == REGULAR) {
            return new Regular(title);
        }
        if (priceCode == CHILDRENS) {
            return new Childrens(title);
        }
        if (priceCode == NEW_RELEASE) {
            return new New_Release(title);
        }
        throw new RuntimeException();
    }

    abstract double calculateAmount(int dayRented);

    abstract int getFrequentRenterPoints(int daysRented);

    public String getTitle() {
        return _title;
    }

    ;
}