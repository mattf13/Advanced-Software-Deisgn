package security;

public class SecurityProxy implements SecuredAccess {

    private String username;
    private SecuredAccess encapsulatedSecuredAccess;
    private SecurityStorage ss;

    public SecurityProxy(String username, SecuredAccess encapsulatedSecuredAccess) {
        this.username = username;
        this.encapsulatedSecuredAccess = encapsulatedSecuredAccess;
        this.ss = SecurityStorage.getInstance();
    }

    @Override
    public void executeOperation() throws AccessNotAuthorizedException {
        if (ss.verifyAccess(username, "SecuredAccess", "executeOperation")) {
                this.encapsulatedSecuredAccess.executeOperation();
        } else {
            throw new AccessNotAuthorizedException();
        }
    }

    @Override
    public void cancelOperation() {
        this.encapsulatedSecuredAccess.cancelOperation();
    }

    @Override
    public void operationMetadata() {
        this.encapsulatedSecuredAccess.operationMetadata();
    }
}
