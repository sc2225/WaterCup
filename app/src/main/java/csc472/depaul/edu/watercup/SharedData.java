package csc472.depaul.edu.watercup;

public class SharedData {

    private static SharedData instance;
    private static int weightValue = 0;

    private SharedData () {}

    public static SharedData getInstance() {
        if (instance == null) {
           instance =  new SharedData();
        }
        return instance;
    }

    //getters and setters
    public static void setWeight (int val) {
        weightValue = val;
        System.out.println("Weight is set to " + val);
    }

    public static int getWeight() {
        return weightValue;
    }
}
