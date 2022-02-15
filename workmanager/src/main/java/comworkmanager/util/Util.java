package comworkmanager.util;

public class Util {

	public static String capitalizeString(String stringa) {
		if(stringa.length()<=0) {
			return "";
		}
		String str = stringa.toLowerCase();
		String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
		return cap;
	}
	
}
