package com.example;

import java.util.*;
import java.text.*;

public class ReleaseSchedule {
	Timeline tl;
	
	public ReleaseSchedule(ArrayList<String> input) {
		Time scheduleStart, scheduleEnd;
		int count;
		count = Integer.parseInt(input.get(0).split(";")[0]);
		// try {
		// 	System.out.println(input.get(0).split(";")[1]);
		// 	Date time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSZ").parse(input.get(0).split(";")[1]);
		// } catch (Exception e) {
		// 	System.out.println("ERROR IN PARSE");
		// }
		scheduleStart = new Time(input.get(0).split(";")[1]);
		scheduleEnd = new Time(input.get(0).split(";")[2]);

		tl = new Timeline(scheduleStart, scheduleEnd);
		// System.out.println(input.get(0));

		for (int i=1; i<input.size(); i++) {
			String[] splits = input.get(i).split(";");
			tl.insert(new Time(splits[1]), new Time(splits[2]));
		}
	}

	public static void main(String[] args) {
		ArrayList<String> inputs = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			inputs.add(sc.nextLine());
		}

		System.out.println(new ReleaseSchedule(inputs).getTimeGap());
	}

	public long getTimeGap() {
		return tl.getTimeGap();
	}
}

class Time implements Comparable<Time> {
	// 13:00:00.000+0800
	// 05:15:00.000Z
	DateFormat df;
	public Date time;
	public Time(String timestring) {
		df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSX");
		try {
			time = df.parse(timestring);
		} catch (Exception e) {
			System.out.println("ERROR IN PARSE: " + timestring);
		}
	}

	public int compareTo(Time other) {
		return time.compareTo(other.time);
	}

	public long minus(Time other) {
		return (int)(time.getTime() - other.time.getTime())/-1000;
	}
}

class Timeline {
	public Time start, end;
	public ArrayList<TimeBar> bars;
	public Timeline(Time start, Time end) {
		bars = new ArrayList<TimeBar>();
		this.start = start;
		this.end = end;
	}

	public long getTimeGap() {
		Time barEnd = start;
		long diffInSeconds, seconds = 0;

		for (TimeBar timeBar: bars) {
			diffInSeconds = barEnd.minus(timeBar.start);
			System.out.println(diffInSeconds);
			if (diffInSeconds > seconds) {
				seconds = diffInSeconds;
			}
			barEnd = timeBar.end;
		}

		// final check
		diffInSeconds = barEnd.minus(end); 
		if (diffInSeconds > seconds) {
			seconds = diffInSeconds;
		}

		return seconds;
	}

	public void insert(Time start, Time end) {
		ArrayList<TimeBar> betweens = new ArrayList<TimeBar>();
		for (TimeBar bar: bars) {
			if (bar.start.minus(end) > 0) {
				break;
			}

			if (start.minus(bar.start) > 0 && bar.end.minus(start) >= 0) {
				start = bar.start;
				betweens.add(bar);
				continue;
			}

			if (bar.start.minus(start) >= 0 && end.minus(bar.end) >= 0) {
				betweens.add(bar);
				continue;
			}

			if (end.minus(bar.start) >= 0 && bar.end.minus(end) > 0) {
				end = bar.end;
				betweens.add(bar);
			}
 		}
 		for (TimeBar bar:betweens) {
 			bars.remove(bar);
 		}
 		bars.add(new TimeBar(start, end));
 		Collections.sort(bars);
	}
}

class TimeBar implements Comparable<TimeBar> {
	public Time start, end;
	public TimeBar(Time start, Time end) {
		this.start = start;
		this.end = end;
	}

	public int compareTo(TimeBar other) {
		return start.compareTo(other.start);
	}
}