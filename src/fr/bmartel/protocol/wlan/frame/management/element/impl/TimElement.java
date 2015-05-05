package fr.bmartel.protocol.wlan.frame.management.element.impl;

import fr.bmartel.protocol.wlan.frame.management.element.WlanElementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.inter.ITimElement;

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
public class TimElement extends WlanElementAbstr implements ITimElement{

	public final static int id = 5;
	
	/**
	 * number of beacon frame will appear before the next DTIM
	 */
	private byte dtimCount = 0;

	/**
	 * number of beacon intervals between 2 DTIM
	 */
	private byte dtimPeriod = 0;

	private byte bitmapControl = 0; 
	
	private byte[] partialVirtualBitmap = new byte[]{};
	
	/**
	 * 
	 * @param length
	 * @param data
	 */
	public TimElement(byte[] data) {
		super(data);
		dtimCount = data[0] ;
		dtimPeriod = data[1] ;
		bitmapControl = data[2] ;
		partialVirtualBitmap=new byte[data.length-3];
		for (int i = 3;i<data.length;i++)
		{
			partialVirtualBitmap[3-i]=data[i];
		}
	}

	@Override
	public byte getDTIMcount()
	{
		return dtimCount;
	}
	
	@Override
	public byte getDTIMperiod()
	{
		return dtimPeriod;
	}
	
	@Override
	public byte getBitmapControl()
	{
		return bitmapControl;
	}
	
	@Override
	public byte[] getPartialVirtualBitmap()
	{
		return partialVirtualBitmap;
	}
	
	@Override
	public byte getElementId() {
		return id;
	}
}
