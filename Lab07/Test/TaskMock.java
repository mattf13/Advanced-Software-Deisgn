import Exceptions.FailedExecutionException;
import Interfaces.Task;

public class TaskMock implements Task {

    @Override
    public Object execute() throws FailedExecutionException {

        return null;
    }
}
