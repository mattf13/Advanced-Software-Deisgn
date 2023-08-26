package Implementations;

import Interfaces.GameRule;
import System.*;

public class ImplementedRule2 implements GameRule {

    private UserRegistry ur2 = new UserRegistry();


    @Override
    public void applyRuleBefore() {

        ur2.getCurrentUser().removePoints(42);
        ur2.getCurrentUser().addBadge("Life is a Mystery");
    }
    @Override
    public void applyRuleAfter(Object taskReturn) {
        ur2.getCurrentUser().removeBadge("The Answer to Life");
    }

    @Override
    public void applyRuleException(Exception e) {ur2.getCurrentUser().addBadge("Galaxy Easter egg");
    }
}
