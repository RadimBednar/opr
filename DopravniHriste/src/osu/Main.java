package osu;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Location loc = new Location("Hlavni trida");

        loc.addSign(new SpeedLimit(50));
        loc.addSign(new ProhibitionSign(Action.PARKING));
        loc.addSign(new DirectionSign(Direction.STRAIGHT));

        FineManagement fm = new FineManagement();

        Car c1 = new Car("1A1 1111", Action.TURNING, 45, Direction.STRAIGHT);
        Car c2 = new Car("2B2 2222", Action.TURNING, 85, Direction.STRAIGHT);
        Car c3 = new Car("3C3 3333", Action.PARKING, 10, Direction.LEFT);

        Car[] cars = {c1, c2, c3};

        for (Car car : cars) {
            System.out.println("SPZ: " + car.getLicenceplate());
            List<Ticket> tickets = fm.checkViolations(car, loc);

            if (tickets.isEmpty()) {
                System.out.println("OK");
            } else {
                int sum = 0;
                for (Ticket t : tickets) {
                    System.out.println("- " + t.getDescription() + " (" + t.getPrice() + " Kc)");
                    sum += t.getPrice();
                }
                System.out.println("Celkem pokuta: " + sum + " Kc");
            }
            System.out.println();
        }
    }
}