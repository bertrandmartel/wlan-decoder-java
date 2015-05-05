package fr.bmartel.protocol.wlan;

import fr.bmartel.protocol.wlan.inter.IWlanFrameControl;


/**
 * Wlan 802.11 Control frame defined by 802.11 Mac Header<br/>
 * http://technet.microsft.com/en-us/library/cc757419(v=ws.10).aspx
 * 
 * @author Bertrand Martel
 * 
 */
public class WlanFrameControl implements IWlanFrameControl{

	/** protocol version on 2bits */
	private byte protocolVersion = 0x00;

	/** wlan type on 2bits */
	private byte type = 0x00;

	/** wlan sub type on 4 bits */
	private byte subType = 0x00;

	/** to Distribution Service control bit (1bit) */
	private boolean toDS = false;

	/** from Distribution Service control bit (1bit) */
	private boolean fromDS = false;

	/** used for large packets (1bit) */
	private boolean moreFragmentation = false;

	/** retry bit => indicate if the frame is being repeated or not */
	private boolean retry = false;

	/** indicates whether the sending STA is in active mode or power-save mode */
	private boolean powerManagement = false;

	/** additionnal data bit indication */
	private boolean moreData = false;

	/** indicate if data is encrypted */
	private boolean wep = false;

	/** indicates that all received data frames must be processed in order. */
	private boolean order = false;

	public WlanFrameControl(byte[] controlFrame) {
		protocolVersion = (byte) (controlFrame[0] & 0b00000011);
		type = (byte) ((controlFrame[0] & 0b00001100) >> 2);
		subType = (byte) ((controlFrame[0] & 0b11110000) >> 4);
		toDS = (controlFrame[1] & 0b00000001) != 0;
		fromDS = (controlFrame[1] & 0b00000010) != 0;
		moreFragmentation = (controlFrame[1] & 0b00000100) != 0;
		retry = (controlFrame[1] & 0b00001000) != 0;
		powerManagement = (controlFrame[1] & 0b00010000) != 0;
		moreData = (controlFrame[1] & 0b00100000) != 0;
		wep = (controlFrame[1] & 0b01000000) != 0;
		order = (controlFrame[1] & 0b10000000) != 0;
	}

	@Override
	public byte getProtocolVersion() {
		return protocolVersion;
	}

	@Override
	public byte getType() {
		return type;
	}

	@Override
	public byte getSubType() {
		return subType;
	}
	
	@Override
	public boolean isToDS() {
		return toDS;
	}

	@Override
	public boolean isFromDS() {
		return fromDS;
	}
	
	@Override
	public boolean isMoreFragmentation() {
		return moreFragmentation;
	}

	@Override
	public boolean isRetry() {
		return retry;
	}
	
	@Override
	public boolean isPowerManagement() {
		return powerManagement;
	}
	
	@Override
	public boolean isMoreData() {
		return moreData;
	}

	@Override
	public boolean isWep() {
		return wep;
	}

	@Override
	public boolean isOrder() {
		return order;
	}
}
