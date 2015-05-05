package fr.bmartel.protocol.wlan.frame.management.element.subelement;

import java.util.ArrayList;
import java.util.List;

import fr.bmartel.protocol.wlan.IWlanMcs;
import fr.bmartel.protocol.wlan.constant.WlanMcsList;
import fr.bmartel.protocol.wlan.frame.management.element.IWlanSubElement;
import fr.bmartel.protocol.wlan.frame.management.element.subelement.inter.IMcsSupportedSet;

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
public class McsSupportedSet implements IWlanSubElement,IMcsSupportedSet {

	private byte[] rxMcsBitmask = null;

	/**
	 * model coding scheme list
	 */
	private List<IWlanMcs> mcsList = null;

	public McsSupportedSet(byte[] data) {
		mcsList = new ArrayList<IWlanMcs>();
		int shift = 0;
		for (int i = 0; i < WlanMcsList.SPATIAL_STREAM_NUM; i++) {
			for (int j = 0; j < 8; j++) {
				if ((data[i] & (1 << j)) != 0) {
					mcsList.add(WlanMcsList.mcsList.get(shift + j));
				}
			}
			shift += 8;
		}
	}
	
	@Override
	public byte[] getRxMcsBitmask() {
		return rxMcsBitmask;
	}
	
	@Override
	public List<IWlanMcs> getMcsList() {
		return mcsList;
	}
}
