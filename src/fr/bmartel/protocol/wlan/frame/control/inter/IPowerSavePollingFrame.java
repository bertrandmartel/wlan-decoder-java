package fr.bmartel.protocol.wlan.frame.control.inter;

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
public interface IPowerSavePollingFrame {

	public byte[] getAssociationId();
	
	public byte[] getBssid();
	
	public byte[] getTransmitterId();
}
