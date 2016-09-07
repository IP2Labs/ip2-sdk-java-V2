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


public class IP2Gateway extends GateWayImp implements ResponseHandler
{	

	public IP2Gateway(String userKey, String pass, Environment environment, String accountId, String subscriptionId) {
		super(userKey, pass, environment, accountId, subscriptionId);
	
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
