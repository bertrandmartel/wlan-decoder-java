package fr.bmartel.protocol.wlan.frame.management.element.inter;

/**
 * SSID element indicates the identity of an ESS or IBSS.<br/>
 * <ul>
 * <li>element id : 1 Byte</li>
 * <li>length : 1 Byte</li>
 * <li>ssid : 0-32 Bytes</li>
 * </ul>
 * <p>
 * The length of the SSID field is between 0 and 32 octets. A SSID field of
 * length 0 is used within Probe Request management frames to indicate the
 * wildcard SSID. The wildcard SSID is also used in Beacon and Probe Response
 * frames transmitted by mesh STAs
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public interface ISsidElement {

	public String getSsid();
	
}
