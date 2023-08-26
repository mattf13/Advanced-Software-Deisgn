package security;

public interface SecuredAccess {
    void executeOperation();

    void cancelOperation();

    void operationMetadata();
}
