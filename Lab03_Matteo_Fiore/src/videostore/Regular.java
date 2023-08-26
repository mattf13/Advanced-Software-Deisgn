package videostore;

public class Regular extends Movie{
    public Regular(String title) {
        super(title);
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }

    @Override
    double calculateAmount(int dayRented) {
        double thisAmount = 2;
        if(dayRented>2){
            thisAmount+=(dayRented-2)*1.5;
        }
        return thisAmount;
    }
}
