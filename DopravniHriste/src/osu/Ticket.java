package osu;

public class Ticket {
    private int price;
    private String description;

    public Ticket(int price, String description) {
        this.price = price;
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}