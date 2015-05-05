package fr.bmartel.protocol.wlan.frame.management.element.impl;

import fr.bmartel.protocol.wlan.frame.management.element.WlanElementAbstr;
import fr.bmartel.protocol.wlan.frame.management.element.inter.IErpElement;

/**
 * The ERP element contains information on the presence of Clause 16 or Clause 17 STAs in the BSS that are
   not capable of Clause 19 (ERP-OFDM) data rates
	<ul>
		<li>element id : 1 byte</li>
		<li>length : 1 byte</li>
		<li>erp parameters : 1 byte</li>
	</ul>

	For ERP parameters : 
	<ul>
		<li>B0 => NonERP_Present</li>
		<li>B1 => Use_Protection</li>
		<li>B2 => Barker Preamble Mode</li>
	</ul>
 * 
 * @author Bertrand Martel
 *
 */
public class ErpElement extends WlanElementAbstr implements IErpElement{

	public final static int id = 42;
	
	private boolean erpPresent = false;
	
	private boolean protection = false;
	
	private boolean barkerPreambleMode = false;
	
	/**
	 * @param data
	 */
	public ErpElement(byte[] data) {
		super(data);
		if ((data[0] & 0b00000001) ==0b00000001)
			erpPresent=true;
		if ((data[0] & 0b00000010) ==0b00000010)
			protection=true;
		if ((data[0] & 0b00000100) ==0b00000100)
			barkerPreambleMode=true;
	}
	
	@Override
	public boolean isErpPresent()
	{
		return erpPresent;		
	}
	
	@Override
	public boolean useProtection()
	{
		return protection;
	}
	
	@Override
	public boolean isBarkerPreambleMode()
	{
		return barkerPreambleMode;
	}

	@Override
	public byte getElementId() {
		return id;
	}

}
