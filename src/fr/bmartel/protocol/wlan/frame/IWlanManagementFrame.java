package fr.bmartel.protocol.wlan.frame;

/**
 * Wlan 802.11 Management frame basic interface
 * 
 * @author Bertrand Martel
 * 
 */
public interface IWlanManagementFrame {
	
	/**
	 * retrieve duration id
	 * 
	 * @return
	 */
	public byte[] getDurationId();

	/**
	 * retrieve destination address
	 * 
	 * @return
	 */
	public byte[] getDestinationAddr();

	/**
	 * retrieve source address
	 * 
	 * @return
	 */
	public byte[] getSourceAddr();

	/**
	 * retrieve BSSID address
	 * 
	 * @return
	 */
	public byte[] getBSSID();

	/**
	 * retrieve sequence control frame
	 * 
	 * @return
	 */
	public byte[] getSequenceControl();

	/**
	 * retrieve frame body byte array
	 * 
	 * @return
	 */
	public byte[] getFrameBody();
	
	/**
	 * Frame Check Sequence
	 * @return
	 */
	public byte[] getFcs();

}
