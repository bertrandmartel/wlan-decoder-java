package fr.bmartel.protocol.wlan.frame.management.inter;

import java.util.List;

import fr.bmartel.protocol.wlan.frame.management.element.IWlanElement;

/**
 * Management frame - Beacon frame<br/>
 * <ul>
 * <li>Timestamp : 8 Bytes</li>
 * <li>Beacon interval : 2 Bytes</li>
 * <li>Capability info : 2 Bytes</li>
 * <li>tagged parameter : X Bytes</li>
 * </ul>
 * <p>
 * timestamp,beacon interval,capability info are static information, tagged are
 * dynamic and can be added with respect to their tag element id
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public interface IBeaconFrame {

	public byte[] getTimestamp();
	
	public byte[] getBeaconInterval();
	
	public byte[] getCapabilityInfo() ;
	
	public List<IWlanElement> getTaggedParameter();
	
}
