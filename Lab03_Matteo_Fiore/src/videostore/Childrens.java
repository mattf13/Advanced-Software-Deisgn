package videostore;

public class Childrens extends Movie{

    public Childrens(String title) {
        super(title);
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }

    @Override
    double calculateAmount(int dayRented) {
        double thisAmount = 1.5;
        if (dayRented > 3){
            thisAmount += (dayRented - 3) * 1.5;
        }
        return thisAmount;
    }
}

