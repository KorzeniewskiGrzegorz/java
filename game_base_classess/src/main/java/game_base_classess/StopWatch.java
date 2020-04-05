package game_base_classess;

import java.time.Duration;
import java.time.Instant;

public class StopWatch {

    Instant startTime, endTime;
    Duration duration;
    boolean isRunning = false;
    
    public StopWatch() {
    	duration = Duration.ZERO; 
    }
    
    public void start() {
        if (!isRunning) {
            this.isRunning = true;
            startTime = Instant.now();
        }
    }

    public void stop() {
        this.endTime = Instant.now();
        if (!isRunning) {
            throw new RuntimeException("Stopwatch has not been started yet");
        }
        isRunning = false;
        Duration result = Duration.between(startTime, endTime);
        if (this.duration == null) {
            this.duration = result;
        } else {
            this.duration = duration.plus(result);
        }
    }

    public long getElapsedTime() {
    	if (isRunning) {
    		Duration sum = duration.plus(Duration.between(startTime, Instant.now()));
    		return sum.getSeconds();
    	}else {
            return this.duration.getSeconds();
    	}
    }

    public void reset() {
        if (this.isRunning) {
            this.stop();
        }
        this.duration = null;
    }
    
	public static void main(String[] args) {
		StopWatch timer = new StopWatch();
		timer.start();
		while(true) {
			System.out.println(String.valueOf(timer.getElapsedTime()));
		}
	}
}