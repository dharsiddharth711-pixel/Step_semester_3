package practice_assignment;

class EventTicket4 {
    protected double basePrice;
    protected double amountPaid;

    public EventTicket4(double basePrice) {
        this.basePrice = basePrice;
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    String printTicket() {
        return "Standard | Balance: " + getBalanceDue();
    }
}

class WorkshopTicket4 extends EventTicket4 {
    private String track;

    public WorkshopTicket4(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    String getTrack() {
        return track;
    }

    @Override
    String printTicket() {
        return "Workshop | Track: " + track + " | Balance: " + getBalanceDue();
    }
}

class TicketAnnouncer {
    static String batchPrint(EventTicket4[] tickets) {
        StringBuilder sb = new StringBuilder();

        for (EventTicket4 ticket : tickets) {
            sb.append(ticket.printTicket()).append(" ");

            if (ticket instanceof WorkshopTicket4) {
                WorkshopTicket4 workshop = (WorkshopTicket4) ticket;
                sb.append("[Track via downcast: ")
                        .append(workshop.getTrack())
                        .append("] | ");
            }
        }

        return sb.toString();
    }
}
