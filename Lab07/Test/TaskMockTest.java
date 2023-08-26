import Exceptions.FailedExecutionException;
import Implementations.*;
import Interfaces.Task;
import Singleton.GamificationFacade;
import System.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TaskMockTest {

    GamificationFacade gf = GamificationFacade.getInstance();
    TaskMock tm = new TaskMock();
    ImplementedRule1 r1 = new ImplementedRule1();
    ImplementedRule2 r2 = new ImplementedRule2();
    UserRegistry ur = new UserRegistry();
    User user = new User("Gughi");


    //Testing the ImplementationRule1
    //This test Should add 42 points and "The answer to life" badge
    @Test
    void executeTest1() {
        //Set current user
        ur.setCurrentUser(user);
        System.out.println("Points at the start: "+ur.getCurrentUser().getPoints()+ " for " + ur.getCurrentUser().getUsername());

        //Setting up the game rules
        gf.setGameRule(r1,tm.getClass());

        //now that the game rule is set the task will be executed
        //There should be points/badges added first and after the task
        gf.execute(tm);

        System.out.println("Points after rule1: " +ur.getCurrentUser().getPoints() + " for " + ur.getCurrentUser().getUsername());

        for (int i = 0; i < ur.getCurrentUser().getBadges().size(); i++) {
            System.out.println(ur.getCurrentUser().getUsername() + " has received the following badge: "+ur.getCurrentUser().getBadges().get(i));
        }

        assertEquals(42,ur.getCurrentUser().getPoints());
        assertEquals("The Answer to Life",ur.getCurrentUser().getBadges().get(0));
    }


    //Testing the ImplementationRule2 on the same user after executing the test 1
    //This test should remove the points and the badges earned in the previous test and add a new badge
    @Test
    void executeTest2() {

        //Set current user
        ur.setCurrentUser(user);

        //Setting up the game rules
        gf.setGameRule(r2,tm.getClass());

        //now that the game rule is set the task will be executed
        //There should be points/badges added first and after the task
        gf.execute(tm);


        for (int i = 0; i < ur.getCurrentUser().getBadges().size(); i++) {
            System.out.println(ur.getCurrentUser().getUsername() + " has received the following badge: "+ur.getCurrentUser().getBadges().get(i));
        }
        assertEquals(0,ur.getCurrentUser().getPoints());
        assertEquals("Life is a Mystery",ur.getCurrentUser().getBadges().get(0));
    }


    //This test will execute the implemented rule 3
    //This test should add points before and after the test and add a badge
    @Test
    void executeTest3() {

        GamificationFacade gf2 = GamificationFacade.getInstance();
        TaskMock2 tm2 = new TaskMock2();
        ImplementedRule3 r3 = new ImplementedRule3();
        UserRegistry ur2 = new UserRegistry();
        User user2 = new User("Fabio");

        //Set current user
        ur2.setCurrentUser(user2);

        //Setting up the game rules
        gf2.setGameRule(r3,tm2.getClass());

        //now that the game rule is set the task will be executed
        //There should be points/badges added first and after the task
        gf2.execute(tm2);

        System.out.println("The new user has the following username: "+ur2.getCurrentUser().getUsername());
        for (int i = 0; i < ur2.getCurrentUser().getBadges().size(); i++) {
            System.out.println(ur2.getCurrentUser().getUsername() + " has the following badge: "+ur2.getCurrentUser().getBadges().get(i));
        }
        assertEquals(13,ur2.getCurrentUser().getPoints());
        assertEquals("The Best Number",ur2.getCurrentUser().getBadges().get(0));
    }


    //This test will execute the implemented rule 3
    //This test should add points before and after the test and add a badge
    @Test
    void executeTest4() {

        GamificationFacade gf3 = GamificationFacade.getInstance();
        TaskMock3 tm3 = new TaskMock3();
        ImplementedRule2 r2 = new ImplementedRule2();
        UserRegistry ur3 = new UserRegistry();
        User user3 = new User("Linus");

        //Set current user
        ur3.setCurrentUser(user3);

        //Setting up the game rules
        gf3.setGameRule(r2,tm3.getClass());

        //Check if the gf3.execute method throws the exception Runtime which is the FailedExectutionException
        assertThrows(java.lang.RuntimeException.class, () -> {gf3.execute(tm3);});

        assertEquals("Galaxy Easter egg",ur3.getCurrentUser().getBadges().get(1));


        //For loop to display the badges (it is just for the programmer to check)
        System.out.println("The new user has the following username: "+ur3.getCurrentUser().getUsername());
        for (int i = 0; i < ur3.getCurrentUser().getBadges().size(); i++) {
            System.out.println(ur3.getCurrentUser().getUsername() + " has the following badge: "+ur3.getCurrentUser().getBadges().get(i));
        }
    }
}