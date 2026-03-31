package osu;

public class ProhibitionSign implements Sign {
    private Action forbiddenAction;

    public ProhibitionSign(Action forbiddenAction) {
        this.forbiddenAction = forbiddenAction;
    }

    @Override
    public Ticket checkViolation(Car car) {
        if (car.getAction() == forbiddenAction) {
            return new Ticket(2000, "Porušení zákazu: " + forbiddenAction);
        }
        return null;
    }
}