import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class CustomerStorage {

    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {


        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");

        String regexMail = "[A-Za-z]{3,}@[A-Za-z]{1,}[.][A-Za-z]{2,}";
        String regexPhone = "\\+7[\\d]{10}";
        if (components.length != 4){
            throw new InputMismatchException("Mistake1");
        } else if (!components[INDEX_EMAIL].matches(regexMail)){
            throw new InputMismatchException("Mistake2");
        } else if (!components[INDEX_PHONE].matches(regexPhone)){
            throw new InputMismatchException("Mistake3");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));



    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}