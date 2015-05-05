package fr.bmartel.protocol.wlan.frame.data;

import fr.bmartel.protocol.wlan.frame.WlanDataAbstr;
import fr.bmartel.protocol.wlan.frame.data.inter.INullFrame;

/**
 * Null data or qos data frame decoder
 * 
 * @author Bertrand Martel
 * 
 */
public class NullFrame extends WlanDataAbstr implements INullFrame {

	/**
	 * Decode null frame
	 * 
	 * @param frame
	 *            frame with omitted control frame
	 * @param toDS
	 *            to distribution system indicator
	 * @param fromDS
	 *            from distribution system indicator
	 */
	public NullFrame(byte[] frame, boolean toDS, boolean fromDS) {
		super(frame, toDS, fromDS);
	}
}
