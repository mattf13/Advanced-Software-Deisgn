package atm;

public class ATM {

    private Service service;

    private Hardware hardware;

    public ATM(Service service, Hardware hardware) {
        this.hardware = hardware;
        this.service = service;
    }

    public String login(String password) {
        String accountNumber;
        try {
            accountNumber = this.hardware.getAccountNumberFromCard(password);
        } catch (Exception e) {
            return "Wrong password";
        }
        return "Welcome " + accountNumber ;
    }

    public String balance(String password) throws Exception {
        String accountNumber = this.hardware.getAccountNumberFromCard(password);
        double balance = service.getAccountBalance(accountNumber);
        return "The balance from account " + accountNumber +" is " + String.format("%.2f", balance);
    }

    public String withdraw(String password, double value) throws Exception {
        String accountNumber = this.hardware.getAccountNumberFromCard(password);
        double balance = this.service.getAccountBalance(accountNumber);
        if (value <= balance) {
            this.hardware.pay(value);
            this.service.persistAccountBalance(accountNumber, balance - value);
            return "You have successfully withdrawn " + String.format("%.2f", value);
        } else {

            return "Not enough fund in the account";
        }
    }

    public String deposit(String accountNumber) throws Exception {
        double valueFromEnvelope = hardware.readEnvelope();
        double currentBalance = service.getAccountBalance(accountNumber);
        double newBalance = currentBalance + valueFromEnvelope;
        service.persistAccountBalance(accountNumber, newBalance);

        return "You have successfully deposited " + String.format("%.2f", valueFromEnvelope);
    }
}
