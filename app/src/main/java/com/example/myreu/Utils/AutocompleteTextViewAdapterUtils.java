package com.example.myreu.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.myreu.R;
import com.google.android.material.chip.ChipGroup;


public class AutocompleteTextViewAdapterUtils {

    //AutocompleteTextView + chips to add the participants :

    public static void Autocomplete(final AutoCompleteTextView textView, final Context context, Button button, final ChipGroup chipgroup, final Drawable drawable) {


        ArrayAdapter<CharSequence> adapterParticipants = ArrayAdapter.createFromResource(context, R.array.participants_arrays, android.R.layout.simple_dropdown_item_1line);
        textView.setAdapter(adapterParticipants);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView.getText() != null) {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 1);

                    String participant;
                    participant = textView.getText().toString().trim();


                    /* Vérifier que l'utilisateur renseigne un e-mail */
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(participant).matches() || participant.isEmpty()) {
                        Toast.makeText(context, R.string.its_not_an_email_message
                                , Toast.LENGTH_SHORT).show();
                    } else {

                        final com.google.android.material.chip.Chip chip = ChipUtils.addChip(participant, chipgroup, drawable);
                        chip.setChipBackgroundColorResource(R.color.teal_200);
                        chipgroup.addView(chip);
                        chip.setOnCloseIconClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                chipgroup.removeView(chip);

                            }
                        });
                    }
                }
                textView.setText("");
            }
        });
    }
}