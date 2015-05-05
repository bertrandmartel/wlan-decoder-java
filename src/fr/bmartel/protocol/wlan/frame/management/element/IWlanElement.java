package fr.bmartel.protocol.wlan.frame.management.element;

/**
 * Generic Wlan Elements defined in management frames<br/>
 * <ul>
 * <li>element id : 1 Byte</li>
 * <li>length : 1 Byte</li>
 * <li>information : X Bytes</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public interface IWlanElement {

	/**
	 * retrieve element id
	 * 
	 * @return
	 */
	public byte getElementId();
	
	/**
	 * data 
	 * @return
	 */
	public byte[] getData();
	
}
