package es.wipop.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a financial transaction.
 */
public class Transaction implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Transaction identifier */
   private String id;

   /** Payment method used */
   @JsonProperty("method")
   private String method;

   /** Transaction amount */
   private BigDecimal amount;

   /** Date when the transaction was created */
   @JsonProperty("creation_date")
   private LocalDate creationDate;

   /** Date when the operation was performed */
   @JsonProperty("operation_date")
   private LocalDate operationDate;

   /** Current status of the transaction */
   private String status;

   /** Transaction description */
   private String description;

   /** Type of transaction */
   @JsonProperty("transaction_type")
   private String transactionType;

   /** Type of operation */
   @JsonProperty("operation_type")
   private String operationType;

   /** Error message if transaction failed */
   @JsonProperty("error_message")
   private String errorMessage;

   /** Error code if transaction failed */
   @JsonProperty("error_code")
   private Integer errorCode;

   /** Card information used in the transaction */
   private Card card;

   /** Authorization code */
   private String authorization;

   /** Order identifier */
   @JsonProperty("order_id")
   private String orderId;

   /** Customer identifier */
   @JsonProperty("customer_id")
   private String customerId;

   /** Due date for the transaction */
   @JsonProperty("due_date")
   private LocalDate dueDate;

   /** Currency code */
   private String currency;

   /** Origin channel of the transaction */
   @JsonProperty("origin_channel")
   private String originChannel;

   /**
    * Gets the transaction ID.
    *
    * @return the transaction ID
    */
   public String getId() {
      return id;
   }

   /**
    * Sets the transaction ID.
    *
    * @param id the transaction ID to set
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Gets the payment method.
    *
    * @return the payment method
    */
   public String getMethod() {
      return method;
   }

   /**
    * Sets the payment method.
    *
    * @param method the payment method to set
    */
   public void setMethod(String method) {
      this.method = method;
   }

   /**
    * Gets the transaction amount.
    *
    * @return the amount
    */
   public BigDecimal getAmount() {
      return amount;
   }

   /**
    * Sets the transaction amount.
    *
    * @param amount the amount to set
    */
   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }

   /**
    * Gets the creation date.
    *
    * @return the creation date
    */
   public LocalDate getCreationDate() {
      return creationDate;
   }

   /**
    * Sets the creation date.
    *
    * @param creationDate the creation date to set
    */
   public void setCreationDate(LocalDate creationDate) {
      this.creationDate = creationDate;
   }

   /**
    * Gets the operation date.
    *
    * @return the operation date
    */
   public LocalDate getOperationDate() {
      return operationDate;
   }

   /**
    * Sets the operation date.
    *
    * @param operationDate the operation date to set
    */
   public void setOperationDate(LocalDate operationDate) {
      this.operationDate = operationDate;
   }

   /**
    * Gets the transaction status.
    *
    * @return the status
    */
   public String getStatus() {
      return status;
   }

   /**
    * Sets the transaction status.
    *
    * @param status the status to set
    */
   public void setStatus(String status) {
      this.status = status;
   }

   /**
    * Gets the transaction description.
    *
    * @return the description
    */
   public String getDescription() {
      return description;
   }

   /**
    * Sets the transaction description.
    *
    * @param description the description to set
    */
   public void setDescription(String description) {
      this.description = description;
   }

   /**
    * Gets the transaction type.
    *
    * @return the transaction type
    */
   public String getTransactionType() {
      return transactionType;
   }

   /**
    * Sets the transaction type.
    *
    * @param transactionType the transaction type to set
    */
   public void setTransactionType(String transactionType) {
      this.transactionType = transactionType;
   }

   /**
    * Gets the operation type.
    *
    * @return the operation type
    */
   public String getOperationType() {
      return operationType;
   }

   /**
    * Sets the operation type.
    *
    * @param operationType the operation type to set
    */
   public void setOperationType(String operationType) {
      this.operationType = operationType;
   }

   /**
    * Gets the error message.
    *
    * @return the error message, or null if no error
    */
   public String getErrorMessage() {
      return errorMessage;
   }

   /**
    * Sets the error message.
    *
    * @param errorMessage the error message to set
    */
   public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
   }

   /**
    * Gets the error code.
    *
    * @return the error code, or null if no error
    */
   public Integer getErrorCode() {
      return errorCode;
   }

   /**
    * Sets the error code.
    *
    * @param errorCode the error code to set
    */
   public void setErrorCode(Integer errorCode) {
      this.errorCode = errorCode;
   }

   /**
    * Gets the card information.
    *
    * @return the card details
    */
   public Card getCard() {
      return card;
   }

   /**
    * Sets the card information.
    *
    * @param card the card details to set
    */
   public void setCard(Card card) {
      this.card = card;
   }

   /**
    * Gets the authorization code.
    *
    * @return the authorization code
    */
   public String getAuthorization() {
      return authorization;
   }

   /**
    * Sets the authorization code.
    *
    * @param authorization the authorization code to set
    */
   public void setAuthorization(String authorization) {
      this.authorization = authorization;
   }

   /**
    * Gets the order ID.
    *
    * @return the order ID
    */
   public String getOrderId() {
      return orderId;
   }

   /**
    * Sets the order ID.
    *
    * @param orderId the order ID to set
    */
   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   /**
    * Gets the customer ID.
    *
    * @return the customer ID
    */
   public String getCustomerId() {
      return customerId;
   }

   /**
    * Sets the customer ID.
    *
    * @param customerId the customer ID to set
    */
   public void setCustomerId(String customerId) {
      this.customerId = customerId;
   }

   /**
    * Gets the due date.
    *
    * @return the due date
    */
   public LocalDate getDueDate() {
      return dueDate;
   }

   /**
    * Sets the due date.
    *
    * @param dueDate the due date to set
    */
   public void setDueDate(LocalDate dueDate) {
      this.dueDate = dueDate;
   }

   /**
    * Gets the currency code.
    *
    * @return the currency code
    */
   public String getCurrency() {
      return currency;
   }

   /**
    * Sets the currency code.
    *
    * @param currency the currency code to set
    */
   public void setCurrency(String currency) {
      this.currency = currency;
   }

   /**
    * Gets the origin channel.
    *
    * @return the origin channel
    */
   public String getOriginChannel() {
      return originChannel;
   }

   /**
    * Sets the origin channel.
    *
    * @param originChannel the origin channel to set
    */
   public void setOriginChannel(String originChannel) {
      this.originChannel = originChannel;
   }
}
