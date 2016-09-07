package ip2.factory;

public class TransactionRequest 
{
	private PaymentMethodReference paymentMethodRefrence;
	private ChannelReference channelReference;
	private ProductReference productReference;
	private MetaDataReference metaDataReference;
	private RequestParams requestParams;
	private RequestReference requestReference;
	
	
	public PaymentMethodReference getPaymentMethodRefrence() {
		return paymentMethodRefrence;
	}
	public void setPaymentMethodRefrence(
			PaymentMethodReference paymentMethodRefrence) {
		this.paymentMethodRefrence = paymentMethodRefrence;
	}
	public ChannelReference getChannelReference() {
		return channelReference;
	}
	public void setChannelReference(ChannelReference channelReference) {
		this.channelReference = channelReference;
	}
	public ProductReference getProductReference() {
		return productReference;
	}
	public void setProductReference(ProductReference productReference) {
		this.productReference = productReference;
	}
	public MetaDataReference getMetaDataReference() {
		return metaDataReference;
	}
	public void setMetaDataReference(MetaDataReference metaDataReference) {
		this.metaDataReference = metaDataReference;
	}
	public RequestParams getRequestParams() {
		return requestParams;
	}
	public void setRequestParams(RequestParams requestParams) {
		this.requestParams = requestParams;
	}
	public RequestReference getRequestReference() {
		return requestReference;
	}
	public void setRequestReference(RequestReference requestReference) {
		this.requestReference = requestReference;
	}
	
	

}
