package fr.bmartel.protocol.wlan.frame.control;

import fr.bmartel.protocol.wlan.frame.IWlanFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IClearToSendFrame;
import fr.bmartel.protocol.wlan.inter.IWlanControlFrame;

/**
 * Control frame - clear to send <br/>
 * <ul>
 * <li>duration id : 2 Bytes</li>
 * <li>receiver address : 6 Bytes</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public class ClearToSendFrame implements IWlanFrame, IWlanControlFrame,
		IClearToSendFrame {

	/**
	 * duration id
	 */
	private byte[] durationId = null;

	/**
	 * receiver addresss
	 */
	private byte[] receiverAddr = null;

	/**
	 * Build control frame Clear to send
	 * 
	 * @param frame
	 *            wlan frame with control frame omitted
	 */
	public ClearToSendFrame(byte[] frame) {
		if (frame.length >= 8) {
			durationId = new byte[] { frame[0], frame[1] };
			receiverAddr = new byte[] { frame[2], frame[3], frame[4], frame[5],
					frame[6], frame[7] };
		} else {
			System.err
					.println("error treating Control frame - clear to send frame");
		}
	}

	@Override
	public byte[] getDurationId() {
		return durationId;
	}

	@Override
	public byte[] getReceiverAddr() {
		return receiverAddr;
	}

}
