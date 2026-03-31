package osu;

public class SpeedLimit implements Sign {
    private int maxSpeed;

    public SpeedLimit(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public Ticket checkViolation(Car car) {
        if (car.getSpeed() > maxSpeed) {
            return new Ticket(1000, "Překročení maximální povolené rychlosti (" + maxSpeed + " km/h).");
        }
        return null; // Žádný přestupek
    }
}