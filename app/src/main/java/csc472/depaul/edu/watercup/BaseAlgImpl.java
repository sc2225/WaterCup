package csc472.depaul.edu.watercup;

import java.text.DecimalFormat;

public class BaseAlgImpl implements WaterCalculation {
    @Override
    public int baseWater(int weight, int age) {
        double res1 = weight / 2.2;
        //get correct multiplicity base on age
        int multiplier = 0;
        if (age <= 30) {
            multiplier = 40;
        } else if (age >= 31 && age < 55) {
            multiplier = 35;
        } else if (age >= 55) {
            multiplier = 30;
        }

        double res2 = res1 + multiplier;
        int finalres = (int) (res2/28.3);
        return finalres;



    }

    @Override
    public int exerciseWater(double time) {
        double res1 = time / 30;
        int finalresult = (int) (res1 * 12);

        return finalresult;
    }
}
