import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Babysitter {
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private double[] rate;
    private int daysWorked;
    private List<Clock> hours;
    private double earning;

    public Babysitter(String employeeNumber, String firstName, String lastName, double[] rate, int daysWorked, List<Clock> hours) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rate = rate;
        this.daysWorked = daysWorked;
        this.hours = hours;

        computeFees();
    }

    public Babysitter() {
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double[] getRate() {
        return rate;
    }

    public void setRate(double[] rate) {
        this.rate = rate;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<Clock> getHours() {
        return hours;
    }

    public void setHours(List<Clock> hours) {
        this.hours = hours;
    }

    public double getEarning() {
        return earning;
    }

    public void computeFees() {
        LocalTime startHour, endHour;
        double[] rates = this.rate;
        double shift;
        double fees = 0.0;


        for (int i = 0; i < hours.size(); i++) {
            startHour = hours.get(i).getClockIn();
            endHour = hours.get(i).getClockOut();

            if (startHour.isAfter(LocalTime.of(5, 59)) && endHour.isBefore(LocalTime.of(9, 01))) {
                shift = startHour.until(endHour, MINUTES);
                this.earning += (shift / 60) * rate[0];

            } else if (startHour.isAfter(LocalTime.of(5, 59)) && endHour.isBefore(LocalTime.of(12, 01))) {
                shift = startHour.until(LocalTime.of(9, 00), MINUTES);
                this.earning += (shift / 60) * rate[0];

                shift = LocalTime.of(9, 00).until(endHour, MINUTES);
                this.earning += (shift / 60) * rate[1];
            } else if (startHour.isAfter(LocalTime.of(5, 59)) && endHour.isBefore(LocalTime.of(18, 01))) {
                shift = startHour.until(LocalTime.of(9, 00), MINUTES);
                this.earning += (shift / 60) * rate[0];

                shift = LocalTime.of(9, 00).until(LocalTime.of(12, 00), MINUTES);
                this.earning += (shift / 60) * rate[1];

                shift = LocalTime.of(12, 00).until(endHour, MINUTES);
                this.earning += (shift / 60) * rate[2];
            } else if (startHour.isAfter(LocalTime.of(8, 59)) && endHour.isBefore(LocalTime.of(12, 01))) {
                shift = startHour.until(endHour, MINUTES);
                this.earning += (shift / 60) * rate[1];
            } else if (startHour.isAfter(LocalTime.of(8, 59)) && endHour.isBefore(LocalTime.of(18, 01))) {
                shift = startHour.until(LocalTime.of(12, 00), MINUTES);
                this.earning += (shift / 60) * rate[1];

                shift = LocalTime.of(12, 00).until(endHour, MINUTES);
                this.earning += (shift / 60) * rate[2];

            } else if (startHour.isAfter(LocalTime.of(11, 59)) && endHour.isBefore(LocalTime.of(18, 01))) {
                shift = startHour.until(endHour, MINUTES);
                this.earning += (shift / 60) * rate[2];
            } else this.earning = earning;

        }


    }


    @Override
    public String toString() {
        return "Babysitter{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rate=" + Arrays.toString(rate) +
                ", daysWorked=" + daysWorked +
                ", hours=" + hours +
                ", earning=" + earning +
                '}';
    }
}
