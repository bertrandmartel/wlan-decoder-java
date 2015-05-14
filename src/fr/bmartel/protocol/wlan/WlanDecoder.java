/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Bertrand Martel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.bmartel.protocol.wlan;

import fr.bmartel.protocol.radiotap.RadioTap;
import fr.bmartel.protocol.radiotap.inter.IRadioTapFrame;
import fr.bmartel.protocol.wlan.frame.IWlanFrame;
import fr.bmartel.protocol.wlan.inter.IWlan802dot11Radiotap;
import fr.bmartel.protocol.wlan.inter.IWlanFrameControl;
import fr.bmartel.utils.RadioTapException;

/**
 * Wlan decoder
 * 
 * @author Bertrand Martel
 * 
 */
public class WlanDecoder implements IWlan802dot11Radiotap {

	public final static boolean DISPLAY_ELEMENT_NOT_DECODED = true;
	
	/**
	 * radio tap object
	 */
	private IRadioTapFrame radioTap = null;

	/**
	 * wlan frame object
	 */
	private WlanFrameDecoder wlan802dot11 = null;

	private byte[] dataFrame = null;

	/**
	 * initialize wlan driver
	 */
	public WlanDecoder(byte[] dataFrame) {
		this.dataFrame = dataFrame;
	}

	public void decodeWithRadiotap() {
		// decode radioTap protocol
		radioTapDecode(dataFrame);

		// decode wlan protocol
		wlan802dot11Decode(dataFrame, getRadioTap().getRadioTapDataLength());
	}

	public void decode() {
		// decode wlan protocol
		wlan802dot11Decode(dataFrame, getRadioTap().getRadioTapDataLength());
	}

	/**
	 * Decode radio tap frames
	 * 
	 * @param frame
	 */
	public void radioTapDecode(byte[] frame) {
		try {
			radioTap = new RadioTap(frame);
		} catch (RadioTapException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Decode wlan 802.11 frames
	 * 
	 * @param frame
	 *            byte aray data frames
	 * @param offset
	 *            offset defined to remove radio tap frames if necessary
	 */
	public void wlan802dot11Decode(byte[] frame, int offset) {
		if ((frame.length - offset) >= 0) {
			byte[] frameWlan = new byte[frame.length - offset];
			System.arraycopy(frame, offset, frameWlan, 0, frame.length - offset);
			wlan802dot11 = new WlanFrameDecoder(frameWlan);
		} else {
			System.err.println("An error occured while decoding wlan frame");
		}
	}

	@Override
	public IRadioTapFrame getRadioTap() {
		return radioTap;
	}

	@Override
	public IWlanFrameControl getFrameControl() {
		return wlan802dot11.getFrameControl();
	}

	@Override
	public IWlanFrame getFrame() {
		return wlan802dot11.getWlanFrame();
	}

}
