package System;


public class UserRegistry{

    public static ThreadLocal<User> userThread = new ThreadLocal<>();

    public User getCurrentUser(){
        return userThread.get();
    }

    public void setCurrentUser(User user) {
        userThread.set(user);
    }
}
