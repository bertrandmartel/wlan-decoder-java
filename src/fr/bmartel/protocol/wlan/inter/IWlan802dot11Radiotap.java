package fr.bmartel.protocol.wlan.inter;

import fr.bmartel.protocol.radiotap.inter.IRadioTapFrame;
import fr.bmartel.protocol.wlan.frame.IWlanFrame;

/**
 *
 * Template for Wlan 802.11 frame with preceding radiotap header
 * 
 * @author Bertrand Martel
 *
 */
public interface IWlan802dot11Radiotap {

	public IRadioTapFrame getRadioTap() ;
	
	public IWlanFrameControl getFrameControl();
	
	public IWlanFrame getFrame();
}
