package ip2.exception;

public class IP2Exception extends Exception
{
	public IP2Exception(String ex)
	{
		super(ex);
	}
	
	public IP2Exception(String ex, Throwable th)
	{
		super(ex, th);
	}

}
