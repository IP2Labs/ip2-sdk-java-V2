package ip2.sample;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import ip2.factory.ChannelRef;
import ip2.factory.ChannelReference;
import ip2.factory.MetaDataRef;
import ip2.factory.MetaDataReference;
import ip2.factory.PaymentMethodReference;
import ip2.factory.PaymentRef;
import ip2.factory.ProductRef;
import ip2.factory.ProductReference;
import ip2.factory.RequestPar;
import ip2.factory.RequestParams;
import ip2.factory.RequestRef;
import ip2.factory.RequestReference;
import ip2.helpers.IP2GatewayException;
import ip2.helpers.IP2GatewayUtils;
import ip2.helpers.Response;
import ip2.interfaces.ResponseHandler;
import ip2.services.IP2Gateway;
import ip2.utils.*;

public class Test
{
	public static void main(String[] args)
	{
		ip2.factory.TransactionRequest request = new ip2.factory.TransactionRequest();

        PaymentMethodReference paymentRef = new PaymentRef();
        ChannelReference channelRef = new ChannelRef();
        ProductReference productRef = new ProductRef();
        MetaDataReference metaDataRef = new MetaDataRef();
        RequestParams requestPar = new RequestPar();
        RequestReference requestRef = new RequestRef();

        requestPar.setBatchId(IP2GatewayUtils.generateAlphaNumeric());
        requestPar.setRequestId(IP2GatewayUtils.generateUUID());
        requestPar.setPaymentMethodId("IP2WALLETUG");



        requestPar.setAmount("500");
        requestPar.setCurrencyCode("UGS");
        requestPar.setCountryCode("256");
        requestPar.setMemo("AIRTIME FOR ");
        requestPar.setChannelId("ANDROIDAPP");
        requestPar.setCustomerId(IP2GatewayUtils.generateUUID());

        HashMap<String, String> requestReference = new HashMap<String, String>();
        requestReference.put("PhoneNumber", "256784703425");
        requestReference.put("ReceiptId", IP2GatewayUtils.generateAlphaNumeric());

        HashMap<String, String> paymentMethodReference = new HashMap<String, String>();
        paymentMethodReference.put("Message", "AIRTIME FOR ");


        HashMap<String, String> metaData = new HashMap<String, String>();
        metaData.put("CreatedOn", "2017-03-03 12:12:02");

        HashMap<String, String> productReference = new HashMap<String, String>();
        
        productReference.put("PhoneNumber", "256784703425");
        requestPar.setProductId("MTNAIRTIMEUG");
       
        HashMap<String, String> channelReference = new HashMap<String, String>();
        channelReference.put("AppId", IP2GatewayUtils.generateUUID());
        channelReference.put("DeviceType", IP2GatewayUtils.generateUUID());

        requestRef.setRequestReference(requestReference);
        paymentRef.setPaymentReference(paymentMethodReference);
        metaDataRef.setMetaDataReference(metaData);
        productRef.setProductReference(productReference);
        channelRef.setChannelReference(channelReference);

        request.setChannelReference(channelRef);
        request.setPaymentMethodRefrence(paymentRef);
        request.setProductReference(productRef);
        request.setMetaDataReference(metaDataRef);
        request.setRequestParams(requestPar);
        request.setRequestReference(requestRef);
        
        IP2Gateway gateway = new IP2Gateway(Environment.SANDBOX, "888494", "8888994", "256784703425", "85883994994");
        gateway.setReadTimeout(2, TimeUnit.MILLISECONDS);
        try {
			gateway.getAccountBalance("8899898", new ResponseHandler() {
				
				@Override
				public void callback(Response response) {
					
					System.out.println(response.getMessage());
				}
			});
		} catch (IP2GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
