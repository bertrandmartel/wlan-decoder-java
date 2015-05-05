package fr.bmartel.protocol.wlan.frame.management.element;

/**
 * Common implementation of a generic element in wlan protocol 802.11
 * 
 * @author Bertrand Martel
 * 
 */
public abstract class WlanElementAbstr implements IWlanElement {

	private byte[] data = null;

	public WlanElementAbstr(byte[] data) {
		this.data = data;
	}

	@Override
	public byte[] getData() {
		return data;
	}
}
