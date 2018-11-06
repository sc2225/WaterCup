package csc472.depaul.edu.watercup;

public class SharedData {

    private static SharedData instance;
    private static int dailyWater;
    private static String temp;
    private static String desc;
    private static String condition;

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

    public static void setTemp(String temperature) {
        temp = temperature;

    }

    public static void setDesc(String des) {
        desc = des;
    }

    public static void setCondition(String cond) {
        condition = cond;
    }

    public static String getTemp() {
        return  temp;
    }

    public static String getDesc() {
        return desc;
    }

    public static String getCondition() {
        return condition;
    }

}
