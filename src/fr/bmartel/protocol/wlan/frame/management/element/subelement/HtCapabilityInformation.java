package fr.bmartel.protocol.wlan.frame.management.element.subelement;

import fr.bmartel.protocol.wlan.frame.management.element.IWlanSubElement;
import fr.bmartel.protocol.wlan.frame.management.element.subelement.inter.IHtCapabilityInformation;

/**
 * Ht informations sub element of HT capabilities element
 * 
 * 
 * @author Bertrand Martel
 * 
 */
public class HtCapabilityInformation implements IWlanSubElement,IHtCapabilityInformation {

	/**
	 * guard interval defined for 20Mhz band in 400ns
	 */
	private boolean supportShortGi20Mhz = false;

	/**
	 * guard interval defined for 40Mhz band in 400ns
	 */
	private boolean supportShortGi40Mhz = false;

	/**
	 * Set to 0 if only 20 MHz operation is supported<br/>
	 * Set to 1 if both 20 MHz and 40 MHz operation is supported
	 */
	private boolean supportedChannelWidthSet = false;

	public HtCapabilityInformation(byte[] data) {
		supportShortGi20Mhz = ((data[0] & 0x40) != 0);
		supportShortGi40Mhz = ((data[0] & 0x20) != 0);
		supportedChannelWidthSet = ((data[0] & 0b01000000) != 0);
	}
	
	@Override
	public boolean isSupportShortGi20Mhz() {
		return supportShortGi20Mhz;
	}

	@Override
	public boolean isSupportShortGi40Mhz() {
		return supportShortGi40Mhz;
	}

	@Override
	public boolean isSupportedChannelWidthSet() {
		return supportedChannelWidthSet;
	}
}
