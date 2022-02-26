package Classes;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private LocalDate time;
    private double sum;

    public Transaction(double sum) {
        id = UUID.randomUUID();
        time = LocalDate.now();
        this.sum = sum;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
