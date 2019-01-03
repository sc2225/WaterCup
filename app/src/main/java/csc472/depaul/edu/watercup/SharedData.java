package csc472.depaul.edu.watercup;

public class SharedData {

    private static SharedData instance;
    private static String temp;

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
        int dailyWater = calc.baseWater(weight, age);
        return dailyWater;
    }

    public static void setTemp(String temperature) {
        temp = temperature;

    }



    public static String getTemp() {
        return  temp;
    }



}
