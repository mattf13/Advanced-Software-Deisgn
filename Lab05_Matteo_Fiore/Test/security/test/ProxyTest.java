package security.test;

import org.junit.jupiter.api.Test;
import security.AccessNotAuthorizedException;
import security.SecuredAccess;
import security.SecurityProxy;
import security.SecurityStorage;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProxyTest {
    @Test
    void testAuthorizeAccess() {
        MockedSecuredAccess securedAccess = new MockedSecuredAccess();
        SecuredAccess encapsulatedSecuredAccess = new SecurityProxy("username", securedAccess);
        SecurityStorage.getInstance().addAccess("username", "SecuredAccess", "cancelOperation");

        encapsulatedSecuredAccess.cancelOperation();
        securedAccess.assertLastMethodCalled("cancelOperation");
    }

    @Test
    void testNotAuthorizedAccess() {
        MockedSecuredAccess securedAccess = new MockedSecuredAccess();
        SecuredAccess encapsulatedSecuredAccess = new SecurityProxy("username", securedAccess);
        SecurityStorage.getInstance().addAccess("username", "SecuredAccess", "cancelOperation");

        assertThrows(AccessNotAuthorizedException.class, () -> encapsulatedSecuredAccess.executeOperation());

        securedAccess.assertNoMethodIsCalled();
    }
}
