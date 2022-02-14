package ru.learnup.java2.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.java2.annotations.Notifable;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@Scope(value = "singleton")
public class OperaHouse {
    @Getter
    private List<OperaEvent> operaEventList = new ArrayList<>();

    public void add(OperaEvent operaEvent) {
        operaEventList.add(operaEvent);
    }

    public void add(String name, String description, int ageRestriction, int ticketsCount) {
        operaEventList.add(new OperaEvent(name, description,ageRestriction, ticketsCount));
    }

    public void remove(String name) {
        for (OperaEvent  operaEvent : operaEventList) {
            if (operaEvent.getName().equals(name)) {
                operaEventList.remove(operaEvent);
                return;
            }
        }
    }

    public List<OperaEvent> getAllEvents() {
        return operaEventList;
    }

    public OperaEvent getEvent(String name) {
        for (OperaEvent operaEvent : operaEventList) {
            if (operaEvent.getName().equals(name))
                return operaEvent;
        }
        return null;
    }

    @Notifable
    public Ticket buyTheTicket(OperaEvent operaEvent, int seatNumber) {
        List<Ticket> freeTickets = operaEvent.getAllFreeTickets();
        for (Ticket ticket : freeTickets) {
            if (ticket.getSeatNumber() == seatNumber) {
                ticket.setStatus("Sold");
                return ticket;
            }
        }
        return null;
    }

    @Notifable
    public List<Ticket> buyTheTickets(OperaEvent operaEvent, int... seatNumbers) {
        List<Ticket> freeTickets = operaEvent.getAllFreeTickets();
        List<Ticket> boughtTickets = new ArrayList<>();
        for (int x = 0; x < seatNumbers.length; x++) {
            for (Ticket ticket : operaEvent.getTickets()) {
                if (ticket.getSeatNumber() == seatNumbers[x]) {
                    ticket.setStatus("Sold");
                    boughtTickets.add(ticket);
                }
            }
        }
        return boughtTickets;
    }

    public void refundTheTicket(OperaEvent operaEvent, int seatNumber) {
        for (Ticket ticket : operaEvent.getTickets()) {
            if (ticket.getSeatNumber() == seatNumber) {
                ticket.setStatus("Free");
                return;
            }
        }
    }

    public void refundTheTicket(Ticket refundableTicket) {
        for (Ticket ticket : refundableTicket.getOperaEvent().getTickets()) {
            if (ticket.equals(refundableTicket)) {
                ticket.setStatus("Free");
                return;
            }
        }
    }

    public void refundTheTickets(OperaEvent operaEvent, int... seatNumbers) {
        for (int seatNumber : seatNumbers) {
            for (Ticket ticket : operaEvent.getTickets()) {
                if (ticket.getSeatNumber() == seatNumber) {
                    ticket.setStatus("Free");
                }
            }
        }
    }

    public void refundTheTickets(Ticket... refundableTickets) {
        for (Ticket refundableTicket : refundableTickets) {
            for (Ticket ticket : refundableTicket.getOperaEvent().getTickets()) {
                if (ticket.equals(refundableTicket)) {
                    ticket.setStatus("Free");
                }
            }
        }
    }

}
