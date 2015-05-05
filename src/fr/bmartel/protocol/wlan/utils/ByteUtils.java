package fr.bmartel.protocol.wlan.utils;

/**
 * This class aims at realizing all kind of conversions involving Hexa/int/bytes
 * 
 * @author Bertrand Martel
 */
public class ByteUtils {

	/**
	 * Build a byte array to string with a prefix message
	 * 
	 * @param message
	 *            message to print before byte array
	 * @param array
	 *            byte array to print
	 * @param separator
	 *            separator between byte values
	 * @return string message
	 */
	public static String byteArrayToStringMessage(String message, byte[] array,
			char separator) {
		String log = "";
		if (!message.equals(""))
			log = message + " : ";
		if (array != null) {
			for (int count = 0; count < array.length; count++) {
				if (count == 0) {
					log += (ByteUtils.convertFromIntToHexa(array[count])
							+ " " + separator + " ");
				} else if (count != array.length - 1) {
					log += (ByteUtils.convertFromIntToHexa(array[count])
							+ " " + separator + " ");
				} else {
					log += (ByteUtils.convertFromIntToHexa(array[count]));
				}
			}
		}

		return log;
	}

	/**
	 * Convert from int data into String hexadecimal (ex 255 => "0xFF")
	 * 
	 * @param data
	 *            data to convert into hexa
	 * @return
	 * 
	 *         data converted into hexa
	 */
	public static String convertFromIntToHexa(byte data) {
		int dataTmp = data & 0xFF;
		/* Put character in uppercase */
		String value = Integer.toHexString(dataTmp).toUpperCase();
		/* Add 0 if length equal to 1 */
		if (value.length() == 1) {
			value = "0" + value;
		}
		return value;
	}

	/**
	 * 
	 * Convert a byte array to integer
	 * 
	 * @param array
	 *            byte array
	 * @return integer value
	 */
	public static int convertByteArrayToInt(byte[] array) {
		int ret = 0;
		for (int i = 0; i < array.length; i++) {
			ret += (array[i] & 0xFF) << ((array.length - 1 - i) * 8);
		}
		return ret;
	}
}
