package fr.bmartel.protocol.wlan.frame.management.element.impl;

import java.io.UnsupportedEncodingException;

import fr.bmartel.protocol.wlan.frame.management.element.WlanElementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.inter.ISsidElement;

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
public class SSIDElement extends WlanElementAbstr implements ISsidElement{

	public final static int id = 0;

	/**
	 * ssid value
	 */
	private String ssid = null;

	/**
	 * Build SSID object
	 * 
	 * @param length
	 * @param data
	 */
	public SSIDElement(byte[] data) {
		super(data);
		
		try {
			ssid = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			ssid = "";
		}
	}

	@Override
	public byte getElementId() {
		return id;
	}

	@Override
	public String getSsid() {
		return ssid;
	}

}
