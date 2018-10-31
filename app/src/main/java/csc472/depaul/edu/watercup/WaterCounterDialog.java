package csc472.depaul.edu.watercup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.NumberPicker;


public class WaterCounterDialog extends DialogFragment {
    private NumberPicker.OnValueChangeListener valueChangeListener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final NumberPicker numPicker = new NumberPicker(getActivity());

        numPicker.setMinValue(1);
        numPicker.setMaxValue(100);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Choose number of ounces drank in water:");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                valueChangeListener.onValueChange(numPicker,
                        numPicker.getValue(), numPicker.getValue());

            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        builder.setView(numPicker);

        return builder.create();

    }

    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}
