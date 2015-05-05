package fr.bmartel.protocol.wlan.frame.management.element.inter;

/**
 * Traffic indication message
 * 
 * A delivery traffic indication message is a frame that wake up sleeping wifi device to listen to router message
 *    
 * <ul>
 * <li>element id : 1 byte </li>
 * <li>length : 1 byte</li>
 * <li>DTIM Count : number of beacon frame will appear before the next DTIM</li>
 * <li>DTIM Period : number of beacon intervals between 2 DTIM</li>
 * <li>Bitmap Control</li>
 * <li>Partial Virtual Bitmap</li>
 * </ul>
 * 
 * 
 * @author Bertrand Martel
 *
 */
public interface ITimElement {

	public byte getDTIMcount();
	
	public byte getDTIMperiod();
	
	public byte getBitmapControl();
	
	public byte[] getPartialVirtualBitmap();
}
