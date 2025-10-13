# Wipop Java Client

Una librería cliente de Java para integrar con la API de procesamiento de pagos de Wipop.

## Características

- **Operaciones de Cargo con Tarjeta**
    - Generación de link de pago
    - Devoluciones
    - Creación de preautorización
    - Confirmación de preautorización
    - Anulación de preautorización
    - Generación de tokens
    - Cargos de un clic
    - Cargos recurrentes
- **Operaciones de Cargo Bizum**
    - Creación de enlaces de pago
    - Devoluciones
- **Operaciones de Checkout**
    - Generación de enlaces de pago
    - Botón de pago

## Requerimientos

- Java 17 o superior
- Maven 3.6 o superior

## Instalación

Añade la siguiente dependencia a tu `pom.xml`:

```xml

<dependency>
    <groupId>es.openpay</groupId>
    <artifactId>wipop-java-client</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Configuración

### Configuración de Entorno

El cliente soporta dos entornos predefinidos:

- **SANDBOX**: `https://sand-api.wipop.es` - Para pruebas y desarrollo
- **PRODUCTION**: `https://api.wipop.es` - Para transacciones en vivo

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

### Configuración del Cliente

```java
import es.wipop.client.WipopClient;
import es.wipop.client.WipopClientConfiguration;
import es.wipop.client.WipopClientHttpConfiguration;

// Usando entorno sandbox con configuración HTTP por defecto
WipopClientConfiguration config = new WipopClientConfiguration(
      WipopClientConfiguration.Environment.SANDBOX,
      "your-merchant-id",
      "your-secret-key"
);

// Usando configuración HTTP personalizada para timeouts
WipopClientHttpConfiguration httpConfig = new WipopClientHttpConfiguration(10000, 45000);
WipopClientConfiguration configWithTimeouts = new WipopClientConfiguration(
      WipopClientConfiguration.Environment.SANDBOX,
      "your-merchant-id",
      "your-secret-key",
      httpConfig
);

WipopClient client = WipopClient.of(config);
```

### Configuración HTTP

El cliente soporta configuración de timeouts HTTP a través de `WipopClientHttpConfiguration`:

- **connectionRequestTimeout**: Timeout para solicitud de conexión (por defecto: 5 segundos)
- **responseTimeout**: Timeout para esperar respuestas (por defecto: 30 segundos)

```java
// Timeouts por defecto (5s, 30s)
WipopClientHttpConfiguration defaultConfig = new WipopClientHttpConfiguration();

// Timeouts personalizados (solicitud conexión, respuesta en milisegundos)
WipopClientHttpConfiguration customConfig = new WipopClientHttpConfiguration(10000, 45000);
```

## Operaciones de Cargo

### Métodos de Ayuda

#### Crear un Cliente

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

#### Crear un Terminal

```java
private Terminal createTerminal() {
    Terminal terminal = new Terminal();
    terminal.setId("1");
    return terminal;
}
```

### Métodos de Pago

El cliente soporta diferentes métodos de pago a través del parámetro `method()`:

#### Crear un cargo con TARJETA

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
      .description("Test pago con tarjeta")
      .productType(ProductType.PAYMENT_LINK)
      .originChannel(OriginChannel.API)
      .capture(true)
      .customer(createCustomer())
      .terminal(createTerminal());

Charge charge = client.chargeOperation().create(params);
```

#### Crear un cargo con BIZUM

```java
// Create charge with BIZUM method
CreateChargeParams params = new CreateChargeParams()
      .method(ChargeMethod.BIZUM)  // Specify BIZUM payment method
      .amount(BigDecimal.ONE)
      .currency(Currency.EUR)
      .orderId("1234abcdefgh")
      .description("Test pago Bizum")
      .productType(ProductType.PAYMENT_LINK)
      .originChannel(OriginChannel.API)
      .capture(true)
      .customer(createCustomer())
      .terminal(createTerminal());

Charge charge = client.chargeOperation().create(params);
```

### Crear cargo con generación de token

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

### Crear cargo de un clic

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

### Crear cargo recurrente

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

### Crear preautorización

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

#### Confirmar un cargo pendiente

```java
import es.wipop.client.operations.charge.params.ConfirmChargeParams;

ConfirmChargeParams confirmParams = new ConfirmChargeParams()
      .tokenId("k000000000000000000")
      .deviceSessionId(UUID.randomUUID().toString())
      .paymentsType(PaymentType.NONE);

Charge confirmedCharge = client.chargeOperation()
      .confirm("tr000000000000000000", confirmParams);
```

#### Reembolsar un cargo

```java
import es.wipop.client.operations.charge.params.RefundParams;

RefundParams refundParams = new RefundParams()
      .amount(BigDecimal.ONE);

Charge refundedCharge = client.chargeOperation()
      .refund("tr000000000000000000", refundParams);
```

#### Reversar un cargo

```java
import es.wipop.client.operations.charge.params.ReversalParams;

ReversalParams reversalParams = new ReversalParams()
      .reason(ReversalReason.PRE_REVERSAL);

Charge reversedCharge = client.chargeOperation()
      .reversal("tr000000000000000000", reversalParams);
```

#### Capturar un cargo preautorizado

```java
import es.wipop.client.operations.charge.params.CaptureParams;

CaptureParams captureParams = new CaptureParams()
      .amount(BigDecimal.ONE);

Charge capturedCharge = client.chargeOperation()
      .capture("tr000000000000000000", captureParams);
```

### Operaciones de Checkout

#### Crear un checkout

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

### Manejo de Errores

```java
import es.wipop.client.exception.WipopClientException;

try{
    Charge charge = client.chargeOperation().create(params);
    // Process successful charge
} catch(WipopClientException e) {
   // Handle the wipop client exception
}
```

## Referencia de la API

### Clases Principales

- **WipopClient**: Cliente principal para operaciones de API
- **WipopClientConfiguration**: Configuración del cliente y ajustes de entorno
- **ChargeOperation**: Interfaz para operaciones relacionadas con cargos
- **CheckoutOperation**: Interfaz para operaciones relacionadas con checkout

### Modelos de Dominio

- **Charge**: Representa un cargo de pago
- **Checkout**: Representa una sesión de checkout
- **Customer**: Información del cliente
- **Card**: Detalles de la tarjeta de pago
- **Address**: Información de dirección
- **Terminal**: Detalles del terminal de pago
- **ChargeMethod**: Enumeración de métodos de pago (CARD, BIZUM)

### Clases de Parámetros

- **CreateChargeParams**: Parámetros para crear cargos
- **ConfirmChargeParams**: Parámetros para confirmar cargos
- **RefundParams**: Parámetros para reembolsar cargos
- **ReversalParams**: Parámetros para reversar cargos
- **CaptureParams**: Parámetros para capturar cargos
- **CheckoutParams**: Parámetros para crear checkouts

## Contribuir

1. Haz fork del repositorio
2. Crea una rama de funcionalidad
3. Realiza tus cambios
4. Añade pruebas para la nueva funcionalidad
5. Envía un pull request

## Licencia

Este proyecto está licenciado bajo los términos especificados en el archivo de licencia del proyecto.

## Soporte

Para soporte y preguntas, consulta la documentación oficial de Wipop o contacta con el equipo de desarrollo.
