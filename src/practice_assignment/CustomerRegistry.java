package practice_assignment;

import java.util.*;

class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Overriding equals and hashCode to ensure identity-based equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class CustomerRegistry {
    private Set<Customer> uniqueCustomers = new HashSet<>();

    public boolean addCustomer(Customer c) {
        boolean isAdded = uniqueCustomers.add(c);
        if (isAdded) {
            System.out.println("true");
        } else {
            System.out.println("false (duplicate rejected)");
        }
        return isAdded;
    }

    public boolean contains(Customer c) {
        return uniqueCustomers.contains(c);
    }

    public int getUniqueCount() {
        return uniqueCustomers.size();
    }

    public static void main(String[] args) {
        CustomerRegistry registry = new CustomerRegistry();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha"); // Duplicate from another branch
        Customer c3 = new Customer(102, "Ravi");

        registry.addCustomer(c1);
        registry.addCustomer(c2);
        registry.addCustomer(c3);

        System.out.println("unique count " + registry.getUniqueCount());
        System.out.println("contains: " + registry.contains(new Customer(101, "Asha")));
    }
}