package fr.bmartel.protocol.wlan.frame.control;

import fr.bmartel.protocol.wlan.frame.IWlanFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IPowerSavePollingFrame;
import fr.bmartel.protocol.wlan.inter.IWlanControlFrame;

/**
 * Power saving frame - Control Frame<br/>
 * <ul>
 * <li>Association id : 2 Bytes</li>
 * <li>BSSID : 6 Bytes</li>
 * <li>Transmitter id : 6 Bytes</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public class PowerSavePollingFrame implements IWlanControlFrame, IWlanFrame,
		IPowerSavePollingFrame {
	private byte[] associationId = null;

	private byte[] bssid = null;

	private byte[] transmitterId = null;

	/**
	 * Build Power saving contriol frame with byte array omitting frame control
	 * 
	 * @param frame
	 *            byte array omitting frame control
	 */
	public PowerSavePollingFrame(byte[] frame) {

		if (frame.length >= 14) {
			associationId = new byte[] { frame[0], frame[1] };

			bssid = new byte[] { frame[2], frame[3], frame[4], frame[5],
					frame[6], frame[7] };

			transmitterId = new byte[] { frame[8], frame[9], frame[10],
					frame[11], frame[12], frame[13] };
		} else {
			System.err
					.println("error treating Control frame - power saving frame");
		}
	}

	@Override
	public byte[] getAssociationId() {
		return associationId;
	}

	@Override
	public byte[] getBssid() {
		return bssid;
	}

	@Override
	public byte[] getTransmitterId() {
		return transmitterId;
	}

}
