package es.wipop.client.fixture;

import es.wipop.client.domain.Customer;

public final class CustomerFixture {

   public static final String ID = "a0000000000000000000";

   private CustomerFixture() {

   }

   public static Customer getCustomer() {
      final var customer = new Customer();
      customer.setName("Foo");
      customer.setLastName("Bar");
      customer.setEmail("foo.bar@example.com");
      customer.setPhoneNumber("5555555555");

      return customer;
   }
}
