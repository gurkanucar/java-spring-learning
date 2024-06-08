package org.gucardev.nullSafe;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer();

        String address = Optional.ofNullable(customer)
                .map(Customer::getMainAddress)
                .map(Address::getPostalAddress)
                .orElse("default");
        System.out.println(address); // default

        address = NullSafeHelper
                .safeGet(() -> customer
                        .getMainAddress()
                        .getPostalAddress());
        System.out.println(address); // default


        address = NullSafeHelper
                .safeGetOrElse(() -> customer
                        .getMainAddress()
                        .getPostalAddress(), "default");
        System.out.println(address); // default


        if (customer != null && customer.getMainAddress() != null) {
            address = customer.getMainAddress().getPostalAddress();
            System.out.println(address);
        }

        address = customer.getMainAddress().getPostalAddress();
        System.out.println(address); // throws exception

    }

}
