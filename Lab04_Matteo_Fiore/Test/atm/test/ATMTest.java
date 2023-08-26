package atm.test;

import atm.ATM;
import atm.Hardware;
import atm.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class ATMTest {

    private Service mockitoService;

    private Hardware mockitoHardware;

    private ATM atm;

    @BeforeEach
    void setup() {
        mockitoService = mock(Service.class);
        mockitoHardware = mock(Hardware.class);

    }

    @Test
    void login() {
        MockService mockService = new MockService();
        MockHardware mockHardware = new MockHardware();
        mockHardware.returnAccountNumber("123456");

        ATM atm1 = new ATM(mockService, mockHardware);
        String message = atm1.login("password");
        assertEquals("Welcome 123456", message);

        mockHardware.assertReceivedPassword("password");
    }

    @Test
    void loginwithMockito() throws Exception {
        atm = new ATM(mockitoService, mockitoHardware);
        Mockito.doThrow(new Exception()).when(mockitoHardware).getAccountNumberFromCard(anyString());

        atm.login("password");
        verify(mockitoHardware, times(1)).getAccountNumberFromCard("password");
    }

    @Test
    void wrongPassword() throws Exception {
        MockService mockService = new MockService();
        MockHardware mockHardware = new MockHardware();
        mockHardware.throwExceptionForAWrongPassword();
        ATM atm1 = new ATM(mockService, mockHardware);
        String message = atm1.login("password");
        assertEquals("Wrong password", message);
    }

    @Test
    void wrongPasswordwithMockito() throws Exception {

        atm = new ATM(mockitoService, mockitoHardware);
        Mockito.doThrow(new Exception()).when(mockitoHardware).getAccountNumberFromCard(anyString());

        atm.login("password2");
        verify(mockitoHardware, Mockito.never()).getAccountNumberFromCard("password");
    }

    @Test
    void balance() throws Exception {
        MockService mockService = new MockService();
        MockHardware mockHardware = new MockHardware();
        mockHardware.returnAccountNumber("123456");
        mockService.returnBalance(1000.);
        ATM atm1 = new ATM(mockService, mockHardware);
        String message = atm1.balance("password");

        assertEquals("The balance from account 123456 is 1000,00", message);
    }

    @Test
    void balanceWithMockito() throws Exception {

        atm = new ATM(mockitoService, mockitoHardware);
        when(mockitoService.getAccountBalance(anyString())).thenReturn(anyDouble());
        String accountNumber = mockitoHardware.getAccountNumberFromCard("password");
        atm.balance("password");

        verify(mockitoService, times(1)).getAccountBalance(accountNumber);
    }

    @Test
    void withdraw() throws Exception {
        MockService mockService = new MockService();
        MockHardware mockHardware = new MockHardware();
        mockHardware.returnAccountNumber("123456");
        mockService.returnBalance(100.);
        ATM atm1 = new ATM(mockService, mockHardware);
        String message = atm1.withdraw("password", 10.);

        mockHardware.assertPaid(10.);
        mockService.assertPersistAccount("123456", 90.0);
        assertEquals("You have successfully withdrawn 10,00", message);
    }

    @Test
    void withdrawWithMockito() throws Exception {
        atm = new ATM(mockitoService, mockitoHardware);
        when(mockitoService.getAccountBalance(anyString())).thenReturn(anyDouble());
        String accountNumber = mockitoHardware.getAccountNumberFromCard("password");
        atm.withdraw("password", 0.);
        verify(mockitoService, times(1)).persistAccountBalance(accountNumber, 0.);
    }

    @Test
    void withdrawWithoutFunds() throws Exception {
        MockService mockService = new MockService();
        MockHardware mockHardware = new MockHardware();
        mockHardware.returnAccountNumber("123456");
        mockService.returnBalance(10.);
        ATM atm1 = new ATM(mockService, mockHardware);
        String message = atm1.withdraw("password", 100.);

        mockHardware.assertPaid(0.);
        assertEquals("Not enough fund in the account", message);
        assertEquals(10., mockService.getAccountBalance("123456"));
    }

    @Test
    void withdrawWithoutFundsWithMockito() throws Exception {
        atm = new ATM(mockitoService, mockitoHardware);
        when(mockitoService.getAccountBalance(anyString())).thenReturn(anyDouble());
        String accountNumber = mockitoHardware.getAccountNumberFromCard("password");
        atm.withdraw("password", 0.);
        verify(mockitoHardware, times(1)).pay(0.);
        verify(mockitoService, times(1)).persistAccountBalance(accountNumber, 0.);

    }

    @Test
    void deposit() throws Exception {
        MockService mockService = new MockService();
        MockHardware mockHardware = new MockHardware();
        mockHardware.returnAccountNumber("123456");
        mockService.returnBalance(10.);
        mockHardware.returnEnvelopeValue(100.0);
        ATM atm1 = new ATM(mockService, mockHardware);
        String message = atm1.deposit("123456");

        mockService.assertPersistAccount("123456", 110.0);
        assertEquals("You have successfully deposited 100,00", message);
    }
    @Test
    void depositWithMockito() throws Exception {
        atm = new ATM(mockitoService, mockitoHardware);
        when(mockitoService.getAccountBalance(anyString())).thenReturn(0.);
        when(mockitoHardware.readEnvelope()).thenReturn(anyDouble());
        String accountNumber = mockitoHardware.getAccountNumberFromCard("password");
        atm.deposit(accountNumber);
        verify(mockitoService, times(1)).persistAccountBalance(accountNumber, 0.);
    }
}
