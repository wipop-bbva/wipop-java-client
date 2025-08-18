package es.wipop.client.operations.charge;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import es.wipop.client.WipopClient;
import es.wipop.client.WipopClientConfiguration;
import es.wipop.client.domain.*;
import es.wipop.client.fixture.CustomerFixture;
import es.wipop.client.operations.charge.params.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static es.wipop.client.domain.OriginChannel.API;
import static es.wipop.client.domain.PaymentMethodType.REDIRECT;
import static es.wipop.client.domain.PaymentMethodType.THREE_DS;
import static es.wipop.client.domain.ProductType.PAYMENT_GATEWAY;
import static es.wipop.client.domain.ProductType.PAYMENT_LINK;
import static es.wipop.client.fixture.TerminalFixture.getTerminal;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 8080)
class ChargeOperationIT {

   private ChargeOperation chargeOperation;

   @BeforeEach
   void setUp() {
      final var config = new WipopClientConfiguration("http://localhost:8080", "m00000000000000000", "sk_test_key");
      this.chargeOperation = WipopClient.of(config).chargeOperation();
   }

   @Test
   void shouldCreateCharge() {
      final var params = new CreateChargeParams()
            .amount(BigDecimal.ONE)
            .currency(Currency.EUR)
            .orderId("1234abcdefgh")
            .description("Test redirection payment link")
            .productType(PAYMENT_LINK)
            .originChannel(API)
            .capture(true)
            .customer(CustomerFixture.getCustomer())
            .terminal(getTerminal("1"));

      final var charge = chargeOperation.create(params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("CHARGE_PENDING", Charge::getStatus)
            .returns("Test redirection payment link", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount)
            .returns("a0000000000000000000", Charge::getCustomerId);
      assertThat(charge.getPaymentMethod())
            .isNotNull()
            .returns("https:/test.wipop.es/c/v1/m00000000000000000/charges/tr000000000000000000/card_capture?lang=es-ES", PaymentMethod::getUrl)
            .returns(PaymentMethodType.REDIRECT, PaymentMethod::getType);
   }

   @Test
   void shouldCreateChargeWithCustomerId() {
      final var params = new CreateChargeParams()
            .amount(BigDecimal.ONE)
            .currency(Currency.EUR)
            .orderId("1234abcdefgh")
            .description("Test redirection payment link")
            .productType(PAYMENT_LINK)
            .originChannel(API)
            .capture(true)
            .terminal(getTerminal("1"));

      final var charge = chargeOperation.create(CustomerFixture.ID, params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("CHARGE_PENDING", Charge::getStatus)
            .returns("Test redirection payment link with customer ID", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount)
            .returns("a0000000000000000000", Charge::getCustomerId);
      assertThat(charge.getPaymentMethod())
            .isNotNull()
            .returns("https:/test.wipop.es/c/v1/m00000000000000000/charges/tr000000000000000000/card_capture?lang=es-ES", PaymentMethod::getUrl)
            .returns(PaymentMethodType.REDIRECT, PaymentMethod::getType);
   }

   @Test
   void shouldCreateOneClickCharge() {
      final var params = new CreateChargeParams()
            .amount(BigDecimal.ONE)
            .currency(Currency.EUR)
            .orderId("1234abcdefgh")
            .description("Test redirection payment gateway One-Click")
            .productType(PAYMENT_GATEWAY)
            .originChannel(API)
            .capture(true)
            .sourceId("k000000000000000000")
            .useCof(true)
            .terminal(getTerminal("2"));

      final var charge = chargeOperation.create(params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("CHARGE_PENDING", Charge::getStatus)
            .returns("Test redirection payment gateway One-Click", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount);
      assertThat(charge.getPaymentMethod())
            .isNotNull()
            .returns("https:/test.wipop.es/c/v1/m00000000000000000/charges/tr000000000000000000/card_capture?lang=es-ES", PaymentMethod::getUrl)
            .returns(THREE_DS, PaymentMethod::getType);
      assertThat(charge.getPaymentMethod().getEmv3ds())
            .isNotNull()
            .returns("CardConfiguration", Emv3ds::getThreeDSInfo)
            .returns("2.2.0", Emv3ds::getProtocolVersion)
            .returns("f169409a-9656-40a5-a22f-399c2048b121", Emv3ds::getThreeDSServerTransID)
            .returns("https://3ds.example/method", Emv3ds::getThreeDSMethodURL);
   }

   @Test
   void shouldCreateCofCharge() {
      final var params = new CreateChargeParams()
            .amount(BigDecimal.ONE)
            .currency(Currency.EUR)
            .orderId("1234abcdefgh")
            .description("Test redirection payment gateway COF")
            .productType(PAYMENT_GATEWAY)
            .originChannel(API)
            .useCof(true)
            .terminal(getTerminal("2"));

      final var charge = chargeOperation.create(params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("CHARGE_PENDING", Charge::getStatus)
            .returns("Test redirection payment gateway COF", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount);
      assertThat(charge.getPaymentMethod())
            .isNotNull()
            .returns("https:/test.wipop.es/c/v1/m00000000000000000/charges/tr000000000000000000/card_capture?lang=es-ES", PaymentMethod::getUrl)
            .returns(REDIRECT, PaymentMethod::getType);
   }

   @Test
   void shouldCreatePreAuthorizedCharge() {
      final var params = new CreateChargeParams()
            .amount(BigDecimal.ONE)
            .currency(Currency.EUR)
            .orderId("1234abcdefgh")
            .description("Test redirection payment link pre-authorization")
            .productType(PAYMENT_LINK)
            .originChannel(API)
            .capture(false)
            .customer(CustomerFixture.getCustomer())
            .terminal(getTerminal("1"));

      final var charge = chargeOperation.create(params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("CHARGE_PENDING", Charge::getStatus)
            .returns("Test redirection payment link pre-authorization", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount)
            .returns("a0000000000000000000", Charge::getCustomerId);
      assertThat(charge.getPaymentMethod())
            .isNotNull()
            .returns("https:/test.wipop.es/c/v1/m00000000000000000/charges/tr000000000000000000/card_capture?lang=es-ES", PaymentMethod::getUrl)
            .returns(PaymentMethodType.REDIRECT, PaymentMethod::getType);
   }

   @Test
   void shouldCreateConfirmCharge() {
      final var params = new ConfirmChargeParams()
            .tokenId("k000000000000000000")
            .deviceSessionId(UUID.randomUUID().toString())
            .paymentsType(PaymentType.NONE);

      final var charge = chargeOperation.confirm("tr000000000000000000", params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("CHARGE_PENDING", Charge::getStatus)
            .returns("Test charge confirm", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount);
      assertThat(charge.getPaymentMethod())
            .isNotNull()
            .returns("https:/test.wipop.es/c/v1/m00000000000000000/charges/tr000000000000000000/card_capture?lang=es-ES", PaymentMethod::getUrl)
            .returns(THREE_DS, PaymentMethod::getType);
      assertThat(charge.getPaymentMethod().getEmv3ds())
            .isNotNull()
            .returns("CardConfiguration", Emv3ds::getThreeDSInfo)
            .returns("2.2.0", Emv3ds::getProtocolVersion)
            .returns("f169409a-9656-40a5-a22f-399c2048b121", Emv3ds::getThreeDSServerTransID)
            .returns("https://3ds.example/method", Emv3ds::getThreeDSMethodURL);
   }

   @Test
   void shouldCreateConfirmChargeWithCustomer() {
      final var params = new ConfirmChargeParams()
            .tokenId("k000000000000000000")
            .deviceSessionId(UUID.randomUUID().toString())
            .paymentsType(PaymentType.NONE);

      final var charge = chargeOperation.confirm("a0000000000000000000", "tr000000000000000000", params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("CHARGE_PENDING", Charge::getStatus)
            .returns("Test charge confirm customer", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount);
      assertThat(charge.getPaymentMethod())
            .isNotNull()
            .returns("https:/test.wipop.es/c/v1/m00000000000000000/charges/tr000000000000000000/card_capture?lang=es-ES", PaymentMethod::getUrl)
            .returns(THREE_DS, PaymentMethod::getType);
      assertThat(charge.getPaymentMethod().getEmv3ds())
            .isNotNull()
            .returns("CardConfiguration", Emv3ds::getThreeDSInfo)
            .returns("2.2.0", Emv3ds::getProtocolVersion)
            .returns("f169409a-9656-40a5-a22f-399c2048b121", Emv3ds::getThreeDSServerTransID)
            .returns("https://3ds.example/method", Emv3ds::getThreeDSMethodURL);
   }

   @Test
   void shouldRefundCharge() {
      final var params = new RefundParams()
            .amount(BigDecimal.ONE);

      final var charge = chargeOperation.refund("tr000000000000000000", params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("COMPLETED", Charge::getStatus)
            .returns("Test charge refund", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount);
      assertThat(charge.getCustomer())
            .isNotNull()
            .returns("a0000000000000000000", Customer::getId)
            .returns("Foo", Customer::getName)
            .returns("Bar", Customer::getLastName)
            .returns("foo.bar@example.com", Customer::getEmail)
            .returns("5555555555", Customer::getPhoneNumber)
            .returns("foo-bar", Customer::getExternalId);
      assertThat(charge.getRefund())
            .isNotNull()
            .returns("tr000000000000000000", Refund::getId)
            .returns(BigDecimal.ONE, Refund::getAmount)
            .returns("100000", Refund::getAuthorization)
            .returns("CARD", Refund::getMethod)
            .returns("OUT", Refund::getOperationType)
            .returns("REFUND", Refund::getTransactionType)
            .returns("COMPLETED", Refund::getStatus)
            .returns("EUR", Refund::getCurrency)
            .returns("Test charge refund 100000", Refund::getDescription)
            .returns("a0000000000000000000", Refund::getCustomerId);
   }

   @Test
   void shouldReversalCharge() {
      final var params = new ReversalParams()
            .reason(ReversalReason.PRE_REVERSAL);

      final var charge = chargeOperation.reversal("tr000000000000000000", params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("CANCELLED", Charge::getStatus)
            .returns("Test charge reversal", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount);
      assertThat(charge.getCustomer())
            .isNotNull()
            .returns("a0000000000000000000", Customer::getId)
            .returns("Foo", Customer::getName)
            .returns("Bar", Customer::getLastName)
            .returns("foo.bar@example.com", Customer::getEmail)
            .returns("5555555555", Customer::getPhoneNumber)
            .returns("foo-bar", Customer::getExternalId);
   }

   @Test
   void shouldCaptureCharge() {
      final var params = new CaptureParams()
            .amount(BigDecimal.ONE);

      final var charge = chargeOperation.capture("tr000000000000000000", params);

      assertThat(charge)
            .isNotNull()
            .returns("tr000000000000000000", Charge::getId)
            .returns("CARD", Charge::getMethod)
            .returns("EUR", Charge::getCurrency)
            .returns("IN", Charge::getOperationType)
            .returns("CHARGE", Charge::getTransactionType)
            .returns("COMPLETED", Charge::getStatus)
            .returns("Test charge refund", Charge::getDescription)
            .returns("1234abcdefgh", Charge::getOrderId)
            .returns(BigDecimal.ONE, Charge::getAmount);
      assertThat(charge.getCustomer())
            .isNotNull()
            .returns("a0000000000000000000", Customer::getId)
            .returns("Foo", Customer::getName)
            .returns("Bar", Customer::getLastName)
            .returns("foo.bar@example.com", Customer::getEmail)
            .returns("5555555555", Customer::getPhoneNumber)
            .returns("foo-bar", Customer::getExternalId);
   }
}
