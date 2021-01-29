import java.time.LocalTime;

public class Clock {
    private LocalTime clockIn;
    private LocalTime clockOut;

    public Clock(LocalTime clockIn, LocalTime clockOut)
    {
        LocalTime add12 = LocalTime.of(6, 00);
        if(clockIn.isBefore(add12))
            clockIn = add12ToHour(clockIn);

            if(clockOut.isBefore(add12))
                clockOut = add12ToHour(clockOut);

        this.clockIn = clockIn;
        this.clockOut = clockOut;
    }

    public LocalTime getClockIn() {
        return clockIn;
    }

    public void setClockIn(LocalTime clockIn) {
        this.clockIn = clockIn;
    }

    public LocalTime getClockOut() {
        return clockOut;
    }

    public void setClockOut(LocalTime clockOut) {
        this.clockOut = clockOut;
    }

    public LocalTime add12ToHour(LocalTime time)
    {
        time = time.plusHours(12);
        return time;
    }

    @Override
    public String toString() {
        return "Clock{" +
                "clockIn=" + clockIn +
                ", clockOut=" + clockOut +
                '}';
    }
}
