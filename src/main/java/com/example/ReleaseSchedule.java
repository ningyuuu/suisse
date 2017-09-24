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
			System.out.println("INSERT: " + input.get(i));
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
	String timestring;
	DateFormat df;
	public Date time;
	public Time(String timestring) {
		this.timestring = timestring;
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
		return (int)(time.getTime() - other.time.getTime())/1000;
	}

	public void print() {
		System.out.println(time.toString());
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
			diffInSeconds = timeBar.start.minus(barEnd);
			System.out.println(diffInSeconds);
			if (diffInSeconds > seconds) {
				seconds = diffInSeconds;
			}
			barEnd = timeBar.end;
		}

		// final check
		diffInSeconds = end.minus(barEnd); 
		if (diffInSeconds > seconds) {
			seconds = diffInSeconds;
		}

		return seconds;
	}

	public void insert(Time newstart, Time newend) {
		if (start.minus(newstart) > 0) {
			newstart = start;
		}

		if (newend.minus(end) > 0) {
			newend = end;
		}
		// start.print();
		// newstart.print();
		// System.out.println(newstart.minus(start));
		// newend.print();
		// end.print();
		// System.out.println(newend.minus(end));
		ArrayList<TimeBar> betweens = new ArrayList<TimeBar>();
		// System.out.println("NUM BARS CURRENT: " + bars.size());
		for (TimeBar bar: bars) {
			// System.out.println("BAR START: " + bar.start + " BAR END: " + bar.end);
			if (bar.start.minus(newend) > 0) {
				System.out.println("BREAK FOR: " + bar.start.minus(newend));
				break;
			}

			if (newstart.minus(bar.start) > 0 && bar.end.minus(newstart) >= 0) {
				System.out.println("NEW START");
				newstart = bar.start;
				betweens.add(bar);
				continue;
			}

			if (bar.start.minus(newstart) >= 0 && newend.minus(bar.end) >= 0) {
 				System.out.println("NEW BETWEEN");
				betweens.add(bar);
				continue;
			}

			if (newend.minus(bar.start) >= 0 && bar.end.minus(newend) > 0) {
 				System.out.println("NEW END");
				newend = bar.end;
				betweens.add(bar);
			}
 		}
 		for (TimeBar bar:betweens) {
 			System.out.println("DELETE A BAR");
 			bars.remove(bar);
 		}
 		bars.add(new TimeBar(newstart, newend));
 		Collections.sort(bars);
 		// System.out.println("SIZE:" + bars.size());
 		// bars.get(0).start.print();
 		// bars.get(0).end.print();
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