package videostore;

public class Rental {

    private Movie _movie;
    private int _daysRented;
    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(getDaysRented());
    }

    double calculateAmount() {
        return _movie.calculateAmount(getDaysRented());
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }
}