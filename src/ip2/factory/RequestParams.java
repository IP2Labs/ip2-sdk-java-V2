package ip2.factory;

public interface RequestParams {

	void setBatchId(String batchId);
	String getBatchId();
	void setRequestId(String requestId);
	String getRequestId();
	void setPaymentMethodId(String paymentMethodId);
	String getPaymentMethodId();
	void setProductId(String productId);
	String getProductId();
	void setAmount(String amount);
	String getAmount();
	void setCurrencyCode(String currencyCode);
	String getCurrencyCode();
	void setCountryCode(String countryCode);
	String getCountryCode();
	void setMemo(String memo);
	String getMemo();
	void setChannelId(String channelId);
	String getChannelId();
	void setCustomerId(String customerId);
	String getCustomerId();
}
