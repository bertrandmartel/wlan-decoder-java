package fr.bmartel.protocol.wlan.frame.management.element.impl;

import fr.bmartel.protocol.wlan.frame.management.element.WlanElementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.inter.IHtCapabilitiesElement;
import fr.bmartel.protocol.wlan.frame.management.element.subelement.HtCapabilityInformation;
import fr.bmartel.protocol.wlan.frame.management.element.subelement.McsSupportedSet;
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
public class HTCapabilitiesElement extends WlanElementAbstr implements IHtCapabilitiesElement {

	public final static int id = 45;
	
	private IHtCapabilityInformation htCapabilityInfo = null;

	private byte ampduParameters = 0x00;

	private IMcsSupportedSet supportedMCSSet = null;

	private byte[] htExtendedCapabilities = null;

	private byte[] transmitBeamformingCapabilities = null;

	private byte aselCapabilities = 0x00;

	public HTCapabilitiesElement(byte[] data) {
		super(data);
		
		if (data.length == 26) {

			htCapabilityInfo = new HtCapabilityInformation(new byte[] {
					data[0], data[1] });

			ampduParameters = data[2];
			byte[] supportedMCSSetArray = new byte[16];
			for (int i = 3; i < 16 + 3; i++) {
				supportedMCSSetArray[i - 3] = data[i];
			}
			supportedMCSSet = new McsSupportedSet(supportedMCSSetArray);

			htExtendedCapabilities = new byte[] { data[19], data[20] };
			transmitBeamformingCapabilities = new byte[] { data[21], data[22],
					data[23], data[24] };
			aselCapabilities = data[25];
		} else {
			System.err.println("Error decoding HT capabilities element");
		}
	}

	@Override
	public byte getElementId() {
		return id;
	}

	@Override
	public IHtCapabilityInformation getHtCapabilityInfo() {
		return htCapabilityInfo;
	}
	
	@Override
	public byte getAmpduParameters() {
		return ampduParameters;
	}
	
	@Override
	public IMcsSupportedSet getSupportedMCSSet() {
		return supportedMCSSet;
	}
	
	@Override
	public byte[] getHtExtendedCapabilities() {
		return htExtendedCapabilities;
	}
	
	@Override
	public byte[] getTransmitBeamformingCapabilities() {
		return transmitBeamformingCapabilities;
	}
	
	@Override
	public byte getAselCapabilities() {
		return aselCapabilities;
	}

}
