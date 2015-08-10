# Wlan 802.11 Decoder in Java #

http://akinaru.github.io/wlan-decoder-java/

<i>Last update 04/05/2015</i>

Decoder for Wlan 802.11 frame preceding with Radiotap header or not

Radiotap header are parsed with project http://akinaru.github.io/pcapng-decoder-java/

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

It lacks a lot of informations element that will be implemented later 

<hr/>

<b>USAGE</b>

The following usage show how to decode a Wlan 802.11 frame preceding with Radiotap frames.

You can also decode only wlan802.11 frames if you dont have radiotap header before

First you need a pcapng file containing :
 * your wlan 802.11 frames preceding with radiotap frames
 * Or your wlan 802.11 frames only
 
You can use to parse this file 
* the jnetpcap library 
* my own lib in http://akinaru.github.io/pcapng-decoder-java/ (pcapng-decoder-java)

You dont have to take the jar to you it is already in lib folder

So basically this project use 2 external libs pcapngdecoder.jar and radiotapdecoder.jar

<hr/>

<b>COMMAND LINE SYNTAX</b> 

``java -cp wlandecoder-1.0.jar fr.bmartel.wlandecoder.DecodeMain -f ../pcapfile/exemple.pcapng  -v``

-f <file.pcapng> : input file

-v               : verbose, will show all section parsing content

This exemple is launched from release folder

<hr/>

<b>PROGRAM SYNTAX for parsing Wlan802.11 frames preceding with radiotap header</b>

``byte[] wlanDataPrecedingWithRadiotapHeader = packet.getPacketData();``

``WlanDecoder dataDecode = new WlanDecoder(packet.getPacketData());``

``dataDecode.decodeWithRadiotap();``

``wlanDecodedFrameList.add(dataDecode);``

Check ``fr.bmartel.wlandecoder.DisplayDecodingInfo.java`` to see how to retrieve data programmatically.

You can retrieve radiotap data too if you want. Go check http://akinaru.github.io/radiotap-decoder-java/ for more precision

<b>PROGRAM SYNTAX for parsing Wlan802.11 frames ONLY</b>

``byte[] wlanDataPrecedingWithRadiotapHeader = packet.getPacketData();``

``WlanDecoder dataDecode = new WlanDecoder(packet.getPacketData());``

``dataDecode.decode();``

``wlanDecodedFrameList.add(dataDecode);``

# Output example


Wlan frame control<br/>
-------protocolVersion   : 0<br/>
-------type              : 0<br/>
-------subType           : 8<br/>
-------toDS              : false<br/>
-------fromDS            : false<br/>
-------moreFragmentation : false<br/>
-------retry             : false<br/>
-------powerManagement   : false<br/>
-------moreData          : false<br/>
-------wep               : false<br/>
-------order             : false<br/>
Wlan management frame<br/>
-------duration id     : 00 | 00<br/>
-------destinationAddr : FF | FF | FF | FF | FF | FF<br/>
-------sourceAddr      : 00 | 24 | D4 | 6B | 0C | 5D<br/>
-------bssid           : 00 | 24 | D4 | 6B | 0C | 5D<br/>
-------sequenceControl : 00 | E5<br/>
-------frameBody       : 60 | 01 | 25 | DE | 32 | 03 | 00 | 00 | 60 | 00 | 01 | 04 | 00 | 08 | 46 | 72 | 65 | 65 | 57 | 69 | 66 | 69 | 01 | 08 | 82 | 84 | 8B | 96 | 2C | 0C | 12 | 18 | 03 | 01 | 0C | 05 | 04 | 00 | 02 | 00 | 00 | 2A | 01 | 04 | 32 | 05 | 24 | 30 | 48 | 60 | 6C | 2D | 1A | 6C | 00 | 03 | FF | FF | FF | 00 | 01 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 01 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 3D | 16 | 0C | 00 | 13 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 7F | 08 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 40 | DD | 18 | 00 | 50 | F2 | 02 | 01 | 01 | 00 | 00 | 03 | A4 | 00 | 00 | 27 | A4 | 00 | 00 | 42 | 43 | 5E | 00 | 62 | 32 | 2F | 00<br/>
-------fcs             : A3 | 26 | 13 | 07<br/>
-------MANAGEMENT BEACON FRAME<br/>
--------------timestamp              : 00 | 00 | 03 | 32 | DE | 25 | 01 | 60<br/>
--------------beaconInterval         : 96<br/>
--------------capability information : 04 | 01<br/>
---------------------Dynamic Elements<br/>
---------------------element id : 0<br/>
----------------------------SSID : FreeWifi<br/>
---------------------Dynamic Elements<br/>
---------------------element id : 1<br/>
----------------------------data rates supported :<br/> 1000kbps,2000kbps,5500kbps,11000kbps,22000kbps,6000kbps,9000kbps,12000kbps,<br/>
---------------------Dynamic Elements<br/>
---------------------element id : 3<br/>
----------------------------currentChannel : 12<br/>
----------------------------frequency      : -1<br/>
---------------------Dynamic Elements<br/>
---------------------element id : 5<br/>
----------------------------number of beacon frame before next DTIM  : 0<br/>
----------------------------number of beacon interval between 2 DTIM : 2<br/>
----------------------------TIM bitmapControl                        : 0<br/>
----------------------------TIM partialVirtualBitmap : 00 | <br/>
---------------------Dynamic Elements<br/>
---------------------element id : 42<br/>
----------------------------isErpPresent         : false<br/>
----------------------------useProtection        : false<br/>
----------------------------isBarkerPreambleMode : true<br/>
---------------------Dynamic Elements<br/>
---------------------element id : 50<br/>
----------------------------extended data rates supported : 18000kbps,24000kbps,36000kbps,48000kbps,54000kbps,<br/>
---------------------Dynamic Elements<br/>
---------------------element id : 45<br/>
----------------------------support short 20Mhz           : true<br/>
----------------------------support short 40Mhz           : true<br/>
----------------------------channel width 20MHz supported : false<br/>
----------------------------channel width 40MHz supported : true<br/>
----------------------------AMPDU paramters               : 3<br/>
----------------------------MCS Supported : n°0-BPSK<br/>
----------------------------MCS Supported : n°1-QPSK<br/>
----------------------------MCS Supported : n°2-QPSK<br/>
----------------------------MCS Supported : n°3-16-QAM<br/>
----------------------------MCS Supported : n°4-16-QAM<br/>
----------------------------MCS Supported : n°5-64-QAM<br/>
----------------------------MCS Supported : n°6-64-QAM<br/>
----------------------------MCS Supported : n°7-64-QAM<br/>
----------------------------MCS Supported : n°8-BPSK<br/>
----------------------------MCS Supported : n°9-QPSK<br/>
----------------------------MCS Supported : n°10-QPSK<br/>
----------------------------MCS Supported : n°11-16-QAM<br/>
----------------------------MCS Supported : n°12-16-QAM<br/>
----------------------------MCS Supported : n°13-64-QAM<br/>
----------------------------MCS Supported : n°14-64-QAM<br/>
----------------------------MCS Supported : n°15-64-QAM<br/>
----------------------------MCS Supported : n°16-BPSK<br/>
----------------------------MCS Supported : n°17-QPSK<br/>
----------------------------MCS Supported : n°18-QPSK<br/>
----------------------------MCS Supported : n°19-16-QAM<br/>
----------------------------MCS Supported : n°20-16-QAM<br/>
----------------------------MCS Supported : n°21-64-QAM<br/>
----------------------------MCS Supported : n°22-64-QAM<br/>
----------------------------MCS Supported : n°23-64-QAM<br/>
----------------------------HT Extended capabilities           : 00 | 00<br/>
----------------------------Transmit beam forming capabilities : 00 | 00 | 00 | 00<br/>
----------------------------ASEL capabilities<br/>


Element id not parsed will be displayed at the beginning of the output like this :
* Element id not decoded => 61

<hr/>

* Project is JRE 1.7 compliant
* You can build it with ant => build.xml
* Development on Eclipse 
* Specification from https://standards.ieee.org/getieee802/download/802.11-2012.pdf
