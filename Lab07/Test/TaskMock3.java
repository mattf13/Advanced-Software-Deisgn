import Exceptions.FailedExecutionException;
import Interfaces.Task;

public class TaskMock3 implements Task {

    @Override
    public Object execute() throws FailedExecutionException {
        throw new FailedExecutionException();
    }
}