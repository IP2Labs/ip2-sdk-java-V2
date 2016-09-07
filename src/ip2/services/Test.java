package ip2.services;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Before;

import ip2.factory.ChannelRef;
import ip2.factory.ChannelReference;
import ip2.factory.MetaDataRef;
import ip2.factory.MetaDataReference;
import ip2.factory.PaymentRef;
import ip2.factory.PaymentMethodReference;
import ip2.factory.ProductRef;
import ip2.factory.ProductReference;
import ip2.factory.RequestPar;
import ip2.factory.RequestParams;
import ip2.factory.RequestRef;
import ip2.factory.RequestReference;
import ip2.factory.TransactionRequest;
import ip2.helpers.IP2GatewayException;
import ip2.helpers.IP2GatewayUtils;
import ip2.helpers.Reference;
import ip2.helpers.Response;
import ip2.interfaces.ResponseHandler;
import ip2.utils.Environment;
import ip2.utils.IP2Constants;

public class Test 
{
	

	@org.junit.Test
	public void prepare()
	{
		IP2Gateway gateWay = new IP2Gateway("6E3FD4F5D57F4F2FA4258E549C579CE6", "password", Environment.SANDBOX, "256776120055", "F6E4CA2A3F214BD886D2D896FE81B331V2");
		
		
		gateWay.setConnectTimeout(20, TimeUnit.SECONDS);
		gateWay.setReadTimeout(20, TimeUnit.SECONDS);
		
        PaymentMethodReference paymentRef = new PaymentRef();
        ChannelReference channelRef = new ChannelRef();
        ProductReference productRef = new ProductRef();
        MetaDataReference metaDataRef = new MetaDataRef();
        RequestParams requestPar = new RequestPar();
        RequestReference requestRef = new RequestRef();
        
        requestPar.setBatchId(IP2GatewayUtils.generateUUID());
        requestPar.setRequestId(IP2GatewayUtils.generateUUID());
        requestPar.setPaymentMethodId("IP2WALLETUG");
        requestPar.setProductId("AIRTELBUNDLESUG");
        requestPar.setAmount("500");
        requestPar.setCurrencyCode("UGX");
        requestPar.setCountryCode("256");
        requestPar.setMemo("Airtime buy");
        requestPar.setChannelId("USSD");
        requestPar.setCustomerId("488993");
        
        HashMap<String, String> chanMap = new HashMap<String, String>();
        chanMap.put("AppId", "USSD9598894");
        chanMap.put("DeviceType", "Techno");
        
        channelRef.setChannelReference(chanMap);
        
        HashMap<String, String> payMap = new HashMap<String, String>();
        payMap.put("SrcMsisdn", "256706829949");
        payMap.put("DstMsisdn", "256706493907");
        
        
        paymentRef.setPaymentReference(payMap);
        
        HashMap<String, String> metaMap = new HashMap<String, String>();
        metaMap.put("CreatedOn", "12-6-2016");
        
        metaDataRef.setMetaDataReference(metaMap);
        
        HashMap<String, String> prodMap = new HashMap<String, String>();
        /*prodMap.put("PhoneNumber", "256784703425");
        prodMap.put("VoucherId", "8488399493");*/
        prodMap.put("MSISDN", "256778392429");
        prodMap.put("BundleCode", "DATA10");
        prodMap.put("Amount", "500");
        
        productRef.setProductReference(prodMap);
        
        HashMap<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("PhoneNumber","0705370161");
        reqMap.put("ReceiptId","1273");
        
        requestRef.setRequestReference(reqMap);
        
        TransactionRequest request = new TransactionRequest();
        request.setChannelReference(channelRef);
        request.setPaymentMethodRefrence(paymentRef);
        request.setProductReference(productRef);
        request.setMetaDataReference(metaDataRef);
        request.setRequestParams(requestPar);
        request.setRequestReference(requestRef);
		
		try {
			//gateWay.requestTicket(request);
			gateWay.getAccount();
			
		} catch (IP2GatewayException e) {
			
		}
		
		gateWay.setResponseListener(new ResponseHandler() {
			
			@Override
			public void callback(Response response) {
				
				System.out.println(response.getMessage() + " "+ response.getStatusCode());
			}
		});
		
	}


}
