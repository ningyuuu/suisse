import java.util.*;

public class ReleaseSchedule {
	public static void main(String[] args) {

	}
}

class Time {
	public int year, month, day, hour, minute, second, millisecond;

	public Time(String timestring) {
		parseDate(timestring.split(" ")[0]);
		parseTime(timestring.split(" ")[1]);
	}

	public int compareTo(Time other) {
		if (year != other.year) {
			return year - other.year;
		}

		if (month != other.month) {
			return month - other.month;
		}

		if (day != other.day) {
			return day - other.day;
		}

		if (hour != other.hour) {
			return hour - other.hour;
		}

		if (minute != other.minute) {
			return minute - other.minute;
		}

		if (second != other.second) {
			return second - other.second;
		}

		if (millisecond != other.millisecond) {
			return millisecond - other.millisecond;
		}

		return 0;
	}

	private void parseDate(String date) {
		//28-05-2017
		day = Integer.parseInt(date.substring(0, 2));
		month = Integer.parseInt(date.substring(3, 5));
		year = Integer.parseInt(date.substring(6));
	}

	private void parseTime(String time) {
		// 13:00:00.000+0800
		// 05:15:00.000Z
		String timezone = time.substring(12);
		int timezone_adj = 0;
		if (!timezone.equals("Z")) {
			//+0800
			//-0400
			timezone_adj = Integer.parseInt(timezone.substring(1));
			timezone_adj = timezone.substring(0, 1).equals(-) ? -1 * timezone_adj : timezone_adj;
		}
		hour = Integer.parseInt(timezone.substring(0, 2)) - (timezone_adj / 100);
		minute = Integer.parseInt(timezone.substring(3, 5)) - (timezone_adj % 100);
		second = Integer.parseInt(6, 8);
		millisecond = Integer.parseInt(9, 12);
	}
}

class Timeline {
	Time start, end;
	ArrayList<TimeBar> bars;
	public Timeline(Time start, Time end) {
		bars = new ArrayList<TimeBar>();
	}

	public Integer getTimeGap(Timeline timeline) {
		Time releaseStart, releaseEnd, start, end;
		Integer seconds = 0;
		int count = 1;

		releaseStart = timeline.getStart();
		releaseEnd = timeline.getEnd();
		start = timeline.getBars().get(0).getStart();
		end = timeline.getBars().get(0).getEnd();

		ArrayList<Integer> timeGap = new ArrayList<Integer>();

		if (releaseStart < start) {
			seconds = start.minus(releaseStart);
			timeGap.add(Integer.parseInt(seconds));
		} // else: releaseStart == start of first timeBar

		for (TimeBar timeBar: timeline.getBars()) {
			if (count != 1) {
				seconds = timeBar.getStart().minus(end);
				timeGap.add(Integer.parseInt(seconds));
				end = timeBar.getEnd();
			}
			count += 1;
		}

			return findMaxTimeGap(timeGap);
		}

    public Integer findMaxTimeGap(ArrayList<Integer> timeGap) {
		Integer gap = timeGap.get(0);

			for (int i = 1; i <= timeGap.size(); i++) {
				if (gap < timeGap.get(i)) {
				gap = timeGap.get(i);
			}
		}
		return gap;
    }
}

class TimeBar {
	public TimeBar(Time start, Time end) {

	}
}