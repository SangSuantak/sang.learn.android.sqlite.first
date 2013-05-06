package sang.learn.android.sqlite.first;

public class Common {

	public static boolean isInteger(String s){
		try {
			Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
}
