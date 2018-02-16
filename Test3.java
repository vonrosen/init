
public class Test3 {

	public static void main(String [] args) {
		String wordsString = "What is happening?";
		char [] words = wordsString.toCharArray();

		int end = words.length - 1;
		for (int i = 0; i < words.length; i++) {
			if (i > end) {
				break;
			}

			char tmp = words[end];

			words[end] = words[i];
			words[i] = tmp;


			--end;
		}

		int subLength = 0;
		for (int i = 0; i < words.length; ++i) {
			if (words[i] != ' ') ++subLength;

			if (words[i] == ' ' || i == words.length - 1) {
				int start = i == words.length - 1 ? words.length - subLength : i - subLength;
				end = subLength + start - 1;
				for (int j = start; j < subLength + start; ++j) {
					if (j > end) {
						break;
					}

					char tmp = words[end];

					words[end] = words[j];
					words[j] = tmp;

					--end;
				}
				subLength = 0;
			}
		}

		System.out.println(new String(words));
	}

}
