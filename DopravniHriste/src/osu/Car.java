package osu;

public class Car {
    private String licenceplate;
    private Action action;
    private int speed;
    private Direction direction;

    public Car(String licenceplate, Action action, int speed, Direction direction) {
        this.licenceplate = licenceplate;
        this.action = action;
        this.speed = speed;
        this.direction = direction;
    }


    public String getLicenceplate() { return licenceplate; }
    public Action getAction() { return action; }
    public int getSpeed() { return speed; }
    public Direction getDirection() { return direction; }
}