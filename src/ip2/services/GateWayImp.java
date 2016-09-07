package ip2.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import intelworld.hmacauth.exception.EmptyOrNullException;
import intelworld.hmacauth.impl.Constants;
import intelworld.hmacauth.impl.HmacAuthHeadersImpl;
import ip2.factory.ChannelReference;
import ip2.factory.MetaDataReference;
import ip2.factory.PaymentMethodReference;
import ip2.factory.ProductReference;
import ip2.factory.RequestPar;
import ip2.factory.RequestParams;
import ip2.factory.RequestReference;
import ip2.factory.TransactionRequest;
import ip2.helpers.IP2GatewayException;
import ip2.helpers.NetworkBroker;
import ip2.helpers.Response;
import ip2.helpers.ResponseMessage;
import ip2.interfaces.ResponseHandler;
import ip2.utils.Environment;
import ip2.utils.IP2Constants;

public abstract class GateWayImp 
{

	private String userKey;
	private String pass;
	IP2Gateway gateway;
	ResponseHandler responseHandler;
	private int connectionTimeout;
	private int serverTimeout;
	private String accountId;
	private String subscriptionId;
	private ResponseMessage responseMessage;
	private Environment environment;
	
	private String PRODUCTION_ENV = "https://gemini-api.azurewebsites.net";
	private String SANDBOX_ENV = "http://ec2-54-148-117-189.us-west-2.compute.amazonaws.com:84";
	
	public GateWayImp(String userKey, String pass, Environment environment, String accountId, String subscriptionId)
	{
		this.userKey = userKey;
		this.pass = pass;
		this.environment = environment;
		
		this.accountId = accountId;
		this.subscriptionId = subscriptionId;
		
	}
	
	
	public void setReadTimeout(int time, TimeUnit unit)
	{
		this.serverTimeout = (int)unit.toMillis(time);
	}
	public void setConnectTimeout(int time,TimeUnit unit)
	{
		this.connectionTimeout = (int)unit.toMillis(time);
	}
	
	protected <T>  void  initiateTransaction(int requestType, TransactionRequest request, T context) throws IP2GatewayException
	{
		responseHandler = (ResponseHandler)context;
		
		prepareTrasaction(requestType, request.getProductReference(), request.getChannelReference(), 
				request.getPaymentMethodRefrence(), request.getMetaDataReference(), request.getRequestParams(), request.getRequestReference());
	}
	
	
	
	
	private void prepareTrasaction(int requestType, ProductReference prodRef, ChannelReference chanRef,
			PaymentMethodReference payRef, MetaDataReference metaRef, RequestParams params, RequestReference reqRef) throws IP2GatewayException
	{
		JSONObject object = new JSONObject();
		object.put("BatchId", params.getBatchId());
		object.put("requestId", params.getRequestId());
		object.put("PaymentMethodId", params.getPaymentMethodId());
		object.put("ProductId", params.getProductId());
		object.put("Amount", params.getAmount());
		object.put("CurrencyCode", params.getCurrencyCode());
		object.put("CountryCode", params.getCountryCode());
		object.put("Memo", params.getMemo());
		object.put("ChannelId", params.getChannelId());
		object.put("CustomerId", params.getCustomerId());
		
		HashMap<String, String> prodRf = prodRef.getProductReference();
		JSONObject prodObject = new JSONObject();
		if(prodRf != null)
		{
			for(Map.Entry<String, String> map: prodRf.entrySet())
			{
				prodObject.put(map.getKey(), map.getValue());
				System.out.println(map.getKey() +"="+map.getValue());
			}
		}
		
		object.put("ProductReference", prodObject);
		
		HashMap<String, String> chanRf = chanRef.getChannelRefence();
		JSONObject chanObject = new JSONObject();
		if(chanRf != null)
		{
			for(Map.Entry<String, String> map: chanRf.entrySet())
			{
				chanObject.put(map.getKey(), map.getValue());
			}
		}
		
		object.put("ChannelReference", chanObject);
		
		HashMap<String, String> payRf = payRef.getPaymentReference();
		JSONObject payObject = new JSONObject();
		if(payRf != null)
		{
			for(Map.Entry<String, String> map: payRf.entrySet())
			{
				payObject.put(map.getKey(), map.getValue());
			}
		}
		
		object.put("PaymentMethodReference", payObject);
		
		HashMap<String, String> metaRf = metaRef.getMetaDataReference();
		JSONObject metaObject = new JSONObject();
		if(metaRf != null)
		{
			for(Map.Entry<String, String> map: metaRf.entrySet())
			{
				metaObject.put(map.getKey(), map.getValue());
			}
		}
		
		object.put("MetaData", metaObject);
		
		
		HashMap<String, String> reqRf = reqRef.getRequestReference();
		JSONObject reqObject = new JSONObject();
		if(reqRf != null)
		{
			for(Map.Entry<String, String> map: reqRf.entrySet())
			{
				reqObject.put(map.getKey(), map.getValue());
			}
		}
		
		object.put("RequestReference", reqObject);
		
		
		String resource = generateResource(requestType);
		
		
	    try {
			startTransaction(object.toString(), resource, generateHmacHeaders(IP2Constants.POST, resource), IP2Constants.POST);
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
		}
		
	}
	
	private String generateResource(int requestType)
	{
		switch(requestType)
		{
		case 0:
			return "/api/v2/transactions/debits/".concat(subscriptionId).concat("/").concat(accountId);
			
		case 1:
			return "/api/v2/transactions/credits/".concat(subscriptionId).concat("/").concat(accountId);
	
		default:
			return null;
		}
	}
	
	private void startTransaction(String entity, String resource, Map<String, String> headers, String method) throws MalformedURLException, IP2GatewayException
	{
		switch(environment)
		{
		case PRODUCTION:
			try {
				returnResponse(NetworkBroker.productionHttpRequest(method, PRODUCTION_ENV.concat(resource), headers, entity, serverTimeout, connectionTimeout));
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		    break;
		case SANDBOX:
			
			try {
				
				returnResponse(NetworkBroker.sandboxHttpRequest(method, SANDBOX_ENV.concat(resource), headers, entity, serverTimeout, connectionTimeout));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		
		}
			
	}
	
	
	
	public <T> void getAccountDetails(T callback) throws IP2GatewayException {
		
		responseHandler = (ResponseHandler)callback;
		
		String accountResource = "/api/v2/accounts/".concat(subscriptionId).concat("/").concat(accountId);
		Map<String, String> headers = generateHmacHeaders(IP2Constants.GET, accountResource);
		switch(environment)
		{
		case PRODUCTION:
			 try {
				returnResponse(NetworkBroker.productionHttpRequest(IP2Constants.GET, PRODUCTION_ENV.concat(accountResource), headers, null, serverTimeout, connectionTimeout));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case SANDBOX:
			try {
				returnResponse(NetworkBroker.sandboxHttpRequest(IP2Constants.GET, SANDBOX_ENV.concat(accountResource), headers, null, serverTimeout, connectionTimeout));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			break;
		}
		
		
		
	}
	
	
	private void returnResponse(ResponseMessage responseMessage)
	{
		this.responseMessage = responseMessage;
		setResponseListener(responseHandler);
	}
	
	
	
	private Map<String, String> generateHmacHeaders(String requestMethod,
			String requestUri) throws IP2GatewayException {

		HmacAuthHeadersImpl hmac = new HmacAuthHeadersImpl(
				Constants.MEDIA_JSON, userKey, pass,
				Constants.HMAC_SHA512, Constants.MD_SHA_512, Constants.MD5);

		try {
			return hmac.getHeadersAsMap(requestMethod, requestUri);
			
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| EmptyOrNullException | UnsupportedEncodingException ex) {
			throw new IP2GatewayException(
					"Exception while generating Hmac Headers", ex);
		}

	}
	
	
	public void setResponseListener(ResponseHandler responseHandler)
	{
		Response response = new Response();
		response.setMessage(responseMessage.getResponse());
		response.setStatusCode(responseMessage.getStatusCode());
		responseHandler.callback(response);
	}
	
	
	
		

}
