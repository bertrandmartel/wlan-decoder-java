package fr.bmartel.protocol.wlan.frame.management;

import java.util.List;

import fr.bmartel.protocol.wlan.frame.WlanManagementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.IWlanElement;
import fr.bmartel.protocol.wlan.frame.management.element.WlanElementIdDecoder;
import fr.bmartel.protocol.wlan.frame.management.inter.IReassociationRequestFrame;

/**
 * Management frame - ReAssociation frame<br/>
 * <ul>
 * <li>Capability info : 2 Bytes</li>
 * <li>listen interval : 2 Bytes</li>
 * <li>current AP address : 6 Bytes</li>
 * <li>tagged parameter : X Bytes</li>
 * </ul>
 * <p>
 * capability info,listen interval and current ap adress are static information,
 * tagged are dynamic and can be added with respect to their tag element id
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public class ReassociationRequestFrame extends WlanManagementAbstr implements IReassociationRequestFrame {

	/**
	 * Capability information field spans to 16 bits and contain information
	 * about capability of the device/network.
	 */
	private byte[] capabilityInfo = null;

	/**
	 * listen interval for association
	 */
	private byte[] listenInterval = null;

	/**
	 * current Access point adress
	 */
	private byte[] currentAPAdress = null;

	/**
	 * parameter identified by their tag id. A collection of these id can be
	 * found in WlanElementTagId class
	 */
	private List<IWlanElement> taggedParameter = null;

	/**
	 * Parse beaon management frame according to basic management frame and
	 * beacon frame specification
	 * 
	 * @param frame
	 *            frame with omitted control frame
	 */
	public ReassociationRequestFrame(byte[] frame) {
		super(frame);
		byte[] frameBody = getFrameBody();

		capabilityInfo = new byte[2];
		for (int i = 0; i < capabilityInfo.length; i++) {
			capabilityInfo[capabilityInfo.length - 1 - i] = frameBody[i];
		}

		listenInterval = new byte[2];
		for (int i = 2; i < 4; i++) {
			listenInterval[4 - 1 - i] = frameBody[i];
		}
		currentAPAdress = new byte[6];
		for (int i = 4; i < 10; i++) {
			currentAPAdress[10 - 1 - i] = frameBody[i];
		}

		byte[] taggedParameterArray = null;

		if (frameBody.length >= 11) {
			taggedParameterArray = new byte[frameBody.length - 10];
			for (int i = 10; i < frameBody.length; i++) {
				taggedParameterArray[i - 10] = frameBody[i];
			}
		} else {
			taggedParameterArray = new byte[] {};
		}

		WlanElementIdDecoder decoder = new WlanElementIdDecoder();
		taggedParameter = decoder.decode(taggedParameterArray);
	}

	@Override
	public byte[] getCapabilityInfo() {
		return capabilityInfo;
	}
	
	@Override
	public List<IWlanElement> getTaggedParameter() {
		return taggedParameter;
	}
	
	@Override
	public byte[] getListenInterval() {
		return listenInterval;
	}
	
	@Override
	public byte[] getCurrentAPAdress() {
		return currentAPAdress;
	}

}
