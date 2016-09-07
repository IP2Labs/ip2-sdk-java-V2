package ip2.helpers;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseResponse
{
	public static int returnStatus(String rawResponse)
	{
		JSONObject object = new JSONObject(rawResponse);
		
		try
		{
			int status = object.getInt("Code");
			return status;
		}
		catch(JSONException ex)
		{
			return 2;
		}
		
		
		
		
	}

}
