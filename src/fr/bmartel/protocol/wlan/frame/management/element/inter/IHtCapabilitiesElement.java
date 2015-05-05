package fr.bmartel.protocol.wlan.frame.management.element.inter;

import fr.bmartel.protocol.wlan.frame.management.element.subelement.inter.IHtCapabilityInformation;
import fr.bmartel.protocol.wlan.frame.management.element.subelement.inter.IMcsSupportedSet;

/**
 * Define high throughput capabilities element
 * <ul>
 * <li>element id : 1 Byte</li>
 * <li>length : 1 Byte</li>
 * <li>HT capabilities info : 2 Bytes</li>
 * <li>A MPDU Parameters : 1 Byte</li>
 * <li>Supported MCS Set : 16 Bytes</li>
 * <li>HT Extended capabilities : 2 Bytes</li>
 * <li>transmit beam forming capabilities : 4 Bytes</li>
 * <li>ASEL capabilities : 1 Byte</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public interface IHtCapabilitiesElement {

	public IHtCapabilityInformation getHtCapabilityInfo() ;
	
	public byte getAmpduParameters();
	
	public IMcsSupportedSet getSupportedMCSSet() ;
	
	public byte[] getHtExtendedCapabilities() ;
	
	public byte[] getTransmitBeamformingCapabilities() ;
	
	public byte getAselCapabilities();
}
