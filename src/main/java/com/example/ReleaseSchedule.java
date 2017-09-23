import java.util.*;

public class ReleaseSchedule {
	private ArrayList<String> schedule;
    private String result;

    public ReleaseSchedule(ReleaseScheduleWrapper input) {
        schedule = new ArrayList<Time>();
        
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

class Time {
	String task, start, end;

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