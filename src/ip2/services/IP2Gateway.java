package ip2.services;

import java.util.concurrent.TimeUnit;

import ip2.factory.ChannelReference;
import ip2.factory.MetaDataReference;
import ip2.factory.PaymentMethodReference;
import ip2.factory.ProductReference;
import ip2.factory.RequestPar;
import ip2.factory.RequestParams;
import ip2.factory.RequestReference;
import ip2.factory.TransactionRequest;
import ip2.helpers.IP2GatewayException;
import ip2.helpers.Response;
import ip2.interfaces.ResponseHandler;
import ip2.utils.Environment;


public class IP2Gateway extends GatewayImp implements ResponseHandler
{	

	public IP2Gateway(Environment environment, String userKey, String pass, String accountId, String subscriptionId) {
		super(environment, userKey, pass, accountId, subscriptionId);
	
	}

	public void requestCredit(TransactionRequest transactionRequest) throws IP2GatewayException
	{
		initiateTransaction(1,transactionRequest, IP2Gateway.this);
	}
	
	public void requestDebit(TransactionRequest transactionRequest) throws IP2GatewayException
	{
		initiateTransaction(0, transactionRequest, IP2Gateway.this);
	}
	
	public void requestTicket(TransactionRequest transactionRequest) throws IP2GatewayException
	{
		initiateTransaction(0, transactionRequest, IP2Gateway.this);
	}
	
	public void getAccount() throws IP2GatewayException
	{
		getAccountDetails(IP2Gateway.this);
	}
	
	public void getProducts(int offset, int count) throws IP2GatewayException
	{
		getProductDetails(offset, count, IP2Gateway.this);
	}
	
	public void getPaymentMethods(int offset, int count) throws IP2GatewayException
	{
		getPaymentMethodDetails(offset, count,IP2Gateway.this);
	}
	
	public void getCreditTransaction(String transactionId) throws IP2GatewayException
	{
		getCreditTrasanction(transactionId, IP2Gateway.this);
	}
	
	public void getCreditTransactionsByDate(String earlierDate, String laterDate, int offset, int count) throws IP2GatewayException
	{
		getCreditTransactionByDate(earlierDate, laterDate, offset, count, IP2Gateway.this);
	}
	
	public void getDebitTransaction(String transactionId) throws IP2GatewayException
	{
		getCreditTrasanction(transactionId, IP2Gateway.this);
	}
	
	public void getDebitTransactionsByDate(String earlierDate, String laterDate, int offset, int count) throws IP2GatewayException
	{
		getCreditTransactionByDate(earlierDate, laterDate, offset, count, IP2Gateway.this);
	}
	

	@Override
	public void setReadTimeout(int time, TimeUnit unit) {
		
		super.setReadTimeout(time, unit);
	}

	@Override
	public void setConnectTimeout(int time, TimeUnit unit) {
		
		super.setConnectTimeout(time, unit);
	}

	@Override
	public void setResponseListener(ResponseHandler response) {
		
		super.setResponseListener(response);
	}


	


	@Override
	public void callback(Response response) {
		// TODO Auto-generated method stub
		
	}


	



}
