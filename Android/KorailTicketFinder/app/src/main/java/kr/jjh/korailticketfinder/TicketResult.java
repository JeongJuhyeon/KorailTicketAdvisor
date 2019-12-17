package kr.jjh.korailticketfinder;

import java.io.Serializable;
import java.util.ArrayList;

public class TicketResult implements Serializable {
    public boolean isIndirect;
    public ArrayList<Ticket>tickets;

    public TicketResult(boolean isIndirect, ArrayList<Ticket> tickets) {
        this.isIndirect = isIndirect;
        this.tickets = tickets;
    }

    public boolean isIndirect() {
        return isIndirect;
    }

    public void setIndirect(boolean indirect) {
        isIndirect = indirect;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}



