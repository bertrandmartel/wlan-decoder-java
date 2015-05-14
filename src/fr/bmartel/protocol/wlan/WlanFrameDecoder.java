/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Bertrand Martel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.bmartel.protocol.wlan;

import fr.bmartel.protocol.wlan.constant.WlanFrameSubType;
import fr.bmartel.protocol.wlan.constant.WlanFrameType;
import fr.bmartel.protocol.wlan.frame.IWlanFrame;
import fr.bmartel.protocol.wlan.frame.control.AckFrame;
import fr.bmartel.protocol.wlan.frame.control.ClearToSendFrame;
import fr.bmartel.protocol.wlan.frame.control.ContentionFreeFrame;
import fr.bmartel.protocol.wlan.frame.control.ContentionFreeReceiveAckFrame;
import fr.bmartel.protocol.wlan.frame.control.PowerSavePollingFrame;
import fr.bmartel.protocol.wlan.frame.control.RequestToSendFrame;
import fr.bmartel.protocol.wlan.frame.data.DataFrame;
import fr.bmartel.protocol.wlan.frame.data.NullFrame;
import fr.bmartel.protocol.wlan.frame.data.QosDataFrame;
import fr.bmartel.protocol.wlan.frame.management.AssociationRequestFrame;
import fr.bmartel.protocol.wlan.frame.management.AuthenticationFrame;
import fr.bmartel.protocol.wlan.frame.management.BeaconFrame;
import fr.bmartel.protocol.wlan.frame.management.DeAuthenticationFrame;
import fr.bmartel.protocol.wlan.frame.management.DisassociationFrame;
import fr.bmartel.protocol.wlan.frame.management.IbssAnnouncementIndicationMapFrame;
import fr.bmartel.protocol.wlan.frame.management.ProbeRequestFrame;
import fr.bmartel.protocol.wlan.frame.management.ProbeResponseFrame;
import fr.bmartel.protocol.wlan.frame.management.ReAssociationResponseFrame;
import fr.bmartel.protocol.wlan.frame.management.ReassociationRequestFrame;
import fr.bmartel.protocol.wlan.inter.IWlanFrameControl;

/**
 * Decode 802.11 Wlan frame<br/>
 * http://technet.microsoft.com/en-us/library/cc757419(v=ws.10).aspx<br/>
 * http://inst.eecs.berkeley.edu/~ee122/sp07/80211.pdf
 * 
 * @author Bertrand Martel
 * 
 */
public class WlanFrameDecoder {

	/**
	 * contains control information used for defining the type of 802.11 MAC
	 * frame and providing information
	 */
	private IWlanFrameControl frameControl = null;

	private IWlanFrame wlanFrame = null;

	/**
	 * Decode frame extracted from byte array (byte array is complete wlan
	 * frames)
	 * 
	 * @param frame
	 *            byte array data
	 */
	public WlanFrameDecoder(byte[] frame) {

		frameControl = new WlanFrameControl(new byte[] { frame[0], frame[1] });

		// define new frame shifted to the right
		byte[] wlanFrameData = new byte[frame.length - 2];
		System.arraycopy(frame, 2, wlanFrameData, 0, frame.length - 2);

		switch (frameControl.getType()) {
		case WlanFrameType.MANAGEMENT_FRAME_TYPE:

			switch (frameControl.getSubType()) {

			case WlanFrameSubType.MANAGEMENT_ASSOCIATION_REQUEST_FRAME:
				wlanFrame = new AssociationRequestFrame(wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_REASSOCIATION_REQUEST_FRAME:
				wlanFrame = new ReassociationRequestFrame(wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_REASSOCIATION_RESPONSE_FRAME:
				wlanFrame = new ReAssociationResponseFrame(wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_PROBE_REQUEST_FRAME:
				wlanFrame = new ProbeRequestFrame(wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_PROBE_RESPONSE_FRAME:
				wlanFrame = new ProbeResponseFrame(wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_BEACON_FRAME:
				wlanFrame = new BeaconFrame(wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_ANNOUNCEMENT_TRAFFIC_INDICATION_MESSAGE_FRAME:
				wlanFrame = new IbssAnnouncementIndicationMapFrame(
						wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_DISASSOCIATION_FRAME:
				wlanFrame = new DisassociationFrame(wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_AUTHENTICATION_FRAME:
				wlanFrame = new AuthenticationFrame(wlanFrameData);
				break;
			case WlanFrameSubType.MANAGEMENT_DEAUTHENTICATION_FRAME:
				wlanFrame = new DeAuthenticationFrame(wlanFrameData);
				break;
			}
			break;

		case WlanFrameType.CONTROL_FRAME_TYPE:

			switch (frameControl.getSubType()) {
			case WlanFrameSubType.CONTROL_POWER_SAVE_POLLING_PACKET:
				wlanFrame = new PowerSavePollingFrame(wlanFrameData);
				break;
			case WlanFrameSubType.CONTROL_REQUEST_TO_SEND:
				wlanFrame = new RequestToSendFrame(wlanFrameData);
				break;
			case WlanFrameSubType.CONTROL_CLEAR_TO_SEND:
				wlanFrame = new ClearToSendFrame(wlanFrameData);
				break;
			case WlanFrameSubType.CONTROL_ACK:
				wlanFrame = new AckFrame(wlanFrameData);
				break;
			case WlanFrameSubType.CONTROL_SIGNAL_CONTENTION_FREE:
				wlanFrame = new ContentionFreeFrame(wlanFrameData);
				break;
			case WlanFrameSubType.CONTROL_SIGNAL_CONTENTION_FREE_AND_RECEIVE_ACK:
				wlanFrame = new ContentionFreeReceiveAckFrame(wlanFrameData);
				break;
			}
			break;
		case WlanFrameType.DATA_FRAME_TYPE:
			switch (frameControl.getSubType()) {
			case WlanFrameSubType.DATA_FRAME:
				wlanFrame = new DataFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_CONTENTION_FREE_ACK:
				wlanFrame = new DataFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_CONTENTION_FREE_POLL:
				wlanFrame = new DataFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_CONTENTION_FREE_ACK_PLUS_POLL:
				wlanFrame = new DataFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_NULL_FRAME:
				wlanFrame = new NullFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.CONTENTION_FREE_ACK:
				wlanFrame = new DataFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.CONTENTION_FREE_POLL:
				wlanFrame = new DataFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.CONTENTION_FREE_ACK_PLUS_POLL:
				wlanFrame = new DataFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_QOS_FRAME:
				wlanFrame = new QosDataFrame(wlanFrameData,
						frameControl.isToDS(), frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_QOS_CONTENTION_FREE_ACK_FRAME:
				wlanFrame = new QosDataFrame(wlanFrameData,
						frameControl.isToDS(), frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_QOS_CONTENTION_FREE_POLL_FRAME:
				wlanFrame = new QosDataFrame(wlanFrameData,
						frameControl.isToDS(), frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_QOS_CONTENTION_FREE_ACK_PLUS_POLL_FRAME:
				wlanFrame = new QosDataFrame(wlanFrameData,
						frameControl.isToDS(), frameControl.isFromDS());
				break;
			case WlanFrameSubType.DATA_QOS_NULL_FRAME:
				wlanFrame = new NullFrame(wlanFrameData, frameControl.isToDS(),
						frameControl.isFromDS());
				break;
			case WlanFrameSubType.QOS_CONTENTION_FREE_ACK_FRAME:
				wlanFrame = new QosDataFrame(wlanFrameData,
						frameControl.isToDS(), frameControl.isFromDS());
				break;
			case WlanFrameSubType.QOS_CONTENTION_FREE_POLL_FRAME:
				wlanFrame = new QosDataFrame(wlanFrameData,
						frameControl.isToDS(), frameControl.isFromDS());
				break;
			case WlanFrameSubType.QOS_CONTENTION_FREE_ACK_PLUS_POLL_FRAME:
				wlanFrame = new QosDataFrame(wlanFrameData,
						frameControl.isToDS(), frameControl.isFromDS());
				break;
			}
			break;
		}
	}

	public IWlanFrameControl getFrameControl() {
		return frameControl;
	}

	public IWlanFrame getWlanFrame() {
		return wlanFrame;
	}
}
