package ip2.helpers;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class NetworkBroker
{
	/**
	 * Text encoding used when getting responses from return HTTP response
	 * InputStream
	 */
	private static final String ENCODING = "UTF-8";
	private static final String genError = "An error has occurred";

	/**
	 * Returns a String array of returned entity response message and the
	 * response code to the invoker of the service This method should be called
	 * in the event that the user does wants to specify their own timeouts for
	 * the HTTP request
	 *
	 * @param http_method
	 *            Request method to be used in the request
	 * @param url_
	 *            The endpoint URL to be called
	 * @param headers
	 *            Name value pairs of header values to be added to the request
	 * @param entity
	 *            If the request requires any raw entity, it should be passed
	 *            with the entity parameter
	 * @param read_timeout
	 *            Any read timeouts to be specified
	 * @param connect_timeout
	 *            Any connect timeouts to be specified
	 * @return String array of response body (index 0) and response code (index
	 *         1)
	 * @throws MalformedURLException
	 *             When the URL is not as expected by the underlying HTTP
	 *             service, this exception will be thrown
	 * @throws IOException
	 *             Any input or output errors will raise IOException
	 * 
	 */
	public static ResponseMessage productionHttpRequest(String http_method,
			String url_, Map<String, String> headers, String entity,
			int read_timeout, int connect_timeout)
			throws MalformedURLException, IOException, IP2GatewayException {

		URL m_url = new URL(url_);


		HttpsURLConnection connection = null;
		int responseCode = 0;
		ResponseMessage message = new ResponseMessage();
		OutputStreamWriter out = null;
		try {
			connection = (HttpsURLConnection) m_url
					.openConnection();

			connection.setReadTimeout(read_timeout);
			connection.setConnectTimeout(connect_timeout);

			connection.setRequestMethod(http_method);
			if(headers != null && !headers.isEmpty())
			{
				for(Map.Entry<String, String> map : headers.entrySet())
				{
					connection.setRequestProperty((String) map.getKey(),
							(String) map.getValue());
				}
			}
			
			if(http_method.equals(HttpMethods.POST.name()))
			{
				connection.setDoOutput(true);

				if (entity != null) {

					out = new OutputStreamWriter(connection.getOutputStream()); 
					out.write(entity);
					out.flush();
					
				}
			}

			

			final InputStream is;
			responseCode = connection.getResponseCode();
			

			if (responseCode != 201) {
				is = connection.getErrorStream();
			} else {
				is = connection.getInputStream();
			}
			final String resp = getResponseMessage(is);
			
			message.setResponse(resp);
			message.setStatusCode(responseCode);
			
			if(out != null)
			{
				out.close();
			}
			
			return message;

		} catch (SocketTimeoutException ex) {
			if(connection != null)
			{
				String s = ex.toString();
                if(s != null)
                {
                    String[] splitS = s.split("\\.");

                    StringBuilder builder = new StringBuilder();
                    int i = 0;
                    for (String str : splitS[2].split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
                        if(i>0)
                        {
                           str = str.toLowerCase();
                        }
                        if(i ==2)
                        {
                            builder.append(str);
                        }
                        else
                        {
                            builder.append(str+" ");
                        }
                        i++;
                    }
                    message.setResponse(builder.toString());
    				message.setStatusCode(-1);
    			
                }
                else
                {
                	message.setResponse(genError);
    				message.setStatusCode(-1);
                }
			}
			if(out != null)
			{
				out.close();
			}
			return message;
			
		} catch (IOException ex) 
		{
			if(responseCode == 0)
			{
				message.setResponse("Connection to server failed, make sure you can connect to internet and try again.");
				message.setStatusCode(-1);
			}
			else
			{
				if(ex.getMessage() != null)
				{
					message.setResponse(ex.getMessage());
					message.setStatusCode(-1);
				}
				else
				{
					message.setResponse(genError);
					message.setStatusCode(-1);
				}
			}
			
			if(out != null)
			{
				out.close();
			}
			
			return message;
		}
		
		catch (Exception ex) {

			if(responseCode == 0)
			{
				message.setResponse("Connection to server failed, make sure you can connect to internet and try again.");
				message.setStatusCode(-1);
			}
			else
			{
			    if(ex.getMessage() != null)
				{
					message.setResponse(ex.getMessage());
					message.setStatusCode(-1);
				}
				else
				{
					message.setResponse(genError);
					message.setStatusCode(-1);
				}
			}
			
			if(out != null)
			{
				out.close();
			}
			
			return message;
		}
		

	}

	public static ResponseMessage sandboxHttpRequest(String http_method,
			String url_, Map<String, String> headers, String entity,
			int read_timeout, int connect_timeout)
			throws MalformedURLException, IOException, IP2GatewayException {

		URL m_url = new URL(url_);

		ResponseMessage message = new ResponseMessage();
		HttpURLConnection connection = null;
		OutputStream os = null;
		int responseCode = 0;
		
		try {
			connection = (HttpURLConnection) m_url.openConnection();

			connection.setReadTimeout(read_timeout);
			connection.setConnectTimeout(connect_timeout);

			connection.setRequestMethod(http_method);

			if (headers != null && !headers.isEmpty()) {
				
				for(Map.Entry<String, String> map : headers.entrySet())
				{
					connection.setRequestProperty(map.getKey(), map.getValue());
				}
				
			}

			if (http_method.equals(HttpMethods.POST.name())) {
				connection.setDoOutput(true);

				if (entity != null) {
					connection.setRequestProperty("Content-Type",
							"application/json; charset=UTF-8");

					os = connection.getOutputStream();
				    os.write(entity.getBytes("UTF-8"));
				    os.flush();
					

				}
			}

			final InputStream is;
			responseCode = connection.getResponseCode();
			
			if (responseCode != 201 && responseCode != 200) {
				is = connection.getErrorStream();

			} else {
				is = connection.getInputStream();
			}
			final String resp = getResponseMessage(is);
			
			
			if(isJSON(resp))
			{
				if(ParseResponse.returnStatus(resp) == 0)
				{
					message.setResponse(resp);
					message.setStatusCode(0);
				}
				else
				{
					message.setResponse(resp);
					message.setStatusCode(2);
				}
				
			}
			else
			{
				message.setResponse(resp);
				message.setStatusCode(-1);
			}
			
			if(os != null)
			{
				os.close();
			}
			
			return message;
		} 
		
		
		catch (SocketTimeoutException ex)
		{
			if(connection != null)
			{
				String s = ex.toString();
                if(s != null)
                {
                    String[] splitS = s.split("\\.");

                    StringBuilder builder = new StringBuilder();
                    int i = 0;
                    for (String str : splitS[2].split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
                        if(i>0)
                        {
                           str = str.toLowerCase();
                        }
                        if(i ==2)
                        {
                            builder.append(str);
                        }
                        else
                        {
                            builder.append(str+" ");
                        }
                        i++;
                    }
                    message.setResponse(builder.toString());
    				message.setStatusCode(-1);
                }
                else
                {
                	message.setResponse(genError);
    				message.setStatusCode(-1);
                }
			}
			
			else
			{
				message.setResponse(genError);
				message.setStatusCode(-1);
			}
			
			if(os != null)
			{
				os.close();
			}
			
			return message;
			
		}
		catch(IOException ex)
		{
			if(responseCode == 0)
			{
				message.setResponse("Connection to server failed, make sure you can connect to internet and try again.");
				message.setStatusCode(-1);
			}
			else
			{
				if(ex.getMessage() != null)
				{
					message.setResponse(ex.getMessage());
					message.setStatusCode(-1);
				}
				else
				{
					message.setResponse(genError);
					message.setStatusCode(-1);
				}
			}
			
			if(os != null)
			{
				os.close();
			}
			
			return message;
		}
		catch (Exception ex) {

			if(responseCode == 0)
			{
				message.setResponse("Connection to server failed, make sure you can connect to internet and try again.");
				message.setStatusCode(-1);
			}
			else
			{
				if(ex.getMessage() != null)
				{
					message.setResponse(ex.getMessage());
					message.setStatusCode(-1);
				}
				else
				{
					message.setResponse(genError);
					message.setStatusCode(-1);
				}
			}
			
			if(os != null)
			{
				os.close();
			}
			
			return message;
		}

	}

	/**
	 *
	 * Returns contents of InputStream that has been returned from a successful
	 * HTTP request
	 *
	 * @param is
	 *            InputStream that has been returned from a successful HTTP
	 *            request
	 * @return A string of the response body parsed from the input stream
	 * @throws UnsupportedEncodingException
	 *             Default encoding of {@code ENCODING} is specified, If this is
	 *             not supported, this UnsupportedEncodingException will be
	 *             thrown
	 * @throws IOException
	 *             Any errors in reading contents of input stream will thrown
	 *             IOException
	 */
	protected static String getResponseMessage(InputStream is)
			throws UnsupportedEncodingException, IOException {

		if (is == null) {
			return null;
		}

		StringBuilder builder;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				is, ENCODING))) {
			builder = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		}
		if(is != null)
		{
			is.close();
		}
		
		return builder.toString();

	}
	
	private static boolean isJSON(String m)
	{
		try
		{
			if(m != null)
			{
				new JSONObject(m);
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(JSONException e)
		{
			return false;
		}
	}

}
