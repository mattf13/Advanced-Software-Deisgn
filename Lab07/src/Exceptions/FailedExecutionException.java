package Exceptions;

public class FailedExecutionException extends Exception {

    public FailedExecutionException() {
        super("There was a problem executing the task");
    }

}
