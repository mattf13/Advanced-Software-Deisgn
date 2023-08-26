package Implementations;

import Interfaces.GameRule;
import System.*;
public class ImplementedRule3 implements GameRule {

    private UserRegistry ur3 = new UserRegistry();

    @Override
    public void applyRuleBefore() {
        ur3.getCurrentUser().addPoints(0);
    }

    @Override
    public void applyRuleAfter(Object taskReturn) {
        ur3.getCurrentUser().addBadge("The Best Number");
        ur3.getCurrentUser().addPoints(13);
    }

    @Override
    public void applyRuleException(Exception e) {
        //Nothing will be obtained
    }
}
