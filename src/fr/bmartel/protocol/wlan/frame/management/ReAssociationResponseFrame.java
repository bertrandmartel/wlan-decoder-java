package fr.bmartel.protocol.wlan.frame.management;

import java.util.List;

import fr.bmartel.protocol.wlan.frame.WlanManagementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.IWlanElement;
import fr.bmartel.protocol.wlan.frame.management.element.WlanElementIdDecoder;
import fr.bmartel.protocol.wlan.frame.management.inter.IReassociationResponseFrame;
import fr.bmartel.protocol.wlan.utils.ByteUtils;

/**
 * Management frame - ReAssociation response frame<br/>
 * <ul>
 * <li>Capability info : 2 Bytes</li>
 * <li>status code : 2 Bytes</li>
 * <li>association id : 2 Bytes</li>
 * <li>tagged parameter : X Bytes</li>
 * </ul>
 * <p>
 * capability info,status code and association id are static information, tagged
 * are dynamic and can be added with respect to their tag element id
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public class ReAssociationResponseFrame extends WlanManagementAbstr implements IReassociationResponseFrame{

	/**
	 * Capability information field spans to 16 bits and contain information
	 * about capability of the device/network.
	 */
	private byte[] capabilityInfo = null;

	/**
	 * status code for association
	 */
	private int statusCode = 0;

	/**
	 * identify the association
	 */
	private int associationId = 0;

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
	public ReAssociationResponseFrame(byte[] frame) {
		super(frame);
		byte[] frameBody = getFrameBody();

		capabilityInfo = new byte[] { frameBody[1], frameBody[0] };

		statusCode = ByteUtils.convertByteArrayToInt(new byte[] {
				frameBody[3], frameBody[2] });

		associationId = ByteUtils.convertByteArrayToInt(new byte[] {
				frameBody[5], frameBody[4] });

		byte[] taggedParameterArray = null;

		if (frameBody.length >= 7) {
			taggedParameterArray = new byte[frameBody.length - 6];
			for (int i = 6; i < frameBody.length; i++) {
				taggedParameterArray[i - 6] = frameBody[i];
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
	public int getStatusCode() {
		return statusCode;
	}
	@Override
	public int getAssociationId() {
		return associationId;
	}

}
