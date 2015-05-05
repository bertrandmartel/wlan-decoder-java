package fr.bmartel.protocol.wlan;

/**
 * Define MCS object in MCS index table
 * 
 * @author Bertrand Martel
 * 
 */
public interface IWlanMcs {

	/**
	 * retrieve MCS index in mcs table
	 * 
	 * @return
	 */
	public byte getMcsIndex();

	/**
	 * retrieve spatial streams number
	 * 
	 * @return
	 */
	public byte getSpatialStreams();

	/**
	 * retrieve modulation type
	 * 
	 * @return
	 */
	public String getModulation();

	/**
	 * retrieve data rate for Channel 20 MHz & 800ns guard interval
	 * 
	 * @return
	 */
	public double getdataRate_CHANNEL_20MHZ_GI_800NS();

	/**
	 * retrieve data rate for Channel 20 MHz & 400ns guard interval
	 * 
	 * @return
	 */
	public double getdataRate_CHANNEL_20MHZ_GI_400NS();

	/**
	 * retrieve data rate for Channel 40 MHz & 800ns guard interval
	 * 
	 * @return
	 */
	public double getdataRate_CHANNEL_40MHZ_GI_800NS();

	/**
	 * retrieve data rate for Channel 40 MHz & 400ns guard interval
	 * 
	 * @return
	 */
	public double getdataRate_CHANNEL_40MHZ_GI_400NS();

}
