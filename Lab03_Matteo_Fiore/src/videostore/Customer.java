package videostore;
import java.util.Enumeration;
import java.util.Vector;
public class Customer {

    private String _name;
    private Vector _rentals = new Vector();
    private double totalAmount;
    private int frequentRenterPoints;

    public Customer(String name) {
        _name = name;
    };
    public void addRental(Rental arg) {
        _rentals.addElement(arg);
        totalAmount += arg.calculateAmount();
        frequentRenterPoints += arg.getFrequentRenterPoints();

    }

    public String getName() {
        return _name;
    }

    public double totalAmount(){
        return totalAmount;
    }
    public int frequentRenterPoints(){
        return frequentRenterPoints;
    }
    public String statement() {

        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
                       result += "\t" + each.getMovie().getTitle() + "\t"
                    + String.valueOf(each.calculateAmount()) + "\n";
        }
        result += "Amount owed is " + String.valueOf(totalAmount()) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints())
                + " frequent renter points";
        return result;
    }
}