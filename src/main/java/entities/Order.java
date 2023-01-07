package entities;

import java.util.UUID;

public class Order {
    private final String id;
    private int numberOfBricks;

    public Order(final int numberOfBricks) {
        this.id = UUID.randomUUID().toString();
        this.numberOfBricks = numberOfBricks;

    }

    public Order(final String id, final int numberOfBricks) {
        this.id = id;
        this.numberOfBricks = numberOfBricks;

    }

    public String getId() {
        return this.id;
    }

    public int getNumberOfBricks() {
        return this.numberOfBricks;
    }

    public void updateNumberOfBricks(final int numberOfBricks) {
        this.numberOfBricks = numberOfBricks;
    }

    public static Order of(final String id, final int numberOfBricks) {
        return new Order(id, numberOfBricks);
    }
}
