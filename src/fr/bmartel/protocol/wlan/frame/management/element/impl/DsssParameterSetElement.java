package fr.bmartel.protocol.wlan.frame.management.element.impl;

import fr.bmartel.protocol.wlan.frame.management.element.WlanElementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.inter.IDsssParameterSetElement;

/**
 * The DSSS Parameter Set element contains information to allow channel number
 * identification for STAs.
 * <ul>
 * <li>element id : 1 byte
 * <li>length : 1 byte
 * <li>current channel : 1 byte
 * </ul>
 * 
 * @author Bertrand Martel
 *
 */
public class DsssParameterSetElement extends WlanElementAbstr implements IDsssParameterSetElement {

	public final static int id = 3;

	/**
	 * current channel
	 */
	private int currentChannel = 0x00;

	private int frequency = -1;

	/**
	 * 
	 * @param length
	 * @param data
	 */
	public DsssParameterSetElement(byte[] data) {
		super(data);
		currentChannel = data[0] & 0xFF;

		switch (frequency) {
		case 1:
			frequency = 2412;
			break;
		case 2:
			frequency = 2417;
			break;
		case 3:
			frequency = 2422;
			break;
		case 4:
			frequency = 2427;
			break;
		case 5:
			frequency = 2432;
			break;
		case 6:
			frequency = 2437;
			break;
		case 7:
			frequency = 2442;
			break;
		case 8:
			frequency = 2447;
			break;
		case 9:
			frequency = 2452;
			break;
		case 10:
			frequency = 2457;
			break;
		case 11:
			frequency = 2462;
			break;
		case 12:
			frequency = 2467;
			break;
		case 13:
			frequency = 2472;
			break;
		case 14:
			frequency = 2484;
			break;
		}
	}

	@Override
	public int getCurrentChannel()
	{
		return currentChannel;
	}
	
	@Override
	public int getFrequency()
	{
		return frequency;
	}

	@Override
	public byte getElementId() {
		return id;
	}

}
