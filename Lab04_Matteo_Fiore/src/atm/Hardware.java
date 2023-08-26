package atm;

public interface Hardware {

    String getAccountNumberFromCard(String password) throws Exception;
    void pay(double newValue);
    double readEnvelope();
}
