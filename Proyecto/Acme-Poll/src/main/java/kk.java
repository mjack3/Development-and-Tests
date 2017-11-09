import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kk {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub

		final Pattern pattern = Pattern.compile("^MALE|FEMALE|HOMBRE|MUJER|$");
		final Matcher m = pattern.matcher("");
		System.out.println(m.matches());
	}

}
