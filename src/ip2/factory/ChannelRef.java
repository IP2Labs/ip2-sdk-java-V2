package ip2.factory;

import java.util.HashMap;

public class ChannelRef implements ChannelReference
{
	private HashMap<String, String> channelRef;

	@Override
	public void setChannelReference(HashMap<String, String> channelRef) {
		this.channelRef = channelRef;
	}

	@Override
	public HashMap<String, String> getChannelRefence() {
		return this.channelRef;
	}

}
