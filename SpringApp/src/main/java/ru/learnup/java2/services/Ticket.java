package ru.learnup.java2.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    private OperaEvent operaEvent;
    private String status = "Free";
    private int seatNumber;

    public Ticket(OperaEvent operaEvent, int seatNumber) {
        this.operaEvent = operaEvent;
        this.seatNumber = seatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getSeatNumber() == ticket.getSeatNumber() && Objects.equals(getOperaEvent(), ticket.getOperaEvent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(operaEvent, seatNumber);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "operaEvent=" + operaEvent.getName() +
                ", status='" + status + '\'' +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
