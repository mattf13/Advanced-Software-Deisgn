package security.test;

import security.SecuredAccess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MockedSecuredAccess implements SecuredAccess {
    String lastMethodName;

    @Override
    public void executeOperation() {
        this.lastMethodName = "executeOperation";
    }

    @Override
    public void cancelOperation() {
        this.lastMethodName = "cancelOperation";
    }

    @Override
    public void operationMetadata() {
        this.lastMethodName = "operationMetadata";
    }
    public void assertLastMethodCalled(String expectedMethodName){
        assertEquals(expectedMethodName, this.lastMethodName);
    }

    public void assertNoMethodIsCalled() {
        assertNull(this.lastMethodName);
    }
}
