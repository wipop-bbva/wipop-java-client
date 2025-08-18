package es.wipop.client.operations.checkout;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import es.wipop.client.WipopClient;
import es.wipop.client.WipopClientConfiguration;
import es.wipop.client.domain.Checkout;
import es.wipop.client.domain.Currency;
import es.wipop.client.domain.Customer;
import es.wipop.client.fixture.CustomerFixture;
import es.wipop.client.operations.checkout.params.CheckoutParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static es.wipop.client.domain.OriginChannel.PAYMENT_BUTTON;
import static es.wipop.client.domain.ProductType.PAYMENT_GATEWAY;
import static es.wipop.client.fixture.TerminalFixture.getTerminal;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 8080)
class CheckoutOperationIT {

   private CheckoutOperation checkoutOperation;

   @BeforeEach
   void setUp() {
      final var config = new WipopClientConfiguration("http://localhost:8080", "m0000000000000000000", "sk_test_key");
      this.checkoutOperation = WipopClient.of(config).checkoutOperation();
   }

   @Test
   void shouldCreateCheckout() {
      final var checkoutParams = new CheckoutParams()
            .amount(BigDecimal.ONE)
            .currency(Currency.EUR)
            .orderId("1234abcdefgh")
            .description("Checkout button payment")
            .productType(PAYMENT_GATEWAY)
            .originChannel(PAYMENT_BUTTON)
            .redirectUrl("https://example.com")
            .sendEmail(true)
            .customer(CustomerFixture.getCustomer())
            .terminal(getTerminal("2"));

      final var checkout = checkoutOperation.createCheckout(checkoutParams);

      assertThat(checkout)
            .isNotNull()
            .returns("ck000000000000000000", Checkout::getId)
            .returns(BigDecimal.ONE, Checkout::getAmount)
            .returns("1234abcdefgh", Checkout::getOrderId)
            .returns("EUR", Checkout::getCurrency)
            .returns("AVAILABLE", Checkout::getStatus)
            .returns("https://test.wipop.es/k/ck/000000000000", Checkout::getCheckoutLink);
      assertThat(checkout.getCustomer())
            .isNotNull()
            .returns("Foo", Customer::getName)
            .returns("foo.bar@example.com", Customer::getEmail);
   }

   @Test
   void shouldCreateCheckoutWithCustomerId() {
      final var checkoutParams = new CheckoutParams()
            .amount(BigDecimal.ONE)
            .currency(Currency.EUR)
            .orderId("1234abcdefgh")
            .description("Checkout button payment")
            .productType(PAYMENT_GATEWAY)
            .originChannel(PAYMENT_BUTTON)
            .redirectUrl("https://example.com")
            .sendEmail(true)
            .terminal(getTerminal("2"));

      final var checkout = checkoutOperation.createCheckout(CustomerFixture.ID, checkoutParams);

      assertThat(checkout)
            .isNotNull()
            .returns("ck000000000000000000", Checkout::getId)
            .returns(BigDecimal.ONE, Checkout::getAmount)
            .returns("1234abcdefgh", Checkout::getOrderId)
            .returns("EUR", Checkout::getCurrency)
            .returns("AVAILABLE", Checkout::getStatus)
            .returns("https://test.wipop.es/k/ck/000000000000", Checkout::getCheckoutLink);
      assertThat(checkout.getCustomer())
            .isNotNull()
            .returns("Foo", Customer::getName)
            .returns("Bar", Customer::getLastName)
            .returns("foo.bar@example.com", Customer::getEmail)
            .returns("5555555555", Customer::getPhoneNumber);
   }
}
