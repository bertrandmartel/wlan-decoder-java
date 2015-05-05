package fr.bmartel.protocol.wlan;


/**
 * build a MCS object (according to MCS index table)
 * 
 * @author Bertrand Martel
 * 
 */
public class WlanMcs implements IWlanMcs {

	private byte index = 0x00;

	private byte spatialStreams = 0x00;

	private String modulation = "";

	private double dataRateChannel20MHzGi800ns = 0;

	private double dataRateChannel20MHzGi400ns = 0;

	private double dataRateChannel40MHzGi800ns = 0;

	private double dataRateChannel40MHzGi400ns = 0;

	public WlanMcs(byte index, byte spatialStreams, String modulation,
			double dataRateChannel20MHzGi800ns,
			double dataRateChannel20MHzGi400ns,
			double dataRateChannel40MHzGi800ns,
			double dataRateChannel40MHzGi400ns) {
		this.index = index;
		this.spatialStreams = spatialStreams;
		this.modulation = modulation;
		this.dataRateChannel20MHzGi800ns = dataRateChannel20MHzGi800ns;
		this.dataRateChannel20MHzGi400ns = dataRateChannel20MHzGi400ns;
		this.dataRateChannel40MHzGi800ns = dataRateChannel40MHzGi800ns;
		this.dataRateChannel40MHzGi400ns = dataRateChannel40MHzGi400ns;
	}

	@Override
	public byte getMcsIndex() {
		return index;
	}

	@Override
	public byte getSpatialStreams() {
		return spatialStreams;
	}

	@Override
	public String getModulation() {
		return modulation;
	}

	@Override
	public double getdataRate_CHANNEL_20MHZ_GI_800NS() {
		return dataRateChannel20MHzGi800ns;
	}

	@Override
	public double getdataRate_CHANNEL_20MHZ_GI_400NS() {
		return dataRateChannel20MHzGi400ns;
	}

	@Override
	public double getdataRate_CHANNEL_40MHZ_GI_800NS() {
		return dataRateChannel40MHzGi800ns;
	}

	@Override
	public double getdataRate_CHANNEL_40MHZ_GI_400NS() {
		return dataRateChannel40MHzGi400ns;
	}

}
