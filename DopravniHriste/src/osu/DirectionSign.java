package osu;

public class DirectionSign implements Sign {
    private Direction allowedDirection;

    public DirectionSign(Direction allowedDirection) {
        this.allowedDirection = allowedDirection;
    }

    @Override
    public Ticket checkViolation(Car car) {
        if (car.getDirection() != allowedDirection) {
            return new Ticket(500, "Jízda nesprávným směrem. Povolený směr: " + allowedDirection);
        }
        return null;
    }
}