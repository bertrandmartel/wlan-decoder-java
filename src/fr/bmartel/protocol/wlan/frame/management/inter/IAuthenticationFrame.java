package fr.bmartel.protocol.wlan.frame.management.inter;

/**
 * Management frame -Authentication frame<br/>
 * <ul>
 * <li>authentication algorithm :2 Bytes</li>
 * <li>authentication sequential number : 2 Bytes</li>
 * <li>status code : 2 Bytes</li>
 * </ul>
 * <p>
 * contains only fixed parameters
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public interface IAuthenticationFrame {

	public int getAuthenticationAlgorithmNum() ;
	
	public int getAuthenticationSeqNum();
	
	public int getStatusCode();
	
}
