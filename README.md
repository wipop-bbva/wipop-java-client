# Wipop Java Client

A Java client library for integrating with the Wipop payment processing API.

## Features

- **Charge Operations**: Create, confirm, capture, refund, and reverse charges
- **Checkout Operations**: Create checkout for payment processing
- **Environments**: Support for sandbox and production environments
- **Type Safety**: Fully typed API with comprehensive domain models
- **Builder Pattern**: Fluent API for building request parameters
- **Error Handling**: Structured exception handling with detailed error information

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Installation

Add the following dependency to your `pom.xml`:

```xml

<dependency>
    <groupId>es.openpay</groupId>
    <artifactId>wipop-java-client</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Configuration

### Environment Configuration

The client supports two predefined environments:

- **SANDBOX**: `https://sand-api.wipop.es` - For testing and development
- **PRODUCTION**: `https://api.wipop.es` - For live transactions

```java
// Sandbox environment
WipopClientConfiguration sandboxConfig = new WipopClientConfiguration(
            WipopClientConfiguration.Environment.SANDBOX,
            "merchant-id",
            "secret-key"
      );

// Production environment  
WipopClientConfiguration prodConfig = new WipopClientConfiguration(
      WipopClientConfiguration.Environment.PRODUCTION,
      "merchant-id",
      "secret-key"
);
```

### Client Configuration

```java
import es.wipop.client.WipopClient;
import es.wipop.client.WipopClientConfiguration;
import es.wipop.client.WipopClientHttpConfiguration;

// Using sandbox environment with default HTTP settings
WipopClientConfiguration config = new WipopClientConfiguration(
      WipopClientConfiguration.Environment.SANDBOX,
      "your-merchant-id",
      "your-secret-key"
);

// Using custom HTTP timeout configuration
WipopClientHttpConfiguration httpConfig = new WipopClientHttpConfiguration(10000, 45000);
WipopClientConfiguration configWithTimeouts = new WipopClientConfiguration(
      WipopClientConfiguration.Environment.SANDBOX,
      "your-merchant-id",
      "your-secret-key",
      httpConfig
);

WipopClient client = WipopClient.of(config);
```

### HTTP Configuration

The client supports HTTP timeout configuration through `WipopClientHttpConfiguration`:

- **connectionRequestTimeout**: Timeout for connection request (default: 5 seconds)
- **responseTimeout**: Timeout for waiting responses (default: 30 seconds)

```java
// Default timeouts (5s, 30s)
WipopClientHttpConfiguration defaultConfig = new WipopClientHttpConfiguration();

// Custom timeouts (connectionRequest, response in milliseconds)
WipopClientHttpConfiguration customConfig = new WipopClientHttpConfiguration(10000, 45000);
```

## Charge Operations

### Helper Methods

#### Create a Customer

```java
private Customer createCustomer() {
    Customer customer = new Customer();
    customer.setName("Foo");
    customer.setLastName("Bar");
    customer.setEmail("foo.bar@example.com");
    customer.setPhoneNumber("5555555555");
    customer.setExternalId("foo-bar");
    
    Address address = new Address();
    address.setLine1("Puerta del Sol");
    address.setCity("Madrid");
    address.setState("Madrid");
    address.setPostalCode("150993");
    address.setCountryCode("ES");
    customer.setAddress(address);
    
    return customer;
}
```

#### Create a Terminal

```java
private Terminal createTerminal() {
    Terminal terminal = new Terminal();
    terminal.setId("1");
    return terminal;
}
```

### Payment Methods

The client supports different payment methods through the `method()` parameter:

#### Create a CARD charge

```java
import es.wipop.client.domain.*;
import es.wipop.client.operations.charge.params.CreateChargeParams;

import java.math.BigDecimal;

// Create charge with CARD method
CreateChargeParams params = new CreateChargeParams()
      .method(ChargeMethod.CARD)  // Specify CARD payment method
      .amount(BigDecimal.ONE)
      .currency(Currency.EUR)
      .orderId("1234abcdefgh")
      .description("Test card payment")
      .productType(ProductType.PAYMENT_LINK)
      .originChannel(OriginChannel.API)
      .capture(true)
      .customer(createCustomer())
      .terminal(createTerminal());

Charge charge = client.chargeOperation().create(params);
```

#### Create a BIZUM charge

```java
// Create charge with BIZUM method
CreateChargeParams params = new CreateChargeParams()
      .method(ChargeMethod.BIZUM)  // Specify BIZUM payment method
      .amount(BigDecimal.ONE)
      .currency(Currency.EUR)
      .orderId("1234abcdefgh")
      .description("Test Bizum payment")
      .productType(ProductType.PAYMENT_LINK)
      .originChannel(OriginChannel.API)
      .capture(true)
      .customer(createCustomer())
      .terminal(createTerminal());

Charge charge = client.chargeOperation().create(params);
```

### Create charge with token generation

```java
// Create a charge that generates a token for future use
CreateChargeParams params = new CreateChargeParams()
    .amount(BigDecimal.ONE)
    .currency(Currency.EUR)
    .orderId("1234abcdefgh")
    .description("Test redirection payment gateway COF")
    .productType(ProductType.PAYMENT_GATEWAY)
    .originChannel(OriginChannel.API)
    .useCof(true)  // Enable Card on File
    .terminal(createTerminal());

Charge charge = client.chargeOperation().create(params);
// Returns CHARGE_PENDING status with redirect URL
```

### Create one-click charge

```java
// Create a one-click charge using a previously stored token
CreateChargeParams params = new CreateChargeParams()
    .amount(BigDecimal.ONE)
    .currency(Currency.EUR)
    .orderId("1234abcdefgh")
    .description("Test redirection payment gateway One-Click")
    .productType(ProductType.PAYMENT_GATEWAY)
    .originChannel(OriginChannel.API)
    .capture(true)
    .sourceId("k000000000000000000")  // Use existing token
    .useCof(true)
    .terminal(createTerminal());

Charge charge = client.chargeOperation().create(params);
// Returns CHARGE_PENDING status with 3D Secure authentication if required
```

### Create recurrent charge

```java
// Create a recurrent charge for subscription payments
PostType postType = new PostType();
postType.setMode(PostTypeMode.RECURRENT);

CreateChargeParams params = new CreateChargeParams()
    .amount(BigDecimal.ONE)
    .currency(Currency.EUR)
    .orderId("1234abcdefgh")
    .description("Monthly subscription payment")
    .productType(ProductType.PAYMENT_GATEWAY)
    .originChannel(OriginChannel.API)
    .sourceId("k000000000000000000")  // Stored card token
    .useCof(true)
    .postType(postType)  // Set recurrent mode
    .terminal(createTerminal());

Charge charge = client.chargeOperation().create(params);
```

### Create pre-authorization

```java
// Create a pre-authorization (capture = false)
CreateChargeParams params = new CreateChargeParams()
    .amount(BigDecimal.ONE)
    .currency(Currency.EUR)
    .orderId("1234abcdefgh")
    .description("Test redirection payment link pre-authorization")
    .productType(ProductType.PAYMENT_LINK)
    .originChannel(OriginChannel.API)
    .capture(false)  // Pre-authorization only
    .customer(createCustomer())
    .terminal(createTerminal());

Charge charge = client.chargeOperation().create(params);
// Returns CHARGE_PENDING status - funds are reserved but not captured
```

#### Confirm a pending charge

```java
import es.wipop.client.operations.charge.params.ConfirmChargeParams;

ConfirmChargeParams confirmParams = new ConfirmChargeParams()
      .tokenId("k000000000000000000")
      .deviceSessionId(UUID.randomUUID().toString())
      .paymentsType(PaymentType.NONE);

Charge confirmedCharge = client.chargeOperation()
      .confirm("tr000000000000000000", confirmParams);
```

#### Refund a charge

```java
import es.wipop.client.operations.charge.params.RefundParams;

RefundParams refundParams = new RefundParams()
      .amount(BigDecimal.ONE);

Charge refundedCharge = client.chargeOperation()
      .refund("tr000000000000000000", refundParams);
```

#### Reverse a charge

```java
import es.wipop.client.operations.charge.params.ReversalParams;

ReversalParams reversalParams = new ReversalParams()
      .reason(ReversalReason.PRE_REVERSAL);

Charge reversedCharge = client.chargeOperation()
      .reversal("tr000000000000000000", reversalParams);
```

#### Capture a pre-authorized charge

```java
import es.wipop.client.operations.charge.params.CaptureParams;

CaptureParams captureParams = new CaptureParams()
      .amount(BigDecimal.ONE);

Charge capturedCharge = client.chargeOperation()
      .capture("tr000000000000000000", captureParams);
```

### Checkout Operations

#### Create a checkout

```java
import es.wipop.client.operations.checkout.params.CheckoutParams;

CheckoutParams checkoutParams = new CheckoutParams()
      .amount(BigDecimal.ONE)
      .currency(Currency.EUR)
      .description("Test redirection payment gateway")
      .orderId("1234abcdefgh")
      .capture(true)
      .originChannel(OriginChannel.API)
      .productType(ProductType.PAYMENT_GATEWAY)
      .customer(createCustomer())
      .terminal(createTerminal())
      .redirectUrl("https://your-site.com/success")
      .sendEmail(true);

Checkout checkout = client.checkoutOperation().createCheckout(checkoutParams);
```

### Error Handling

```java
import es.wipop.client.exception.WipopClientException;

try{
    Charge charge = client.chargeOperation().create(params);
    // Process successful charge
} catch(WipopClientException e) {
   // Handle the wipop client exception
}
```

## API Reference

### Core Classes

- **WipopClient**: Main client for API operations
- **WipopClientConfiguration**: Client configuration and environment settings
- **ChargeOperation**: Interface for charge-related operations
- **CheckoutOperation**: Interface for checkout-related operations

### Domain Models

- **Charge**: Represents a payment charge
- **Checkout**: Represents a checkout session
- **Customer**: Customer information
- **Card**: Payment card details
- **Address**: Address information
- **Terminal**: Payment terminal details
- **ChargeMethod**: Payment method enumeration (CARD, BIZUM)

### Parameter Classes

- **CreateChargeParams**: Parameters for creating charges
- **ConfirmChargeParams**: Parameters for confirming charges
- **RefundParams**: Parameters for refunding charges
- **ReversalParams**: Parameters for reversing charges
- **CaptureParams**: Parameters for capturing charges
- **CheckoutParams**: Parameters for creating checkouts

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the terms specified in the project license file.

## Support

For support and questions, please refer to the official Wipop documentation or contact the development team.
