package fr.bmartel.protocol.wlan.frame;


/**
 * Basic management frame<br/>
 * <ul>
 * <li>duration id : 2 Bytes</li>
 * <li>destination addr : 6 Bytes</li>
 * <li>source addr : 6 Bytes</li>
 * <li>BSSID : 6 Bytes</li>
 * <li>sequence control : 2 Bytes</li>
 * <li>frame body : 0-2312 Bytes</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public abstract class WlanManagementAbstr implements IWlanManagementFrame,
		IWlanFrame {

	/**
	 * duration id value
	 */
	private byte[] durationId = null;

	/**
	 * destination address
	 */
	private byte[] destinationAddr = null;

	/**
	 * source address
	 */
	private byte[] sourceAddr = null;

	/**
	 * BSSID value
	 */
	private byte[] bssid = null;

	/**
	 * sequence control byte array value
	 */
	private byte[] sequenceControl = null;

	/**
	 * frame body content
	 */
	private byte[] frameBody = null;

	private byte[] fcs = null;
	
	/**
	 * Build basic management frame from wlan frame
	 * 
	 * @param frame
	 *            wlan frame with control frame omitted
	 */
	public WlanManagementAbstr(byte[] frame) {
		if (frame.length >= 26) {
			durationId = new byte[] { frame[0], frame[1] };
			destinationAddr = new byte[] { frame[2], frame[3], frame[4],
					frame[5], frame[6], frame[7] };
			sourceAddr = new byte[] { frame[8], frame[9], frame[10], frame[11],
					frame[12], frame[13] };
			bssid = new byte[] { frame[14], frame[15], frame[16], frame[17],
					frame[18], frame[19] };
			sequenceControl = new byte[] { frame[20], frame[21] };
			if (frame.length == 26) {
				frameBody = new byte[] {};
				fcs=new byte[]{ frame[22], frame[23],  frame[24], frame[25]};
				
			} else {
				frameBody = new byte[frame.length - 26];
				for (int i = 22; i < frame.length-4; i++) {
					frameBody[i - 22] = frame[i];
				}
				fcs=new byte[]{ frame[frame.length-4], frame[frame.length-3],  frame[frame.length-2], frame[frame.length-1]};
			}
		} else {
			System.err.println("error treating Management frame");
		}
	}

	@Override
	public byte[] getFcs()
	{
		return fcs;
	}
	
	@Override
	public byte[] getDurationId() {
		return durationId;
	}

	@Override
	public byte[] getDestinationAddr() {
		return destinationAddr;
	}

	@Override
	public byte[] getSourceAddr() {
		return sourceAddr;
	}

	@Override
	public byte[] getBSSID() {
		return bssid;
	}

	@Override
	public byte[] getSequenceControl() {
		return sequenceControl;
	}

	@Override
	public byte[] getFrameBody() {
		return frameBody;
	}

}
