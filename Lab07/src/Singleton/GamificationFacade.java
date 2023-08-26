package Singleton;

import Interfaces.GameRule;
import Interfaces.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Exceptions.FailedExecutionException;

public class GamificationFacade  {

    HashMap<Class<? extends Task>, ArrayList<GameRule>> tasksAndRulesMap;
    private static GamificationFacade instance;


    private GamificationFacade(){
        tasksAndRulesMap = new HashMap<Class<? extends Task>, ArrayList<GameRule>>();
    }

    public static GamificationFacade getInstance() {
        if(instance== null) {
            instance = new GamificationFacade();
        }
        return instance;
    }

    public void setGameRule(GameRule gr, Class<? extends Task> c) {
        if(!tasksAndRulesMap.containsKey(c)){
            tasksAndRulesMap.put(c, new ArrayList<>());
        }
        tasksAndRulesMap.get(c).add(gr);
    }

    public Object execute(Task t) {
        List<GameRule> grList = tasksAndRulesMap.get(t.getClass());

        for(GameRule g : grList) {
                g.applyRuleBefore();
        }

        try {
            Object o = t.execute();

            for(GameRule g : grList) {
                g.applyRuleAfter(o);
            }
            return o;
        }
        catch (FailedExecutionException e) {

            for(GameRule g : grList) {
                g.applyRuleException(e);
            }
            throw new RuntimeException(e);
        }
    }
}
