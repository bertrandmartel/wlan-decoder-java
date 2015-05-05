package fr.bmartel.protocol.wlan.frame.management.element.inter;

/**
 * Define data rate<br/>
 * <ul>
 * <li>element id : 1 Byte</li>
 * <li>length : 1 Byte</li>
 * <li>data : 1 - 8 Bytes</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public interface ISupportedRateElement {

	public byte[] getDataRate() ;
}
