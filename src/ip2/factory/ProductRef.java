package ip2.factory;

import java.util.HashMap;

public class ProductRef implements ProductReference{

	private HashMap<String, String> productReference;
	
	@Override
	public void setProductReference(HashMap<String, String> productReference) {
		// TODO Auto-generated method stub
		this.productReference = productReference;
	}

	@Override
	public HashMap<String, String> getProductReference() {
		// TODO Auto-generated method stub
		return this.productReference;
	}

}
