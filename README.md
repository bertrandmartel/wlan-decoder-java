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

You can use to parse this file 
* the jnetpcap library 
* my own lib in http://akinaru.github.io/pcapng-decoder-java/ (pcapng-decoder-java)

You dont have to take the jar to you it is already in lib folder

So basically this project use 2 external libs pcapngdecoder.jar and radiotapdecoder.jar

<hr/>

<b>COMMAND LINE SYNTAX</b> 

``java -cp ../libs/pcapngdecoder-1.0.jar:../libs/radiotapdecoder-1.0.jar:wlandecoder-1.0.jar fr.bmartel.wlandecoder.DecodeMain -f ../pcapfile/exemple.pcapng  -v``

-f <file.pcapng> : input file

-v               : verbose, will show all section parsing content

This exemple is launched from release folder

<hr/>

<b>PROGRAM SYNTAX for parsing Wlan802.11 frames preceding with radiotap header</b>

``//data parsed from pcapng file``
``byte[] wlanDataPrecedingWithRadiotapHeader = packet.getPacketData();``

``//initiate decoding``
``WlanDecoder dataDecode = new WlanDecoder(packet.getPacketData());``
``dataDecode.decodeWithRadiotap();``
``wlanDecodedFrameList.add(dataDecode);``

You can retrieve radiotap data too if you want. Go check http://akinaru.github.io/radiotap-decoder-java/ for more precision

<b>PROGRAM SYNTAX for parsing Wlan802.11 frames ONLY</b>

``//data parsed from pcapng file``
``byte[] wlanDataPrecedingWithRadiotapHeader = packet.getPacketData();``

``//initiate decoding``
``WlanDecoder dataDecode = new WlanDecoder(packet.getPacketData());``
``dataDecode.decode();``
``wlanDecodedFrameList.add(dataDecode);``

# Output example


Wlan frame control
-------protocolVersion   : 0
-------type              : 0
-------subType           : 8
-------toDS              : false
-------fromDS            : false
-------moreFragmentation : false
-------retry             : false
-------powerManagement   : false
-------moreData          : false
-------wep               : false
-------order             : false
Wlan management frame
-------duration id     : 00 | 00
-------destinationAddr : FF | FF | FF | FF | FF | FF
-------sourceAddr      : 00 | 24 | D4 | 6B | 0C | 5D
-------bssid           : 00 | 24 | D4 | 6B | 0C | 5D
-------sequenceControl : 00 | E5
-------frameBody       : 60 | 01 | 25 | DE | 32 | 03 | 00 | 00 | 60 | 00 | 01 | 04 | 00 | 08 | 46 | 72 | 65 | 65 | 57 | 69 | 66 | 69 | 01 | 08 | 82 | 84 | 8B | 96 | 2C | 0C | 12 | 18 | 03 | 01 | 0C | 05 | 04 | 00 | 02 | 00 | 00 | 2A | 01 | 04 | 32 | 05 | 24 | 30 | 48 | 60 | 6C | 2D | 1A | 6C | 00 | 03 | FF | FF | FF | 00 | 01 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 01 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 3D | 16 | 0C | 00 | 13 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 7F | 08 | 00 | 00 | 00 | 00 | 00 | 00 | 00 | 40 | DD | 18 | 00 | 50 | F2 | 02 | 01 | 01 | 00 | 00 | 03 | A4 | 00 | 00 | 27 | A4 | 00 | 00 | 42 | 43 | 5E | 00 | 62 | 32 | 2F | 00
-------fcs             : A3 | 26 | 13 | 07
-------MANAGEMENT BEACON FRAME
--------------timestamp              : 00 | 00 | 03 | 32 | DE | 25 | 01 | 60
--------------beaconInterval         : 96
--------------capability information : 04 | 01
---------------------Dynamic Elements
---------------------element id : 0
----------------------------SSID : FreeWifi
---------------------Dynamic Elements
---------------------element id : 1
----------------------------data rates supported : 1000kbps,2000kbps,5500kbps,11000kbps,22000kbps,6000kbps,9000kbps,12000kbps,
---------------------Dynamic Elements
---------------------element id : 3
----------------------------currentChannel : 12
----------------------------frequency      : -1
---------------------Dynamic Elements
---------------------element id : 5
----------------------------number of beacon frame before next DTIM  : 0
----------------------------number of beacon interval between 2 DTIM : 2
----------------------------TIM bitmapControl                        : 0
----------------------------TIM partialVirtualBitmap : 00 | 
---------------------Dynamic Elements
---------------------element id : 42
----------------------------isErpPresent         : false
----------------------------useProtection        : false
----------------------------isBarkerPreambleMode : true
---------------------Dynamic Elements
---------------------element id : 50
----------------------------extended data rates supported : 18000kbps,24000kbps,36000kbps,48000kbps,54000kbps,
---------------------Dynamic Elements
---------------------element id : 45
----------------------------support short 20Mhz           : true
----------------------------support short 40Mhz           : true
----------------------------channel width 20MHz supported : false
----------------------------channel width 40MHz supported : true
----------------------------AMPDU paramters               : 3
----------------------------MCS Supported : n°0-BPSK
----------------------------MCS Supported : n°1-QPSK
----------------------------MCS Supported : n°2-QPSK
----------------------------MCS Supported : n°3-16-QAM
----------------------------MCS Supported : n°4-16-QAM
----------------------------MCS Supported : n°5-64-QAM
----------------------------MCS Supported : n°6-64-QAM
----------------------------MCS Supported : n°7-64-QAM
----------------------------MCS Supported : n°8-BPSK
----------------------------MCS Supported : n°9-QPSK
----------------------------MCS Supported : n°10-QPSK
----------------------------MCS Supported : n°11-16-QAM
----------------------------MCS Supported : n°12-16-QAM
----------------------------MCS Supported : n°13-64-QAM
----------------------------MCS Supported : n°14-64-QAM
----------------------------MCS Supported : n°15-64-QAM
----------------------------MCS Supported : n°16-BPSK
----------------------------MCS Supported : n°17-QPSK
----------------------------MCS Supported : n°18-QPSK
----------------------------MCS Supported : n°19-16-QAM
----------------------------MCS Supported : n°20-16-QAM
----------------------------MCS Supported : n°21-64-QAM
----------------------------MCS Supported : n°22-64-QAM
----------------------------MCS Supported : n°23-64-QAM
----------------------------HT Extended capabilities           : 00 | 00
----------------------------Transmit beam forming capabilities : 00 | 00 | 00 | 00
----------------------------ASEL capabilities


Element id not parsed will be displayed at the beginning of the output like this :
* Element id not decoded => 61

<hr/>

* Project is JRE 1.7 compliant
* You can build it with ant => build.xml
* Development on Eclipse 
* Specification from http://www.radiotap.org
