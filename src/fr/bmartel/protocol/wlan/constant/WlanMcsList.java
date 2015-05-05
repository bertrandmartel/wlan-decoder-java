package fr.bmartel.protocol.wlan.constant;

import java.util.ArrayList;
import java.util.List;

import fr.bmartel.protocol.wlan.WlanMcs;

/**
 * Define constant list of MCS index table according to guard interval, coding
 * scheme (index)
 * 
 * @author Bertrand Martel
 * 
 */
public class WlanMcsList {

	public final static int SPATIAL_STREAM_NUM = 4;

	/**
	 * define list of MCS index with coding scheme and modulation
	 */
	public static List<WlanMcs> mcsList = new ArrayList<WlanMcs>();

	static {
		mcsList.add(new WlanMcs((byte) 0, (byte) 1, "BPSK", 6.5, 7.2, 13.5,
				15.0));
		mcsList.add(new WlanMcs((byte) 1, (byte) 1, "QPSK", 13.0, 14.4, 27.0,
				30.0));
		mcsList.add(new WlanMcs((byte) 2, (byte) 1, "QPSK", 19.5, 21.7, 40.5,
				45.0));
		mcsList.add(new WlanMcs((byte) 3, (byte) 1, "16-QAM", 26.0, 28.9, 54.0,
				60.0));
		mcsList.add(new WlanMcs((byte) 4, (byte) 1, "16-QAM", 39.0, 43.3, 81.0,
				90.0));
		mcsList.add(new WlanMcs((byte) 5, (byte) 1, "64-QAM", 52.0, 57.8,
				108.0, 120.0));
		mcsList.add(new WlanMcs((byte) 6, (byte) 1, "64-QAM", 58.5, 65.0,
				121.5, 135.0));
		mcsList.add(new WlanMcs((byte) 7, (byte) 1, "64-QAM", 65.0, 72.2,
				135.0, 150.0));
		mcsList.add(new WlanMcs((byte) 8, (byte) 2, "BPSK", 13.0, 14.4, 27.0,
				30.0));
		mcsList.add(new WlanMcs((byte) 9, (byte) 2, "QPSK", 26.0, 28.9, 54.0,
				60.0));
		mcsList.add(new WlanMcs((byte) 10, (byte) 2, "QPSK", 39.0, 43.3, 81.0,
				90.0));
		mcsList.add(new WlanMcs((byte) 11, (byte) 2, "16-QAM", 52.0, 57.8,
				108.0, 120.0));
		mcsList.add(new WlanMcs((byte) 12, (byte) 2, "16-QAM", 78.0, 86.7,
				162.0, 180.0));
		mcsList.add(new WlanMcs((byte) 13, (byte) 2, "64-QAM", 104.0, 115.6,
				216.0, 240.0));
		mcsList.add(new WlanMcs((byte) 14, (byte) 2, "64-QAM", 117.0, 130.0,
				243.0, 270.0));
		mcsList.add(new WlanMcs((byte) 15, (byte) 2, "64-QAM", 130.0, 144.4,
				270.0, 300.0));
		mcsList.add(new WlanMcs((byte) 16, (byte) 3, "BPSK", 19.5, 21.7, 40.5,
				45.0));
		mcsList.add(new WlanMcs((byte) 17, (byte) 3, "QPSK", 39.0, 43.3, 81.0,
				90.0));
		mcsList.add(new WlanMcs((byte) 18, (byte) 3, "QPSK", 58.5, 65.0, 121.5,
				135.0));
		mcsList.add(new WlanMcs((byte) 19, (byte) 3, "16-QAM", 78.0, 86.7,
				162.0, 180.0));
		mcsList.add(new WlanMcs((byte) 20, (byte) 3, "16-QAM", 117.0, 130.0,
				243.0, 270.0));
		mcsList.add(new WlanMcs((byte) 21, (byte) 3, "64-QAM", 156.0, 173.3,
				324.0, 360.0));
		mcsList.add(new WlanMcs((byte) 22, (byte) 3, "64-QAM", 175.5, 195.0,
				364.5, 405.0));
		mcsList.add(new WlanMcs((byte) 23, (byte) 3, "64-QAM", 195.0, 216.7,
				405.0, 450.0));
		mcsList.add(new WlanMcs((byte) 24, (byte) 4, "BPSK", 26.0, 28.8, 54.0,
				60.0));
		mcsList.add(new WlanMcs((byte) 25, (byte) 4, "QPSK", 52.0, 57.6, 108.0,
				120.0));
		mcsList.add(new WlanMcs((byte) 26, (byte) 4, "QPSK", 78.0, 86.8, 162.0,
				180.0));
		mcsList.add(new WlanMcs((byte) 27, (byte) 4, "16-QAM", 104.0, 115.6,
				216.0, 240.0));
		mcsList.add(new WlanMcs((byte) 28, (byte) 4, "16-QAM", 156.0, 173.2,
				324.0, 360.0));
		mcsList.add(new WlanMcs((byte) 29, (byte) 4, "64-QAM", 208.0, 231.2,
				432.0, 480.0));
		mcsList.add(new WlanMcs((byte) 30, (byte) 4, "64-QAM", 234.0, 260.0,
				486.0, 540.0));
		mcsList.add(new WlanMcs((byte) 31, (byte) 4, "64-QAM", 260.0, 288.8,
				540.0, 600.0));
	}
}
