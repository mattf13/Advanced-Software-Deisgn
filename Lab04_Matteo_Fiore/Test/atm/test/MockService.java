package atm.test;

import atm.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockService implements Service {
    private double balanceToReturn;
    private String persistedAccount;
    private double persistedValue;

    @Override
    public double getAccountBalance(String accountNumber) {
        return this.balanceToReturn;
    }

    @Override
    public void persistAccountBalance(String accountNumber, double newValue) throws Exception {
        this.persistedAccount = accountNumber;
        this.persistedValue = newValue;
    }

    public void returnBalance(double value) {
        this.balanceToReturn = value;
    }

    public void assertPersistAccount(String accountNumber, double remainingValue) {
        assertEquals(accountNumber, this.persistedAccount);
        assertEquals(remainingValue, this.persistedValue);
    }
}
