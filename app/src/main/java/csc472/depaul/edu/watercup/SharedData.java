package csc472.depaul.edu.watercup;

public class SharedData {

    private static SharedData instance;
    private static int dailyWater;

    private SharedData () {}

    public static SharedData getInstance() {
        if (instance == null) {
           instance =  new SharedData();
        }
        return instance;
    }

    //getters and setters
    public static int getDailyWater (int weight, int age) {
        Calculations calc = new Calculations();
        dailyWater = calc.baseWater(weight, age);
        return dailyWater;
    }
}
