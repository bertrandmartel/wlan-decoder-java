package fr.bmartel.protocol.wlan.frame.control.inter;

/**
 * Control Frame : Contention free<br/>
 * <ul>
 * <li>duration id : 2 Bytes</li>
 * <li>receiver address : 6 Bytes</li>
 * <li>BSSID address : 6 Bytes</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public interface IContentionFreeFrame {

	public byte[] getBssid();
	
	public byte[] getReceiverAddr();
	
	public byte[] getDurationId();
}
