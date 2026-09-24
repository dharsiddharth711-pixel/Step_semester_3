package home_assignment;

class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class F3_BookInventory {
    public static void main(String[] args) {
        try {
            new BookInventory(0);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(b.getCopiesAvailable());
    }
}