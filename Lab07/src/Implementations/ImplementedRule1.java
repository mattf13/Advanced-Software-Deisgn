package Implementations;
import Interfaces.GameRule;
import System.UserRegistry;

public class ImplementedRule1 implements GameRule {

    private UserRegistry ur = new UserRegistry();

    @Override
    public void applyRuleBefore() {
        ur.getCurrentUser().addPoints(42);
    }

    @Override
    public void applyRuleAfter(Object taskReturn) {
        ur.getCurrentUser().addBadge("The Answer to Life");
    }

    @Override
    public void applyRuleException(Exception e) {
        ur.getCurrentUser().addBadge("Almost the Answer to Life");
    }
}
