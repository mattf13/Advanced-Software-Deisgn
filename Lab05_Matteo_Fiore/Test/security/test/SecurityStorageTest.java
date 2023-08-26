package security.test;
import org.junit.jupiter.api.*;
import security.SecurityStorage;

import static org.junit.jupiter.api.Assertions.*;
public class SecurityStorageTest {
    @Test
    void testSettingAndRetrievingAccess(){
        SecurityStorage ss = SecurityStorage.getInstance();

        String username = "guerra";
        String className = "ClassName";
        String methodName = "methodName";

        ss.addAccess(username, className, methodName);

        boolean result1 = ss.verifyAccess(username, className, methodName);
        assertTrue(result1);
        boolean result2 = ss.verifyAccess(username, className, "notConfiguredMethod");
        assertFalse(result2);

        ss.clear();
    }
}
