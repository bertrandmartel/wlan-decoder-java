package fr.bmartel.wlandecoder;

import java.util.ArrayList;

import fr.bmartel.protocol.wlan.frame.IWlanDataFrame;
import fr.bmartel.protocol.wlan.frame.IWlanManagementFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IClearToSendFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IContentionFreeFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IContentionFreeReceiveAckFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IPowerSavePollingFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IRequestToSendFrame;
import fr.bmartel.protocol.wlan.frame.control.inter.IackFrame;
import fr.bmartel.protocol.wlan.frame.data.inter.IDataFrame;
import fr.bmartel.protocol.wlan.frame.data.inter.INullFrame;
import fr.bmartel.protocol.wlan.frame.data.inter.IQosDataFrame;
import fr.bmartel.protocol.wlan.frame.management.DisassociationFrame;
import fr.bmartel.protocol.wlan.frame.management.element.IWlanElement;
import fr.bmartel.protocol.wlan.frame.management.element.inter.IDsssParameterSetElement;
import fr.bmartel.protocol.wlan.frame.management.element.inter.IErpElement;
import fr.bmartel.protocol.wlan.frame.management.element.inter.IExtendedSupportedRateElement;
import fr.bmartel.protocol.wlan.frame.management.element.inter.IHtCapabilitiesElement;
import fr.bmartel.protocol.wlan.frame.management.element.inter.ISsidElement;
import fr.bmartel.protocol.wlan.frame.management.element.inter.ISupportedRateElement;
import fr.bmartel.protocol.wlan.frame.management.element.inter.ITimElement;
import fr.bmartel.protocol.wlan.frame.management.inter.IAssociationRequestFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IAssociationResponseFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IAuthenticationFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IBeaconFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IDeauthenticationFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IProbeRequestFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IProbeResponseFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IReassociationRequestFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IReassociationResponseFrame;
import fr.bmartel.protocol.wlan.frame.management.inter.IibssAnnoucementIndicationMapFrame;
import fr.bmartel.protocol.wlan.inter.IWlan802dot11Radiotap;
import fr.bmartel.protocol.wlan.inter.IWlanControlFrame;
import fr.bmartel.protocol.wlan.utils.ByteUtils;

/**
 * Display decoding info on console
 * 
 * @author Bertrand Martel
 *
 */
public class DisplayDecodingInfo {

	private final static String tab ="-------";
	
	/**
	 * Display all information about Wlan802dot11 frames
	 * 
	 * @param wlanDecodedFrameList
	 */
	public static void displayAllInfo(ArrayList<IWlan802dot11Radiotap> wlanDecodedFrameList)
	{
		for (int i = 0; i <wlanDecodedFrameList.size();i++)
		{
			System.out.println("Wlan frame control");
		
			System.out.println(tab+"protocolVersion   : " + (wlanDecodedFrameList.get(i).getFrameControl().getProtocolVersion() & 0xFF));
			System.out.println(tab+"type              : " + (wlanDecodedFrameList.get(i).getFrameControl().getType() & 0xFF));
			System.out.println(tab+"subType           : " + (wlanDecodedFrameList.get(i).getFrameControl().getSubType() & 0xFF));
			System.out.println(tab+"toDS              : " + wlanDecodedFrameList.get(i).getFrameControl().isToDS());
			System.out.println(tab+"fromDS            : " + wlanDecodedFrameList.get(i).getFrameControl().isFromDS());
			System.out.println(tab+"moreFragmentation : " + wlanDecodedFrameList.get(i).getFrameControl().isMoreFragmentation());
			System.out.println(tab+"retry             : " + wlanDecodedFrameList.get(i).getFrameControl().isRetry());
			System.out.println(tab+"powerManagement   : " + wlanDecodedFrameList.get(i).getFrameControl().isPowerManagement());
			System.out.println(tab+"moreData          : " + wlanDecodedFrameList.get(i).getFrameControl().isMoreData());
			System.out.println(tab+"wep               : " + wlanDecodedFrameList.get(i).getFrameControl().isWep());
			System.out.println(tab+"order             : " + wlanDecodedFrameList.get(i).getFrameControl().isOrder());
			
			if (wlanDecodedFrameList.get(i).getFrame() instanceof IWlanManagementFrame)
			{
				System.out.println("Wlan management frame");
				IWlanManagementFrame managementFrame= (IWlanManagementFrame) wlanDecodedFrameList.get(i).getFrame();
				
				System.out.println(tab+"duration id     : "+ ByteUtils.byteArrayToStringMessage("", managementFrame.getDurationId(), '|'));
				System.out.println(tab+"destinationAddr : "+ ByteUtils.byteArrayToStringMessage("",managementFrame.getDestinationAddr(), '|'));
				System.out.println(tab+"sourceAddr      : "+ ByteUtils.byteArrayToStringMessage("", managementFrame.getSourceAddr(), '|'));
				System.out.println(tab+"bssid           : "+ ByteUtils.byteArrayToStringMessage("", managementFrame.getBSSID(), '|'));
				System.out.println(tab+"sequenceControl : "+ ByteUtils.byteArrayToStringMessage("",managementFrame.getSequenceControl(), '|'));
				System.out.println(tab+"frameBody       : "+ ByteUtils.byteArrayToStringMessage("",managementFrame.getFrameBody(), '|'));
				System.out.println(tab+"fcs             : "+ ByteUtils.byteArrayToStringMessage("",managementFrame.getFcs(), '|'));
				
				if (wlanDecodedFrameList.get(i).getFrame() instanceof IAssociationRequestFrame)
				{
					System.out.println(tab+"MANAGEMENT ASSOCIATION REQUEST FRAME");
					
					IAssociationRequestFrame associationRequestFrame =(IAssociationRequestFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+tab+"listenInterval         : "+ ByteUtils.byteArrayToStringMessage("", associationRequestFrame.getListenInterval(),'|'));
					System.out.println(tab+tab+"capability information : "+ ByteUtils.byteArrayToStringMessage("", associationRequestFrame.getCapabilityInfo(),'|'));

					for (int j = 0; j< associationRequestFrame.getTaggedParameter().size(); j++) {
						displayTaggedParameter(associationRequestFrame.getTaggedParameter().get(j));
					}
				}
				else if(wlanDecodedFrameList.get(i).getFrame() instanceof IAssociationResponseFrame)
				{
					System.out.println(tab+"MANAGEMENT ASSOCATION RESPONSE FRAME");
					
					IAssociationResponseFrame associationFrame =(IAssociationResponseFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+tab+"statusCode             : " + associationFrame.getStatusCode());
					System.out.println(tab+tab+"associationId          : "+ associationFrame.getAssociationId());
					System.out.println(tab+tab+"capability information : "+ ByteUtils.byteArrayToStringMessage("", associationFrame.getCapabilityInfo(),'|'));
					
					for (int j = 0; j< associationFrame.getTaggedParameter().size(); j++) {
						displayTaggedParameter(associationFrame.getTaggedParameter().get(j));
					}
				}
				else if(wlanDecodedFrameList.get(i).getFrame() instanceof IAuthenticationFrame)
				{
					System.out.println(tab+"MANAGEMENT AUTHENTICATION FRAME");
					
					IAuthenticationFrame authenticationFrame =(IAuthenticationFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+tab+"authenticationAlgorithmNum : "+ authenticationFrame.getAuthenticationAlgorithmNum());
					System.out.println(tab+tab+"authenticationSeqNum       : "+ authenticationFrame.getAuthenticationSeqNum());
					System.out.println(tab+tab+"statusCode                 : " + authenticationFrame.getStatusCode());
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IBeaconFrame)
				{
					System.out.println(tab+"MANAGEMENT BEACON FRAME");
					
					IBeaconFrame beaconFrame =(IBeaconFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+tab+"timestamp              : "+ ByteUtils.byteArrayToStringMessage("",beaconFrame.getTimestamp(), '|'));
					System.out.println(tab+tab+"beaconInterval         : "+ ByteUtils.convertByteArrayToInt(beaconFrame.getBeaconInterval()));
					System.out.println(tab+tab+"capability information : "+ ByteUtils.byteArrayToStringMessage("", beaconFrame.getCapabilityInfo(),'|'));
					
					for (int j = 0; j< beaconFrame.getTaggedParameter().size(); j++) {
						displayTaggedParameter(beaconFrame.getTaggedParameter().get(j));
					}
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IDeauthenticationFrame)
				{
					System.out.println(tab+"MANAGEMENT DEAUTHENTICATION FRAME");
					
					System.out.println(tab+tab+"reasonCode : " + ((IDeauthenticationFrame)wlanDecodedFrameList.get(i).getFrame()).getReasonCode());
				}
				else if(wlanDecodedFrameList.get(i).getFrame() instanceof DisassociationFrame)
				{
					System.out.println(tab+"MANAGEMENT DISASSOCIATION FRAME");
					
					System.out.println(tab+tab+"statusCode : " + ((DisassociationFrame)wlanDecodedFrameList.get(i).getFrame()).getStatusCode());
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IibssAnnoucementIndicationMapFrame)
				{
					System.out.println(tab+"MANAGEMENT IBSS ANNOUCEMENT INDICATION MAP FRAME");
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IProbeRequestFrame)
				{
					System.out.println(tab+"MANAGEMENT - PROBE REQUEST FRAME");
					IProbeRequestFrame probeFrame =(IProbeRequestFrame) wlanDecodedFrameList.get(i).getFrame();
					
					for (int j = 0; j< probeFrame.getTaggedParameter().size(); j++) {
						displayTaggedParameter(probeFrame.getTaggedParameter().get(j));
					}
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IProbeResponseFrame)
				{
					IProbeResponseFrame probeFrame =(IProbeResponseFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+"MANAGEMENT - PROBE RESPONSE FRAME");
					System.out.println(tab+tab+"timestamp              : "+ ByteUtils.byteArrayToStringMessage("",probeFrame.getTimestamp(), '|'));
					System.out.println(tab+tab+"beaconInterval         : "+ ByteUtils.convertByteArrayToInt(probeFrame.getBeaconInterval()));
					System.out.println(tab+tab+"capability information : "+ ByteUtils.byteArrayToStringMessage("", probeFrame.getCapabilityInformation(),'|'));
					
					for (int j = 0; j< probeFrame.getTaggedParameter().size(); j++) {
						displayTaggedParameter(probeFrame.getTaggedParameter().get(j));
					}
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IReassociationRequestFrame)
				{
					IReassociationRequestFrame reassociationFrame =(IReassociationRequestFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+"MANAGEMENT REASSOCIATION FRAME");
					
					System.out.println(tab+tab+"listenInterval         : "+ ByteUtils.byteArrayToStringMessage("", reassociationFrame.getListenInterval(),'|'));
					System.out.println(tab+tab+"currentAPAdress        : "+ ByteUtils.convertByteArrayToInt(reassociationFrame.getCurrentAPAdress()));
					System.out.println(tab+tab+"capability information : "+ ByteUtils.byteArrayToStringMessage("", reassociationFrame.getCapabilityInfo(),'|'));
					
					for (int j = 0; j< reassociationFrame.getTaggedParameter().size(); j++) {
						displayTaggedParameter(reassociationFrame.getTaggedParameter().get(j));
					}
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IReassociationResponseFrame)
				{
					IReassociationResponseFrame reassociationFrame =(IReassociationResponseFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+"MANAGEMENT REASSOCATION RESPONSE FRAME");

					System.out.println(tab+tab+"statusCode             : " + reassociationFrame.getStatusCode());
					System.out.println(tab+tab+"associationId          : "+ reassociationFrame.getAssociationId());
					System.out.println(tab+tab+"capability information : "+ ByteUtils.byteArrayToStringMessage("", reassociationFrame.getCapabilityInfo(),'|'));
					
					for (int j = 0; j< reassociationFrame.getTaggedParameter().size(); j++) {
						displayTaggedParameter(reassociationFrame.getTaggedParameter().get(j));
					}
				}
			}
			if (wlanDecodedFrameList.get(i).getFrame() instanceof IWlanDataFrame)
			{
				System.out.println("Wlan data frame");
				IWlanDataFrame dataFrame= (IWlanDataFrame) wlanDecodedFrameList.get(i).getFrame();
				
				System.out.println(tab+"duration id     : "+ ByteUtils.byteArrayToStringMessage("", dataFrame.getDurationId(), '|'));
				System.out.println(tab+"destinationAddr : "+ ByteUtils.byteArrayToStringMessage("",dataFrame.getDestinationAddr(), '|'));
				System.out.println(tab+"sourceAddr      : "+ ByteUtils.byteArrayToStringMessage("", dataFrame.getSourceAddr(), '|'));
				System.out.println(tab+"bssid           : "+ ByteUtils.byteArrayToStringMessage("", dataFrame.getBSSID(), '|'));
				System.out.println(tab+"sequenceControl : "+ ByteUtils.byteArrayToStringMessage("",dataFrame.getSequenceControl(), '|'));
				System.out.println(tab+"frameBody       : "+ ByteUtils.byteArrayToStringMessage("",dataFrame.getFrameBody(), '|'));
				
				if (wlanDecodedFrameList.get(i).getFrame() instanceof IDataFrame)
					System.out.println(tab+"DATA FRAME");
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof INullFrame)
					System.out.println(tab+"NULL FRAME");
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IQosDataFrame)
				{
					System.out.println(tab+"QOS DATA FRAME");
					System.out.println(tab+tab+"qos control : "+ ByteUtils.byteArrayToStringMessage("", ((IQosDataFrame)wlanDecodedFrameList.get(i).getFrame()).getQosControl(), '|'));
				}		
			}
			if (wlanDecodedFrameList.get(i).getFrame() instanceof IWlanControlFrame)
			{
				
				System.out.println("Wlan control frame");
				
				if (wlanDecodedFrameList.get(i).getFrame() instanceof IackFrame)
				{
					IackFrame ackFrame =(IackFrame) wlanDecodedFrameList.get(i).getFrame();
					System.out.println(tab+"CONTROL FRAME - ACK PACKET");
					System.out.println(tab+tab+"duration id  : "+ ByteUtils.byteArrayToStringMessage("", ackFrame.getDurationId(), '|'));
					System.out.println(tab+tab+"receiverAddr : "+ ByteUtils.byteArrayToStringMessage("", ackFrame.getReceiverAddr(),'|'));
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IClearToSendFrame)
				{
					IClearToSendFrame cts =(IClearToSendFrame) wlanDecodedFrameList.get(i).getFrame();
					System.out.println(tab+"CONTROL FRAME - CLEAR TO SEND PACKET");
					System.out.println(tab+tab+"duration id  : "+ ByteUtils.byteArrayToStringMessage("", cts.getDurationId(), '|'));
					System.out.println(tab+tab+"receiverAddr : "+ ByteUtils.byteArrayToStringMessage("", cts.getReceiverAddr(),'|'));
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IContentionFreeFrame)
				{
					IContentionFreeFrame contentionFr =(IContentionFreeFrame) wlanDecodedFrameList.get(i).getFrame();
					System.out.println(tab+"CONTROL FRAME - CONTENTION FREE PACKET");
					System.out.println(tab+tab+ "duration id : "+ ByteUtils.byteArrayToStringMessage("", contentionFr.getDurationId(), '|'));
					System.out.println(tab+tab+"receiverAddr : "+ ByteUtils.byteArrayToStringMessage("", contentionFr.getReceiverAddr(),'|'));
					System.out.println(tab+tab+"bss id       : "+ ByteUtils.byteArrayToStringMessage("", contentionFr.getBssid(), '|'));
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IContentionFreeReceiveAckFrame)
				{
					IContentionFreeReceiveAckFrame contentionFr =(IContentionFreeReceiveAckFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+"CONTROL FRAME - CONTENTION FREE AND RECEIVE ACK PACKET");
					System.out.println(tab+tab+"duration id  : "+ ByteUtils.byteArrayToStringMessage("", contentionFr.getDurationId(), '|'));
					System.out.println(tab+tab+"receiverAddr : "+ ByteUtils.byteArrayToStringMessage("", contentionFr.getReceiverAddr(),'|'));
					System.out.println(tab+tab+"bss id       : "+ ByteUtils.byteArrayToStringMessage("", contentionFr.getBssid(), '|'));
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IPowerSavePollingFrame)
				{
					IPowerSavePollingFrame powerSave =(IPowerSavePollingFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+"CONTROL FRAME - POWER SAVE POLLING PACKET");
					System.out.println(tab+tab+"association id : "+ ByteUtils.byteArrayToStringMessage("", powerSave.getAssociationId(),'|'));
					System.out.println(tab+tab+"bss id         : "+ ByteUtils.byteArrayToStringMessage("", powerSave.getBssid(), '|'));
					System.out.println(tab+tab+"transmitter id : "+ ByteUtils.byteArrayToStringMessage("", powerSave.getTransmitterId(),'|'));
				}
				else if (wlanDecodedFrameList.get(i).getFrame() instanceof IRequestToSendFrame)
				{
					IRequestToSendFrame rts =(IRequestToSendFrame) wlanDecodedFrameList.get(i).getFrame();
					
					System.out.println(tab+"CONTROL FRAME - REQUEST TO SEND PACKET");
					System.out.println(tab+tab+"duration id     : "+ ByteUtils.byteArrayToStringMessage("", rts.getDurationId(), '|'));
					System.out.println(tab+tab+"receiverAddr    : "+ ByteUtils.byteArrayToStringMessage("", rts.getReceiverAddr(),'|'));
					System.out.println(tab+tab+"transmitterAddr : "+ ByteUtils.byteArrayToStringMessage("",rts.getTransmitterAddr(), '|'));
				}
			}
			
		}
	}
	
	/**
	 * Display element information
	 * 
	 * @param elementList
	 * 		List of wlan element 
	 */
	public static void displayTaggedParameter(IWlanElement elementList)
	{
		System.out.println(tab+tab+tab + "Dynamic Elements");
		System.out.println(tab+tab+tab + "element id : "+ (elementList.getElementId() & 0xFF));
		
		if (elementList instanceof IDsssParameterSetElement)
		{
			IDsssParameterSetElement element = (IDsssParameterSetElement)elementList;
			System.out.println(tab+tab+tab+tab+"currentChannel : "+ element.getCurrentChannel());
			System.out.println(tab+tab+tab+tab+"frequency      : " + element.getFrequency());
		}
		else if (elementList instanceof IErpElement)
		{
			IErpElement element = (IErpElement)elementList;
			System.out.println(tab+tab+tab+tab+"isErpPresent         : " + element.isErpPresent());
			System.out.println(tab+tab+tab+tab+"useProtection        : " + element.useProtection());
			System.out.println(tab+tab+tab+tab+"isBarkerPreambleMode : " + element.isBarkerPreambleMode());
		}
		else if (elementList instanceof IExtendedSupportedRateElement)
		{
			IExtendedSupportedRateElement element = (IExtendedSupportedRateElement)elementList;
			String dataRateStr = "";
			for (int i = 0; i < element.getDataRate().length; i++) {
				dataRateStr += ((element.getDataRate()[i] & 0x7F) * 500) + "kbps,";
			}
			System.out.println(tab+tab+tab+tab+"extended data rates supported : " + dataRateStr);
		}
		else if (elementList instanceof IHtCapabilitiesElement)
		{
			IHtCapabilitiesElement element = (IHtCapabilitiesElement)elementList;
			System.out.println(tab+tab+tab+tab+"support short 20Mhz           : "+ element.getHtCapabilityInfo().isSupportShortGi20Mhz());
			System.out.println(tab+tab+tab+tab+"support short 40Mhz           : "+ element.getHtCapabilityInfo().isSupportShortGi40Mhz());

			System.out.println(tab+tab+tab+tab+"channel width 20MHz supported : "+ !element.getHtCapabilityInfo().isSupportedChannelWidthSet());
			System.out.println(tab+tab+tab+tab+"channel width 40MHz supported : "+ element.getHtCapabilityInfo().isSupportedChannelWidthSet());
			
			System.out.println(tab+tab+tab+tab+"AMPDU paramters               : "	+ (element.getAmpduParameters() & 0xFF));
			
			for (int i = 0; i < element.getSupportedMCSSet().getMcsList().size(); i++) {
				System.out.println(tab+tab+tab+tab+"MCS Supported : n°"+ element.getSupportedMCSSet().getMcsList().get(i).getMcsIndex() + "-"+ element.getSupportedMCSSet().getMcsList().get(i).getModulation());
			}
			
			System.out.println(tab+tab+tab+tab+"HT Extended capabilities           : "+ ByteUtils.byteArrayToStringMessage("",element.getHtExtendedCapabilities(), '|'));
			System.out.println(tab+tab+tab+tab+"Transmit beam forming capabilities : "+ ByteUtils.byteArrayToStringMessage("",element.getTransmitBeamformingCapabilities(), '|'));
			System.out.println(tab+tab+tab+tab+"ASEL capabilities                  : "+ (element.getAselCapabilities() & 0xFF));
		}
		else if (elementList instanceof ISsidElement)
		{
			System.out.println(tab+tab+tab+tab+"SSID : " +((ISsidElement)elementList).getSsid());
		}
		else if (elementList instanceof ISupportedRateElement)
		{
			ISupportedRateElement element = (ISupportedRateElement)elementList;
			String dataRateStr = "";
			
			for (int i = 0; i < element.getDataRate().length; i++) {
				dataRateStr += ((element.getDataRate()[i] & 0x7F) * 500) + "kbps,";
			}
			
			System.out.println(tab+tab+tab+tab+"data rates supported : "+ dataRateStr);
		}
		else if (elementList instanceof ITimElement)
		{
			ITimElement element = (ITimElement)elementList;
			System.out.println(tab+tab+tab+tab+"number of beacon frame before next DTIM  : "+ (element.getDTIMcount() & 0xFF));
			System.out.println(tab+tab+tab+tab+"number of beacon interval between 2 DTIM : "+ (element.getDTIMperiod() & 0XFF));
			System.out.println(tab+tab+tab+tab+"TIM bitmapControl                        : "+ (element.getBitmapControl() & 0XFF));
			System.out.println(ByteUtils.byteArrayToStringMessage(tab+tab+tab+tab+"TIM partialVirtualBitmap", element.getPartialVirtualBitmap(), '|'));;
		}
	}
	
}
