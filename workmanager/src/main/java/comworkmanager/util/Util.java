package comworkmanager.util;

public class Util {

	public static String capitalizeString(String str) {
		String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
		return cap;
	}
	
}
