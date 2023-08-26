import Exceptions.FailedExecutionException;
import Interfaces.Task;

public class TaskMock2 implements Task {

    @Override
    public Object execute() throws FailedExecutionException {

        return null;
    }
}