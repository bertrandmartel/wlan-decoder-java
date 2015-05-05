package fr.bmartel.protocol.wlan.frame.management.inter;

import java.util.List;

import fr.bmartel.protocol.wlan.frame.management.element.IWlanElement;

/**
 * Management frame - Association frame<br/>
 * <ul>
 * <li>Capability info : 2 Bytes</li>
 * <li>listen interval : 2 Bytes</li>
 * <li>tagged parameter : X Bytes</li>
 * </ul>
 * <p>
 * capability info,listen interval are static information, tagged are dynamic
 * and can be added with respect to their tag element id
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public interface IAssociationRequestFrame {

	public byte[] getCapabilityInfo();
	
	public List<IWlanElement> getTaggedParameter();

	public byte[] getListenInterval();
}
