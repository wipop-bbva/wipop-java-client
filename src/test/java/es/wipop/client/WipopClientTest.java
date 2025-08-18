package es.wipop.client;

import es.wipop.client.operations.charge.ChargeOperation;
import es.wipop.client.operations.checkout.CheckoutOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WipopClientTest {

   private WipopClient client;

   @BeforeEach
   void setUp() {
      final var configuration = new WipopClientConfiguration(
            WipopClientConfiguration.Environment.SANDBOX,
            "test-merchant",
            "test-key"
      );
      client = WipopClient.of(configuration);
   }

   @Test
   void shouldCreateChargeOperationInstance() {
      final var chargeOperation = client.chargeOperation();

      assertThat(chargeOperation).isInstanceOf(ChargeOperation.class);
   }

   @Test
   void shouldCreateCheckoutOperationInstance() {
      final var checkoutOperation = client.checkoutOperation();

      assertThat(checkoutOperation).isInstanceOf(CheckoutOperation.class);
   }
}
