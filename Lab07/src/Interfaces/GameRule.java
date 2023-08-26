package Interfaces;

public interface GameRule {

    public void applyRuleBefore();
    public void applyRuleAfter(Object taskReturn);

    public void applyRuleException(Exception e);

}
