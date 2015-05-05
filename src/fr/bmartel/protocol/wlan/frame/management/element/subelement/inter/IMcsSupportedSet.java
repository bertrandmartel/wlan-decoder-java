package fr.bmartel.protocol.wlan.frame.management.element.subelement.inter;

import java.util.List;

import fr.bmartel.protocol.wlan.IWlanMcs;

/**
 * parse MCS supported set subtype element
 * <ul>
 * <li>RX MCS bitmask : b0-b76</li>
 * <li>reserved : b77-b79</li>
 * <li>RX Highest Supported Data Rate : b80-b89</li>
 * <li>reserved : b90-b95</li>
 * <li>TX MCS set defined : b96</li>
 * <li>TX RX MCS set not equal : b97</li>
 * <li>TX maximum number spatial streams supported : b98-b99</li>
 * <li>TX unequal modulation supported : b100</li>
 * <li>reserved : b101:b127</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public interface IMcsSupportedSet {

	public byte[] getRxMcsBitmask() ;
	
	public List<IWlanMcs> getMcsList();
	
}
