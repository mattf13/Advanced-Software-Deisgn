package System;

import java.util.ArrayList;
import java.util.List;

public class User {

    public static String username;
    public static int userPoints;
    private List<Integer> pointsList;

    public static List<String> badgesList;


    public User(String username) {
        this.username = username;
        this.userPoints = 0;
        this.badgesList = new ArrayList<>();
        this.pointsList = new ArrayList<>();

    }


    public String getUsername() {
        return this.username;
    }

    public int getPoints() {
        return this.userPoints;
    }

    public List getBadges() {
        return this.badgesList;
    }

    public void addPoints(int pointsNumber) {
        pointsList.add(pointsNumber);
        userPoints = userPoints + pointsNumber;
    }

    public void removePoints(int pointsNumber) {
        pointsNumber = Math.abs(pointsNumber);
        userPoints = userPoints - pointsNumber;
    }

    public int getLastPoints() {
        return pointsList.get(pointsList.size() - 1);
    }

    public void addBadge(String badgeToAdd) {
        badgesList.add(badgeToAdd);
    }

    public void removeBadge(String badgeToRemove) {
        if (badgesList.contains(badgeToRemove))
            badgesList.remove(badgeToRemove);
    }


    public String getLastBadge() {
        return badgesList.get(badgesList.size() - 1);
    }
}
