import java.util.*;

public class ReleaseSchedule {
	public static void main(String[] args) {

	}
}

class Time {
	public Time(String timestring) {

	}

	public int compareTo(Time other) {
		return 0;
	}
}

class Timeline {
	Time start, end;
	ArrayList<TimeBar> bars;
	public Timeline(Time start, Time end) {
		bars = new ArrayList<TimeBar>();
	}
}

class TimeBar {
	public TimeBar(Time start, Time end) {

	}
}