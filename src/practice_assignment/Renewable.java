package practice_assignment;

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

abstract class LibraryItem {
    private static int counter = 1000;
    private final String itemId;

    public LibraryItem() {
        itemId = "ITEM-" + (++counter);
    }

    public String getItemId() {
        return itemId;
    }

    public abstract int getLoanPeriodDays();
}

class Textbook extends LibraryItem
        implements Renewable, Reservable {

    private String title;

    public Textbook(String title) {
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }

    @Override
    public String reserve() {
        return title + " reserved";
    }
}

class Magazine extends LibraryItem
        implements Renewable {

    private String title;

    public Magazine(String title) {
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }
}

class DigitalPass implements Renewable {
    private String resourceName;

    public DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }
}

class Test5 {

    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items)
            System.out.println(item.getLoanPeriodDays());
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable)
            return ((Reservable) o).reserve();

        return "Reservation not supported";
    }

    public static void main(String[] args) {

        Textbook t = new Textbook("Java Fundamentals");
        Magazine m = new Magazine("Tech Monthly");
        DigitalPass d = new DigitalPass("E-Journal Access");

        processCheckouts(new LibraryItem[]{t, m});

        System.out.println(reserveIfSupported(t));
        System.out.println(reserveIfSupported(m));
        System.out.println(reserveIfSupported(d));
    }
}