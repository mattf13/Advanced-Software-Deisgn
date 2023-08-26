package videostore;

public class New_Release extends Movie {

    public New_Release(String title) {
        super(title);
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        if (daysRented>1){
            return 2;
        }
        return 1;
    }

    @Override
    double calculateAmount(int dayRented) {
        return dayRented * 3;
    }
}
