package osu;

import java.util.ArrayList;
import java.util.List;

public class FineManagement {

    public List<Ticket> checkViolations(Car car, Location location) {
        List<Ticket> tickets = new ArrayList<>();

        for (Sign currentSign : location.getSigns()) {
            Ticket ticket = currentSign.checkViolation(car);
            if (ticket != null) {
                tickets.add(ticket);
            }
        }

        return tickets;
    }
}