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


public class IP2Gateway extends GatewayImp 
{	

	public IP2Gateway(Environment environment, String userKey, String pass, String accountId, String subscriptionId) {
		super(environment, userKey, pass, accountId, subscriptionId);
	
	}

	public void requestCredit(TransactionRequest transactionRequest, ResponseHandler responseHandler) throws IP2GatewayException
	{
	
		
		initiateTransaction(1,transactionRequest, responseHandler, IP2Gateway.this);
	}
	
	public void requestDebit(TransactionRequest transactionRequest, ResponseHandler responseHandler) throws IP2GatewayException
	{
		initiateTransaction(0, transactionRequest, responseHandler, IP2Gateway.this);
	}
	
	public void requestTicket(TransactionRequest transactionRequest, ResponseHandler responseHandler) throws IP2GatewayException
	{
		initiateTransaction(0, transactionRequest, responseHandler,  IP2Gateway.this);
	}
	
	public void getAccount(ResponseHandler responseHandler) throws IP2GatewayException
	{
		getAccountDetails(responseHandler, IP2Gateway.this);
	}
	
	public void getProducts(int offset, int count, ResponseHandler responseHandler) throws IP2GatewayException
	{
		getProductDetails(offset, count, responseHandler,IP2Gateway.this);
	}
	
	public void getPaymentMethods(int offset, int count,ResponseHandler responseHandler) throws IP2GatewayException
	{
		getPaymentMethodDetails(offset, count, responseHandler, IP2Gateway.this);
	}
	
	public void getCreditTransaction(String transactionId, ResponseHandler responseHandler) throws IP2GatewayException
	{
		getCreditTrasanction(transactionId,responseHandler, IP2Gateway.this);
	}
	
	public void getCreditTransactionsByDate(String earlierDate, String laterDate, int offset, int count, ResponseHandler responseHandler) throws IP2GatewayException
	{
		getCreditTransactionByDate(earlierDate, laterDate, offset, count, responseHandler, IP2Gateway.this);
	}
	
	public void getDebitTransaction(String transactionId, ResponseHandler responseHandler) throws IP2GatewayException
	{
		getCreditTrasanction(transactionId, responseHandler, IP2Gateway.this);
	}
	
	public void getDebitTransactionsByDate(String earlierDate, String laterDate, int offset, int count, ResponseHandler responseHandler) throws IP2GatewayException
	{
		getCreditTransactionByDate(earlierDate, laterDate, offset, count, responseHandler, IP2Gateway.this);
	}
	
	public void getAccountBalance(String accountId, ResponseHandler responseHandler) throws IP2GatewayException
	{
		getAccountBalance(accountId, responseHandler, IP2Gateway.this);
	}
	

	@Override
	public void setReadTimeout(int time, TimeUnit unit) {
		
		super.setReadTimeout(time, unit);
	}

	@Override
	public void setConnectTimeout(int time, TimeUnit unit) {
		
		super.setConnectTimeout(time, unit);
	}

	



	



}
