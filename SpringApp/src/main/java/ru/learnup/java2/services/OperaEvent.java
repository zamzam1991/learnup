package ru.learnup.java2.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Scope(value = "prototype")
@NoArgsConstructor
public class OperaEvent {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    private int ageRestriction;
    @Getter
    private List<Ticket> tickets = new ArrayList<>();

    public OperaEvent(String name, String description, int ageRestriction, int ticketsCount) {
        if (ageRestriction >= 0) {
            this.name = name;
            this.description = description;
            this.ageRestriction = ageRestriction;
            for (int i = 1; i <= ticketsCount; i++)
                tickets.add(new Ticket(this, i));
        } else
            throw new IllegalArgumentException("Возрастное ограничение не может быть отрицательным!");
    }

    public void setAgeRestriction(int ageRestriction) {
        if (ageRestriction >= 0)
            this.ageRestriction = ageRestriction;
        else
            throw new IllegalArgumentException("Возрастное ограничение не может быть отрицательным!");
    }

    public List<Ticket> getAllFreeTickets() {
        List<Ticket> freeTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getStatus().equals("Free"))
                freeTickets.add(ticket);
        }
        return freeTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperaEvent)) return false;
        OperaEvent that = (OperaEvent) o;
        return getAgeRestriction() == that.getAgeRestriction() && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getAgeRestriction());
    }

    @Override
    public String toString() {
        return "OperaEvent{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ageRestriction=" + ageRestriction +
                ", freeTickets=" + getAllFreeTickets().size() +
                '}';
    }
}
