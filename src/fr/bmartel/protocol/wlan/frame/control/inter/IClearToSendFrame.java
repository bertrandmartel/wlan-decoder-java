package fr.bmartel.protocol.wlan.frame.control.inter;

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
public interface IClearToSendFrame {

	public byte[] getDurationId() ;
	
	public byte[] getReceiverAddr();
}
