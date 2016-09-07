package ip2.helpers;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class IP2GatewayUtils {

	public static String generateUUID()
	{
		return UUID.randomUUID().toString();
	}
	public static String generateAlphaNumeric()
    {
    	return RandomStringUtils.random(10, true, true).toUpperCase();
    }
}
