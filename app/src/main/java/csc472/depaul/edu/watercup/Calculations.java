package csc472.depaul.edu.watercup;

public class Calculations implements WaterCalculation {

    private WaterCalculation mainImpl;

    public Calculations () {
        mainImpl = new BaseAlgImpl();
    }

    @Override
    public int baseWater(int weight, int age) {
       return mainImpl.baseWater(weight, age);
    }

    @Override
    public int exerciseWater(double time) {
        return mainImpl.exerciseWater(time);
    }
}
