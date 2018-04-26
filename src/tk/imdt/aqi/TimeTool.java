package tk.imdt.aqi;

import java.time.LocalDateTime;

import tk.imdt.aqi.utils.Logger;

public class TimeTool extends Thread {
	public static LocalDateTime nextTime = LocalDateTime.now();
	protected static long seconds = 60;
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			try {
				checkTime();
				Thread.sleep(60 * 1000L);
			} catch (InterruptedException e) {
				Logger.log("Error when trying to sleep thread TimeTool");
				e.printStackTrace();
			}
		}
	}
	public TimeTool(long seconds) {
		TimeTool.seconds = seconds;
	}
	public TimeTool() {
		this(60);
	}
	public static void checkTime() {
		//Logger.log("nextTime : " + nextTime);
		if (LocalDateTime.now().isAfter(nextTime)) {
			new ScheduledTask().start();
			
			nextTime = (LocalDateTime.of(LocalDateTime.now().getYear(),
					LocalDateTime.now().getMonthValue(),
					LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), 25)).plusSeconds(seconds);
				
			//nextTime = LocalDateTime.now().plusSeconds(seconds);
		}
	}

}
