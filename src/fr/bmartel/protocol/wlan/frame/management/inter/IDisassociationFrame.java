package fr.bmartel.protocol.wlan.frame.management.inter;

/**
 * Management frame - Disassociation frame<br/>
 * <ul>
 * <li>status code :2 Bytes</li>
 * </ul>
 * <p>
 * contains only fixed parameters
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public interface IDisassociationFrame {

	public int getStatusCode() ;
	
}
