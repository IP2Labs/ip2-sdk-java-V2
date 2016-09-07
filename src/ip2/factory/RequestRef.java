package ip2.factory;

import java.util.HashMap;

public class RequestRef implements RequestReference{

	private HashMap<String, String> requestReference;
	
	@Override
	public void setRequestReference(HashMap<String, String> requestReference) {
		this.requestReference = requestReference;
		
	}

	@Override
	public HashMap<String, String> getRequestReference() {
		// TODO Auto-generated method stub
		return this.requestReference;
	}

}
