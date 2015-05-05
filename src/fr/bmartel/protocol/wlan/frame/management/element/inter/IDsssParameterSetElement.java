package fr.bmartel.protocol.wlan.frame.management.element.inter;


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
public interface IDsssParameterSetElement {

	public int getCurrentChannel();
	
	public int getFrequency();
}
