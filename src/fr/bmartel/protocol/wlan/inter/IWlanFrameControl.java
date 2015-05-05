package fr.bmartel.protocol.wlan.inter;

/**
 * Template for frame control 802.11 (!=control frame management type)
 * 
 * @author Bertrand Martel
 *
 */
public interface IWlanFrameControl {
	
	public byte getProtocolVersion();

	public byte getType();

	public byte getSubType();
	
	public boolean isToDS();
	
	public boolean isFromDS();
	
	public boolean isMoreFragmentation();
	
	public boolean isRetry();
	
	public boolean isPowerManagement();
	
	public boolean isMoreData();
	
	public boolean isWep();
	
	public boolean isOrder();
}
