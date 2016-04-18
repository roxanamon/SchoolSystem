package paymentapp;

public class PaymentApp 
{
	public ResponseCode makeCreditCardPayment(CreditCard creditCard, float amount)
	{
		String cardNumber = creditCard.getCardNumber();
		if(cardNumber.length() != 16)
		{
			return ResponseCode.BadRequest;
		}
		
		switch(cardNumber)
		{
		case "1111222233334444": 
			return ResponseCode.BarredCard; 
		case "2222333344441111": 
			return ResponseCode.Unauthorized;
		case "3333444411112222":
			return ResponseCode.UnknownError;
		default: 
			return ResponseCode.Success;
		}
	}

	public enum ResponseCode
	{
		Success(200),
		BadRequest(400),
		Unauthorized(401),
		BarredCard(403),
		UnknownError(500);

		private int id;

		private ResponseCode(int id)
		{
			this.id = id;
		}

		public int getId()
		{
			return this.id;
		}
	}
}
