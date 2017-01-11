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
package fr.bmartel.wlandecoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import fr.bmartel.pcapdecoder.PcapDecoder;
import fr.bmartel.pcapdecoder.structure.types.inter.IEnhancedPacketBLock;
import fr.bmartel.pcapdecoder.utils.DecoderStatus;
import fr.bmartel.protocol.wlan.WlanDecoder;
import fr.bmartel.protocol.wlan.inter.IWlan802dot11Radiotap;

/**
 * @mainpage  WLAN 802.11 JAVA DECODER
 *
 *Decoder for Wlan 802.11 frame preceding with Radiotap header or not

 Radiotap header are parsed with project http://bertrandmartel.github.io/pcapng-decoder-java/

 All type of Wlan 802.11 frames are parsed :
 * MANAGEMENT_FRAME_TYPE
 * CONTROL_FRAME_TYPE
 * DATA_FRAME_TYPE

 But there are a few of "information element" block parsed for now (tagged parameters) :
 * SSID
 * HT CAPABILITIES
 * SUPPORTED RATE
 * TIM
 * EXTENDED SUPPORTED RATE
 * ERP
 * DSSS PARAMETER
 * 
 */
/**
 * Start WLAN decoder
 * 
 * @author Bertrand Martel
 * 
 */
public class DecodeMain {

	/**
	 * Start WLAN decoder
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		boolean verbose = false;

		if (args.length > 1) {

			byte[] dataFromFile = new byte[] {};

			if (args[0].equals("-f")) {
				dataFromFile = readFile(args[1]);
				if (args.length > 2 && args[2].equals("-v")) {
					verbose = true;
				}
			} else if (args[0].equals("-v")) {
				verbose = true;
				if (args.length > 2) {
					if (args[1].equals("-f"))
						dataFromFile = readFile(args[2]);
					else {
						System.err.println("Insufficient argument");
						return;
					}
				} else {
					System.err.println("Insufficient argument");
					return;
				}
			} else {
				System.err.println("Invalid argument");
				return;
			}
			if (dataFromFile.length > 0) {

				PcapDecoder pcapNgDecoder = new PcapDecoder(dataFromFile);
				int status = pcapNgDecoder.decode();

				if (status == DecoderStatus.SUCCESS_STATUS) {
					ArrayList<IWlan802dot11Radiotap> wlanDecodedFrameList = new ArrayList<IWlan802dot11Radiotap>();

					for (int i = 0; i < pcapNgDecoder.getSectionList().size(); i++) {
						if (pcapNgDecoder.getSectionList().get(i) instanceof IEnhancedPacketBLock) {
							
							IEnhancedPacketBLock packet = (IEnhancedPacketBLock) pcapNgDecoder.getSectionList().get(i);

							WlanDecoder dataDecode = new WlanDecoder(packet.getPacketData());
							dataDecode.decodeWithRadiotap();
							wlanDecodedFrameList.add(dataDecode);
						}
					}
					long endTime   = System.currentTimeMillis();
					long totalTime = endTime - startTime;
					
					System.out.println("decoding time : " + totalTime + " ms");
					
					if (verbose)
						DisplayDecodingInfo.displayAllInfo(wlanDecodedFrameList);
				}
				else
					System.err.println("Decoding error occured");
			}
			else
				System.err.println("File is empty");
		}
		else
			System.err.println("Insufficient argument");
	}

	/**
	 * Read all bytes from file
	 * 
	 * @param path
	 *            file path
	 */
	static byte[] readFile(String path) {
		Path path2 = Paths.get(path);

		byte[] data = new byte[] {};

		try {
			data = Files.readAllBytes(path2);
		} catch (IOException e) {
			System.err.print("Error file path is incorrect");
		}

		return data;
	}

}
