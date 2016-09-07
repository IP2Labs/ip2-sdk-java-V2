package ip2.factory;

import java.util.HashMap;

public class PaymentRef implements PaymentMethodReference
{
	
	private HashMap<String, String> paymentReference;

	@Override
	public void setPaymentReference(HashMap<String, String> paymentReference) {
	
		this.paymentReference = paymentReference;
	}

	@Override
	public HashMap<String, String> getPaymentReference() {
		
		return this.paymentReference;
	}
	
}
