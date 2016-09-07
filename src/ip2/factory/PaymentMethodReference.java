package ip2.factory;

import java.util.HashMap;

public interface PaymentMethodReference {

	void setPaymentReference(HashMap<String, String> paymentReference);
	HashMap<String, String> getPaymentReference();
}
