package fr.bmartel.protocol.wlan.frame.management.element.subelement.inter;

/**
 * Ht informations sub element of HT capabilities element
 * 
 * 
 * @author Bertrand Martel
 * 
 */
public interface IHtCapabilityInformation {

	public boolean isSupportShortGi20Mhz() ;
	
	public boolean isSupportShortGi40Mhz() ;
	
	public boolean isSupportedChannelWidthSet() ;
	
}
