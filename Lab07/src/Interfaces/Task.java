package Interfaces;
import Exceptions.FailedExecutionException;

public interface Task {

    public Object execute () throws FailedExecutionException;


}
