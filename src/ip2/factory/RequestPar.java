package ip2.factory;

public class RequestPar implements RequestParams
{
	private String batchId;
	private String requestId;
	private String paymentMethodId;
	private String productId;
	private String amount;
	private String currencyCode;
	private String countryCode;
	private String memo;
	private String channelId;
	private String customerId;
	
	@Override
	public String getBatchId() {
		return batchId;
	}
	
	@Override
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	@Override
	public String getRequestId() {
		return requestId;
	}
	
	@Override
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	@Override
	public String getPaymentMethodId() {
		return paymentMethodId;
	}
	
	@Override
	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	
	@Override
	public String getProductId() {
		return productId;
	}
	
	@Override
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Override
	public String getAmount() {
		return amount;
	}
	
	@Override
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Override
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	@Override
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@Override
	public String getCountryCode() {
		return countryCode;
	}
	
	@Override
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Override
	public String getMemo() {
		return memo;
	}
	
	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String getChannelId() {
		return channelId;
	}
	
	@Override
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	@Override
	public String getCustomerId() {
		return customerId;
	}
	
	@Override
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	

}
