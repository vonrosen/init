import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String [] args) {

//		Test t = new Test();
//		System.out.println(t.timeInWords(1, 31));

		List<long[]> ls = new ArrayList<long[]>();

		for (int i = 0; i < 10; ++i) {
			long [] a = new long[10_000_000];

			for (int j = 0; j < 10000; ++j) {
				a[j] = 66787;
			}

			ls.add(a);
		}

		System.out.println(ls.size());
	}


	public String timeInWords(int hour, int minute) {
	    if (hour < 1 || hour > 12) {
	        throw new IllegalArgumentException("Hour must be between 0 and 12");
	    }

	    if (minute < 0 || minute > 59) {
	        throw new IllegalArgumentException("Minute must be between 0 and 59");
	    }

	    String [] hours = { "one", "two", "three", "four", "five", "six", "seven", "eigth", "nine", "ten", "eleven",
	                    "twelve" };

	    String [] minutes = {
	    "one",
	    "two",
	    "three",
	    "four",
	    "five",
	    "six",
	    "seven",
	    "eigth",
	    "nine",
	    "ten",
	    "eleven",
	    "twelve",
	    "thirteen",
	    "fourteen",
	    null,
	    "sixteen",
	    "seventeen",
	    "eighteen",
	    "nineteen",
	    "twenty",
	    "twenty one",
	    "twenty two",
	    "twenty three",
	    "twenty four",
	    "twenty five",
	    "twenty six",
	    "twenty seven",
	    "twenty eight",
	    "twenty nine",
	    };

	    Map<Integer, String> hourMap = new HashMap<Integer, String>();
	    Map<Integer, String> minuteMap = new HashMap<Integer, String>();

	    for (int i = 1; i <= 12; ++i) {
	        hourMap.put(i, hours[i - 1]);
	    }

	    for (int i = 0; i < 29; ++i) {
	        minuteMap.put(i + 1, minutes[i]);
	    }

	    if (minute == 0) {
	        return hourMap.get(hour) + " o'clock";
	    }

	    if (minute > 0 && minute < 15) {
	        return minuteMap.get(minute) + " past " + hourMap.get(hour);
	    }

	    if (minute == 15) {
	        return "quarter past " + hourMap.get(hour);
	    }

	    if (minute > 15 && minute < 30) {
	        return minuteMap.get(minute) + " past " + hourMap.get(hour);
	    }

	    if (minute == 30) {
	        return "half past " + hourMap.get(hour);
	    }

	    int nextHour = hour == 12 ? 1 : hour + 1;

	    return minuteMap.get(60 - minute) + " to " + hourMap.get(nextHour);
	}

}
