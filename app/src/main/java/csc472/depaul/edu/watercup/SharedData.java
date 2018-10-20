package csc472.depaul.edu.watercup;

public class SharedData {

    private static SharedData instance;

    private SharedData () {}

    public static SharedData getInstance() {
        if (instance == null) {
           instance =  new SharedData();
        }
        return instance;
    }

    //getters and setters
}
