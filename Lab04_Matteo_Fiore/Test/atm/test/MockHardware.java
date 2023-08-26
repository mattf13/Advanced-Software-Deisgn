package atm.test;

import atm.Hardware;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockHardware implements Hardware {
    private String accountNumber;
    private String password;
    private boolean throwException = false;
    private double paidValue = 0;
    private double depositedValue;

    @Override
    public String getAccountNumberFromCard(String password) throws Exception {
        if (!this.throwException) {
            this.password = password;
            return this.accountNumber;
        }
        throw new Exception();
    }

    @Override
    public void pay(double value) {
        this.paidValue = value;
    }

    @Override
    public double readEnvelope() {
        return depositedValue;
    }

    public void returnAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void assertReceivedPassword(String password) {
        assertEquals(this.password, password);
    }

    public void throwExceptionForAWrongPassword() {
        this.throwException = true;
    }

    public void assertPaid(double paidValue) {
        assertEquals(paidValue, this.paidValue);
    }

    public void returnEnvelopeValue(double value) {
        this.depositedValue = value;
    }
}
