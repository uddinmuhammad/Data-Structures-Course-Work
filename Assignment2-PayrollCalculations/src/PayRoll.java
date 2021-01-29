import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.*;

public class PayRoll {

    private String personnelDataFile = "src/PersonnelData.txt";
    private String payrollDataFile = "src/PayrollData.txt";

    List<Babysitter> babySitters;
    List<Clock> employeeHours;

    public void readInputData() {


        try {
            String employeeNumber;
            int daysWorked;
            String lastName, firstName;
            double[] rates = new double[3];

            Clock clock;
            babySitters = new ArrayList<>();
            Scanner personnelData = new Scanner(new File(personnelDataFile));
            Scanner payRollDate = new Scanner(new File(payrollDataFile));

            while (personnelData.hasNext() || payRollDate.hasNext()) {
                Babysitter babysitter;
                employeeHours = new ArrayList<>();

                employeeNumber = personnelData.next();
                lastName = removeComma(personnelData.next());
                firstName =  personnelData.next();
                personnelData.nextLine();
                personnelData.nextLine();
                personnelData.nextLine();

                for (int i = 0; i < rates.length; i++) {
                    rates[i] = personnelData.nextDouble();
                }

                employeeNumber = payRollDate.next();
                daysWorked = payRollDate.nextInt();
                payRollDate.nextLine();

                for (int i = 0; i < daysWorked; i++) {
                    LocalTime clockIn = convertimeToLocalTime(payRollDate.next());
                    LocalTime clockOut = convertimeToLocalTime(payRollDate.next());

                    clock = new Clock(clockIn, clockOut);
                    employeeHours.add(clock);
                }

                babysitter = new Babysitter(employeeNumber, firstName, lastName, rates, daysWorked, employeeHours);

                babySitters.add(babysitter);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public String removeComma(String name){
        if (name.endsWith(",")) {
            return name.substring(0, name.length() - 1);
        }
        return name;
    }

    public LocalTime convertimeToLocalTime(String time){
        String timeSplit[]= time.split(":");
        int hour = Integer.parseInt(timeSplit[0]);
        int min = Integer.parseInt(timeSplit[1]);

        LocalTime localTime = LocalTime.of(hour, min);

        return localTime;
    }

    public void printBabysitterChecks()
    {
        Babysitter temp;

        for(int i = 0; i< babySitters.size(); i++) {

            for (int j = i + 1; j < babySitters.size(); j++) {

                if (babySitters.get(i).getLastName().compareTo(babySitters.get(j).getLastName()) > 0) {

                    temp = babySitters.get(i);
                    babySitters.set(i, babySitters.get(j));
                    babySitters.set(j, temp);

                }
            }
        }

        for(int i = 0; i < babySitters.size(); i++)
        {
            System.out.println(babySitters.get(i).getLastName() +", "+ babySitters.get(i).getFirstName() + ": $" + babySitters.get(i).getEarning());

        }
    }

    public void start() {
        readInputData();
        printBabysitterChecks();
    }
}
