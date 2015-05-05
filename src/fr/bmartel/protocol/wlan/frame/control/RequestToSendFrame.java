package fr.bmartel.protocol.wlan.frame.control;

import fr.bmartel.protocol.wlan.frame.IWlanFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IRequestToSendFrame;
import fr.bmartel.protocol.wlan.inter.IWlanControlFrame;

/**
 * Control Frame : request to Send<br/>
 * <ul>
 * <li>duration id : 2 Bytes</li>
 * <li>receiver address : 6 Bytes</li>
 * <li>transmitter address : 6 Bytes</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public class RequestToSendFrame implements IWlanFrame,IWlanControlFrame,IRequestToSendFrame {

	/**
	 * duration id value on 2 bytes
	 */
	private byte[] durationId = null;

	/**
	 * receiver address on 6 Bytes
	 */
	private byte[] receiverAddr = null;

	/**
	 * transmitter address on 6 Bytes
	 */
	private byte[] transmitterAddr = null;

	/**
	 * Build a request to send control frame
	 * 
	 * @param frame
	 *            wlan frame with control frame omitted
	 */
	public RequestToSendFrame(byte[] frame) {
		if (frame.length >= 14) {
			durationId = new byte[] { frame[0], frame[1] };
			receiverAddr = new byte[] { frame[2], frame[3], frame[4], frame[5],
					frame[6], frame[7] };
			transmitterAddr = new byte[] { frame[8], frame[9], frame[10],
					frame[11], frame[12], frame[13] };
		} else {
			System.err.println("error treating Control frame - request to send frame");
		}
	}

	@Override
	public byte[] getDurationId() {
		return durationId;
	}
	
	@Override
	public byte[] getTransmitterAddr() {
		return transmitterAddr;
	}
	
	@Override
	public byte[] getReceiverAddr() {
		return receiverAddr;
	}

}
