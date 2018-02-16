import java.util.StringTokenizer;

public class Test2 {

 	static byte [] b = new byte[1000];

	public static void main(String [] args) {
		String s = "Help me   understand   what to do ";

		System.out.println(removeWS(s));

	}

	public static String removeWS(String s) {
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s);

		while (st.hasMoreTokens()) {
			sb.append(st.nextToken());
			sb.append(" ");
		}

		return sb.toString().trim();
	}

}
