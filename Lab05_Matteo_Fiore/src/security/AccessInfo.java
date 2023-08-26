package security;

import java.util.Objects;

public class AccessInfo {
    private String className;
    private String methodName;

    public AccessInfo(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessInfo that = (AccessInfo) o;
        return className.equals(that.className) && methodName.equals(that.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, methodName);
    }
}
