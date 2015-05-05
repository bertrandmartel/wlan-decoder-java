package fr.bmartel.protocol.wlan.frame.management;

import fr.bmartel.protocol.wlan.frame.WlanManagementAbstr;
import fr.bmartel.protocol.wlan.frame.management.inter.IDeauthenticationFrame;
import fr.bmartel.protocol.wlan.utils.ByteUtils;

/**
 * Management frame - DeAuthentication frame<br/>
 * <ul>
 * <li>reason code :2 Bytes</li>
 * </ul>
 * <p>
 * contains only fixed parameters
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public class DeAuthenticationFrame extends WlanManagementAbstr implements IDeauthenticationFrame{

	/**
	 * authentication reason code
	 */
	private int reasonCode = 0;

	/**
	 * Parse DeAuthentication management frame according to basic management
	 * frame and beacon frame specification
	 * 
	 * @param frame
	 *            frame with omitted control frame
	 */
	public DeAuthenticationFrame(byte[] frame) {
		super(frame);
		byte[] frameBody = getFrameBody();
		reasonCode = ByteUtils.convertByteArrayToInt(new byte[] {
				frameBody[1], frameBody[0] });
	}

	@Override
	public int getReasonCode() {
		return reasonCode;
	}
}
