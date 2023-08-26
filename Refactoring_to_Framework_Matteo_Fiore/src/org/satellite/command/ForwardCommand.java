package org.satellite.command;


@CommandType("MOVE_FORWARD")
public class ForwardCommand {
    private String power;
    private int time;
    private String secretCode;

    public ForwardCommand(String power, int time,String secretCode) {
        this.power = power;
        this.time = time;
        this.secretCode = secretCode;
    }

    public String getPower() {
        return power;
    }

    @IgnoreInSerialization
    public int getTime() {
        return time;
    }
    public String getSecretCode() {
        return secretCode;
    }
}
