# Wipop Java Client

A Java client library for integrating with the Wipop payment processing API.

## Features

- **Charge Operations**: Create, confirm, capture, refund, and reverse charges
- **Checkout Operations**: Create checkout sessions for payment processing
- **Multiple Environments**: Support for sandbox and production environments
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

## Quick Start

### 1. Initialize the Client

```java
import es.wipop.client.WipopClient;
import es.wipop.client.WipopClientConfiguration;

// Using predefined environment
WipopClientConfiguration config = new WipopClientConfiguration(
      WipopClientConfiguration.Environment.SANDBOX,
      "your-merchant-id",
      "your-secret-key"
);

      WipopClient client = WipopClient.of(config);
```

### 2. Create a Charge

```java
import es.wipop.client.domain.*;
import es.wipop.client.operations.charge.params.CreateChargeParams;

import java.math.BigDecimal;

// Create charge parameters
CreateChargeParams params = new CreateChargeParams()
      .amount(BigDecimal.ONE)
      .currency(Currency.EUR)
      .orderId("1234abcdefgh")
      .description("Test redirection payment link")
      .productType(ProductType.PAYMENT_LINK)
      .originChannel(OriginChannel.API)
      .capture(true)
      .customer(createCustomer())
      .terminal(createTerminal());

      // Execute the charge
      Charge charge = client.chargeOperation().create(params);

System.out.

      println("Charge ID: "+charge.getId());
      System.out.

      println("Status: "+charge.getStatus());
      System.out.

      println("Payment URL: "+charge.getPaymentMethod().

      getUrl());
```

### 3. Create a Customer

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

### 4. Create a Terminal

```java
private Terminal createTerminal() {
   Terminal terminal = new Terminal();
   terminal.setId("1");
   return terminal;
}
```

## Advanced Usage

### Charge Operations

#### Confirm a Charge

```java
import es.wipop.client.operations.charge.params.ConfirmChargeParams;

ConfirmChargeParams confirmParams = new ConfirmChargeParams()
      .tokenId("k000000000000000000")
      .deviceSessionId(UUID.randomUUID().toString())
      .paymentsType(PaymentType.NONE);

Charge confirmedCharge = client.chargeOperation()
      .confirm("tr000000000000000000", confirmParams);
```

#### Refund a Charge

```java
import es.wipop.client.operations.charge.params.RefundParams;

RefundParams refundParams = new RefundParams()
      .amount(BigDecimal.ONE);

Charge refundedCharge = client.chargeOperation()
      .refund("tr000000000000000000", refundParams);
```

#### Reverse a Charge

```java
import es.wipop.client.operations.charge.params.ReversalParams;

ReversalParams reversalParams = new ReversalParams()
      .reason(ReversalReason.PRE_REVERSAL);

Charge reversedCharge = client.chargeOperation()
      .reversal("tr000000000000000000", reversalParams);
```

#### Capture a Pre-authorized Charge

```java
import es.wipop.client.operations.charge.params.CaptureParams;

CaptureParams captureParams = new CaptureParams()
      .amount(BigDecimal.ONE);

Charge capturedCharge = client.chargeOperation()
      .capture("tr000000000000000000", captureParams);
```

### Checkout Operations

#### Create a Checkout Session

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

System.out.

println("Checkout ID: "+checkout.getId());
      System.out.

println("Checkout URL: "+checkout.getCheckoutLink());
```

### Error Handling

```java
import es.wipop.client.exception.WipopClientException;

try{
Charge charge = client.chargeOperation().create(params);
// Process successful charge
}catch(
WipopClientException e){
      System.err.

println("Error: "+e.getMessage());

      if(e.

getResponseCode() !=null){
      System.err.

println("Error Code: "+e.getResponseCode().

code());
      System.err.

println("Error Level: "+e.getResponseCode().

level());
      System.err.

println("Error Detail: "+e.getResponseCode().

detail());
      }
      }
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
