package com.example.myreu.Utils;

import android.graphics.drawable.Drawable;

import com.example.myreu.R;
import com.google.android.material.chip.ChipGroup;


public class ChipUtils {

    public static com.google.android.material.chip.Chip addChip(String participant, ChipGroup chipgroup, Drawable drawable) {

        final com.google.android.material.chip.Chip chip = new com.google.android.material.chip.Chip(chipgroup.getContext());
        chip.setChipBackgroundColorResource(R.color.purple_200);

        chip.setText(participant);
        chip.setChipIcon(drawable);
        chip.setCheckable(false);
        chip.setClickable(true);
        chip.setCloseIconVisible(true);
        return chip;
    }


}
