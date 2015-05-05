package fr.bmartel.protocol.wlan.frame.management;

import java.util.List;

import fr.bmartel.protocol.wlan.frame.WlanManagementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.IWlanElement;
import fr.bmartel.protocol.wlan.frame.management.element.WlanElementIdDecoder;
import fr.bmartel.protocol.wlan.frame.management.inter.IBeaconFrame;

/**
 * Management frame - Beacon frame<br/>
 * <ul>
 * <li>Timestamp : 8 Bytes</li>
 * <li>Beacon interval : 2 Bytes</li>
 * <li>Capability info : 2 Bytes</li>
 * <li>tagged parameter : X Bytes</li>
 * </ul>
 * <p>
 * timestamp,beacon interval,capability info are static information, tagged are
 * dynamic and can be added with respect to their tag element id
 * </p>
 * 
 * @author Bertrand Martel
 * 
 */
public class BeaconFrame extends WlanManagementAbstr implements IBeaconFrame{

	/**
	 * timestamp value for this frame (for sync)
	 */
	private byte[] timestamp = null;

	/**
	 * This is the time interval between beacon transmissions
	 */
	private byte[] beaconInterval = null;

	/**
	 * Capability information field spans to 16 bits and contain information
	 * about capability of the device/network.
	 */
	private byte[] capabilityInfo = null;

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
	public BeaconFrame(byte[] frame) {
		super(frame);
		byte[] frameBody = getFrameBody();

		timestamp = new byte[8];
		for (int i = 0; i < timestamp.length; i++) {
			timestamp[timestamp.length - 1 - i] = frameBody[i];
		}

		beaconInterval = new byte[2];
		for (int i = 8; i < 10; i++) {
			beaconInterval[10 - i - 1] = frameBody[i];
		}

		capabilityInfo = new byte[2];
		for (int i = 10; i < 12; i++) {
			capabilityInfo[12 - i - 1] = frameBody[i];
		}

		// manage tagged parameter list with element id decoder
		byte[] taggedParameterArray = null;
		if (frameBody.length >= 13) {
			taggedParameterArray = new byte[frameBody.length - 12];
			for (int i = 12; i < frameBody.length; i++) {
				taggedParameterArray[i - 12] = frameBody[i];
			}
		} else {
			taggedParameterArray = new byte[] {};
		}

		WlanElementIdDecoder decoder = new WlanElementIdDecoder();
		taggedParameter = decoder.decode(taggedParameterArray);
	}

	@Override
	public byte[] getTimestamp() {
		return timestamp;
	}
	
	@Override
	public byte[] getBeaconInterval() {
		return beaconInterval;
	}
	
	@Override
	public byte[] getCapabilityInfo() {
		return capabilityInfo;
	}
	
	@Override
	public List<IWlanElement> getTaggedParameter() {
		return taggedParameter;
	}

}
