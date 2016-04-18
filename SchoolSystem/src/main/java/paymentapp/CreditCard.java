package paymentapp;

import java.time.LocalDateTime;

public class CreditCard {
	
	private String cardHolderName;
	private CreditCardType cardType;
	private String cardNumber;
	private LocalDateTime expirationDate;
	private String ccCode;
	
	public CreditCard(String cardHolderName, CreditCardType cardType, String cardNumber, LocalDateTime expirationDate, String ccCode)
	{
		setCardHolderName(cardHolderName);
		setCardType(cardType);
		setCardNumber(cardNumber);
		setExpirationDate(expirationDate);
		setCcCode(ccCode);
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public CreditCardType getCardType() {
		return cardType;
	}

	public void setCardType(CreditCardType cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCcCode() {
		return ccCode;
	}

	public void setCcCode(String ccCode) {
		this.ccCode = ccCode;
	}
}
