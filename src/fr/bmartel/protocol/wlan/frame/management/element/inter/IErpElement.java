package fr.bmartel.protocol.wlan.frame.management.element.inter;

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
public interface IErpElement {

	public boolean isErpPresent();
	
	public boolean useProtection();
	
	public boolean isBarkerPreambleMode();
	
}
