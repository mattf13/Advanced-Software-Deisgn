package atm;

public interface Service {
    double getAccountBalance(String accountNumber);

    void persistAccountBalance(String accountNumber, double newValue) throws Exception;
}
