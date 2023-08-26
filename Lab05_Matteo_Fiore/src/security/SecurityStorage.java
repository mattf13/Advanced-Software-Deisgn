package security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecurityStorage {
    private static SecurityStorage ss = null;

    private Map<String, Set<AccessInfo>> storage = new HashMap<>();
    private SecurityStorage() {}

    public static SecurityStorage getInstance() {
        if (ss == null) {
            ss = new SecurityStorage();
        }
        return ss;
    }

    public void addAccess(String username, String className, String methodName) {
        if(!storage.containsKey(username)) {
            storage.put(username, new HashSet<>());
        }
        storage.get(username).add(new AccessInfo(className, methodName));
    }

    public boolean verifyAccess(String username, String className, String methodName) {
        return storage.containsKey(username) && storage.get(username).contains(new AccessInfo(className, methodName));
    }

    public static void clear() {
        ss = null;
    }
}
