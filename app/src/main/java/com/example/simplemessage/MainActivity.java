package com.example.simplemessage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.messagelibrary.SimpleToastMessage;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioSimpleMessage, radioColoredMessage, radioImageMessage;
    private EditText editTextMessage;
    private Button buttonShowToast;
    private Spinner spinnerColors, spinnerImages, spinnerTextColors;
    private SeekBar seekBarTextSize, seekBarDialogSize, seekBarDialogDuration;
    private TextView textSizeLabel, dialogSizeLabel, dialogDurationLabel;
    private CheckBox checkBoxAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViews();
        setArrayAdapters();
        setOnClick();
    }

    private void setOnClick() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioColoredMessage) {
                spinnerColors.setVisibility(View.VISIBLE);
                spinnerImages.setVisibility(View.GONE);
            } else if (checkedId == R.id.radioImageMessage) {
                spinnerColors.setVisibility(View.GONE);
                spinnerImages.setVisibility(View.VISIBLE);
            } else {
                spinnerColors.setVisibility(View.GONE);
                spinnerImages.setVisibility(View.GONE);
            }
        });

        seekBarTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSizeLabel.setText("גודל טקסט: " + progress + "sp");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarDialogSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dialogSizeLabel.setText("גודל דיאלוג: " + progress + "dp");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarDialogDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dialogDurationLabel.setText("משך דיאלוג: " + progress + " שניות");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        buttonShowToast.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString();
            int textSize = seekBarTextSize.getProgress();
            int dialogSize = seekBarDialogSize.getProgress();
            int dialogDuration = seekBarDialogDuration.getProgress() * 1000;
            boolean withAnimation = checkBoxAnimation.isChecked();
            String selectedTextColor = spinnerTextColors.getSelectedItem().toString();
            int textColor = getColorFromName(selectedTextColor);

            if (radioSimpleMessage.isChecked()) {
                SimpleToastMessage.show(this, message, textSize, textColor, dialogSize, dialogDuration, withAnimation);
            } else if (radioColoredMessage.isChecked()) {
                String selectedColor = spinnerColors.getSelectedItem().toString();
                int color = getColorFromName(selectedColor);
                SimpleToastMessage.showWithBackgroundColor(this, message, color, textSize, textColor, dialogSize, dialogDuration, withAnimation);
            } else if (radioImageMessage.isChecked()) {
                String selectedImage = spinnerImages.getSelectedItem().toString();
                int imageResId = getResources().getIdentifier(selectedImage, "drawable", getPackageName());
                float imageSize = 48.0f;
                if (imageResId != 0) {
                    SimpleToastMessage.showWithImage(this, message, imageResId, textSize, imageSize, textColor, dialogSize, dialogDuration, withAnimation);
                } else {
                    SimpleToastMessage.show(this, "תמונה לא תקינה", textSize, textColor, dialogSize, dialogDuration, withAnimation);
                }
            }
        });
    }

    private void setArrayAdapters() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColors.setAdapter(adapter);

        ArrayAdapter<CharSequence> imageAdapter = ArrayAdapter.createFromResource(this,
                R.array.images_array, android.R.layout.simple_spinner_item);
        imageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerImages.setAdapter(imageAdapter);

        ArrayAdapter<CharSequence> textColorAdapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);
        textColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextColors.setAdapter(textColorAdapter);
    }

    private void findViews() {
        radioGroup = findViewById(R.id.radioGroup);
        radioSimpleMessage = findViewById(R.id.radioSimpleMessage);
        radioColoredMessage = findViewById(R.id.radioColoredMessage);
        radioImageMessage = findViewById(R.id.radioImageMessage);
        editTextMessage = findViewById(R.id.editTextMessage);
        spinnerColors = findViewById(R.id.spinnerColors);
        spinnerImages = findViewById(R.id.spinnerImages);
        spinnerTextColors = findViewById(R.id.spinnerTextColors);
        buttonShowToast = findViewById(R.id.buttonShowToast);
        seekBarTextSize = findViewById(R.id.seekBarTextSize);
        textSizeLabel = findViewById(R.id.textSizeLabel);
        seekBarDialogSize = findViewById(R.id.seekBarDialogSize);
        dialogSizeLabel = findViewById(R.id.dialogSizeLabel);
        seekBarDialogDuration = findViewById(R.id.seekBarDialogDuration);
        dialogDurationLabel = findViewById(R.id.dialogDurationLabel);
        checkBoxAnimation = findViewById(R.id.checkBoxAnimation);

    }
    private int getColorFromName(String colorName) {
        switch (colorName) {
            case "Red":
                return Color.RED;
            case "Green":
                return Color.GREEN;
            case "Blue":
                return Color.BLUE;
            case "Yellow":
                return Color.YELLOW;
            case "Cyan":
                return Color.CYAN;
            case "Magenta":
                return Color.MAGENTA;
            default:
                return Color.WHITE;
        }
    }
}