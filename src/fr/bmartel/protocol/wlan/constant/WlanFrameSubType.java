package fr.bmartel.protocol.wlan.constant;

/**
 * Define Wlan frame subtype list<br/>
 * http://www.wildpackets.com/resources/compendium/wireless_lan/
 * wlan_packet_types<br/>
 * http://flylib.com/books/en/2.799.1.38/1/
 * 
 * @author Bertrand Martel
 * 
 */
public class WlanFrameSubType {

	public final static byte MANAGEMENT_ASSOCIATION_REQUEST_FRAME = 0x00;
	public final static byte MANAGEMENT_ASSOCIATION_RESPONSE_FRAME = 0x01;
	public final static byte MANAGEMENT_REASSOCIATION_REQUEST_FRAME = 0x02;
	public final static byte MANAGEMENT_REASSOCIATION_RESPONSE_FRAME = 0x03;
	public final static byte MANAGEMENT_PROBE_REQUEST_FRAME = 0x04;
	public final static byte MANAGEMENT_PROBE_RESPONSE_FRAME = 0x05;
	public final static byte MANAGEMENT_BEACON_FRAME = 0x08;
	public final static byte MANAGEMENT_ANNOUNCEMENT_TRAFFIC_INDICATION_MESSAGE_FRAME = 0x09;
	public final static byte MANAGEMENT_DISASSOCIATION_FRAME = 0x0A;
	public final static byte MANAGEMENT_AUTHENTICATION_FRAME = 0x0B;
	public final static byte MANAGEMENT_DEAUTHENTICATION_FRAME = 0x0C;

	public final static byte CONTROL_POWER_SAVE_POLLING_PACKET = 0x0A;
	public final static byte CONTROL_REQUEST_TO_SEND = 0x0B;
	public final static byte CONTROL_CLEAR_TO_SEND = 0x0C;
	public final static byte CONTROL_ACK = 0x0D;
	public final static byte CONTROL_SIGNAL_CONTENTION_FREE = 0x0E;
	public final static byte CONTROL_SIGNAL_CONTENTION_FREE_AND_RECEIVE_ACK = 0x0F;

	public final static byte DATA_FRAME = 0x00;
	public final static byte DATA_CONTENTION_FREE_ACK = 0x01;
	public final static byte DATA_CONTENTION_FREE_POLL = 0x02;
	public final static byte DATA_CONTENTION_FREE_ACK_PLUS_POLL = 0x03;
	public final static byte DATA_NULL_FRAME = 0x04;

	public final static byte CONTENTION_FREE_ACK = 0x05;
	public final static byte CONTENTION_FREE_POLL = 0x06;
	public final static byte CONTENTION_FREE_ACK_PLUS_POLL = 0x07;

	public final static byte DATA_QOS_FRAME = 0x08;
	public final static byte DATA_QOS_CONTENTION_FREE_ACK_FRAME = 0x09;
	public final static byte DATA_QOS_CONTENTION_FREE_POLL_FRAME = 0x0A;
	public final static byte DATA_QOS_CONTENTION_FREE_ACK_PLUS_POLL_FRAME = 0x0B;
	public final static byte DATA_QOS_NULL_FRAME = 0x0C;
	public final static byte QOS_CONTENTION_FREE_ACK_FRAME = 0x0D;
	public final static byte QOS_CONTENTION_FREE_POLL_FRAME = 0x0E;
	public final static byte QOS_CONTENTION_FREE_ACK_PLUS_POLL_FRAME = 0x0F;
}
