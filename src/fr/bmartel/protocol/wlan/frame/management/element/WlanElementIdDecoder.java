package fr.bmartel.protocol.wlan.frame.management.element;

import java.util.ArrayList;
import java.util.List;

import fr.bmartel.protocol.wlan.WlanDecoder;
import fr.bmartel.protocol.wlan.frame.management.element.impl.DsssParameterSetElement;
import fr.bmartel.protocol.wlan.frame.management.element.impl.ErpElement;
import fr.bmartel.protocol.wlan.frame.management.element.impl.ExtendedSupportedRateElement;
import fr.bmartel.protocol.wlan.frame.management.element.impl.HTCapabilitiesElement;
import fr.bmartel.protocol.wlan.frame.management.element.impl.SSIDElement;
import fr.bmartel.protocol.wlan.frame.management.element.impl.SupportedRateElement;
import fr.bmartel.protocol.wlan.frame.management.element.impl.TimElement;

/**
 * Decode Wlan 802.11 element id
 * 
 * @author Bertrand Martel
 * 
 */
public class WlanElementIdDecoder {

	public WlanElementIdDecoder() {
	}

	/**
	 * decode element id from byte array frame
	 * 
	 * @param frame
	 *            data shifted to beginning of element id frames
	 * @return
	 */
	public List<IWlanElement> decode(byte[] frame) {
		// list of wlan element id to be created
		List<IWlanElement> listOfElements = new ArrayList<IWlanElement>();
		if (frame.length >= 2) {
			// decode frame until no element id frame can be read
			boolean endOfFrame = false;
			byte[] currentFrame = frame;

			while (!endOfFrame) {
				byte elementId = currentFrame[0];
				int length = currentFrame[1] & 0xFF;
				byte[] treatedFrame = new byte[length];
				IWlanElement element = null;
				
				System.arraycopy(currentFrame, 2, treatedFrame, 0, length);

				boolean done = false;
				
				switch (elementId) {
				case SSIDElement.id:
					done=true;
					element = new SSIDElement(treatedFrame);
					break;
				case SupportedRateElement.id:
					done=true;
					element = new SupportedRateElement(treatedFrame);
					break;
				case HTCapabilitiesElement.id:
					done=true;
					element = new HTCapabilitiesElement(treatedFrame);
					break;
				case ExtendedSupportedRateElement.id:
					done=true;
					element = new ExtendedSupportedRateElement(treatedFrame);
					break;
				case TimElement.id:
					done=true;
					element = new TimElement(treatedFrame);
					break;
				case ErpElement.id:
					done=true;
					element = new ErpElement(treatedFrame);
					break;
				case DsssParameterSetElement.id:
					done=true;
					element = new DsssParameterSetElement(treatedFrame);
					break;
				}
				if (!done && WlanDecoder.DISPLAY_ELEMENT_NOT_DECODED)
				{
					System.out.println("Element id not decoded => " + (elementId & 0XFF));
				}
				if (element != null) {
					listOfElements.add(element);
				}

				if ((currentFrame.length - length - 2) > 0) {
					byte[] dest = new byte[currentFrame.length - length - 2];
					System.arraycopy(currentFrame, length + 2, dest, 0,
							currentFrame.length - length - 2);
					currentFrame = dest;
				} else {
					endOfFrame = true;
				}
			}
		}
		return listOfElements;
	}
}
