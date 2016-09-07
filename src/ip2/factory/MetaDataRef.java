package ip2.factory;

import java.util.HashMap;

public class MetaDataRef implements MetaDataReference{

	private HashMap<String, String> metaData;
	@Override
	public void setMetaDataReference(HashMap<String, String> metaData) {
		// TODO Auto-generated method stub
		this.metaData = metaData;
	}

	@Override
	public HashMap<String, String> getMetaDataReference() {
		// TODO Auto-generated method stub
		return this.metaData;
	}

}
