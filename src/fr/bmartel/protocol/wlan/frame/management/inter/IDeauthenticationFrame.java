package fr.bmartel.protocol.wlan.frame.management.inter;

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
public interface IDeauthenticationFrame {

	public int getReasonCode();
	
}
