package fr.bmartel.protocol.wlan.frame.management;

import java.util.List;

import fr.bmartel.protocol.wlan.frame.WlanManagementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.IWlanElement;
import fr.bmartel.protocol.wlan.frame.management.element.WlanElementIdDecoder;
import fr.bmartel.protocol.wlan.frame.management.inter.IProbeRequestFrame;

/**
 * Probe request management frame decoder<br/>
 * <ul>
 * <li>tagged parameters : X bytes</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public class ProbeRequestFrame extends WlanManagementAbstr implements IProbeRequestFrame{

	private List<IWlanElement> taggedParameter = null;

	public ProbeRequestFrame(byte[] frame) {
		super(frame);
		WlanElementIdDecoder decoder = new WlanElementIdDecoder();
		taggedParameter = decoder.decode(getFrameBody());

	}

	@Override
	public List<IWlanElement> getTaggedParameter() {
		return taggedParameter;
	}
}
