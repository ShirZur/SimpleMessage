package com.example.messagelibrary;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleToastMessage {
    public static void show(Context context, String message, float textSize, int textColor, int dialogSize, int dialogDuration, boolean withAnimation) {
        Log.d("SimpleToast", "TextSize: " + textSize);
        showDialog(context, message, 0, 0, textSize, 0, false, false, textColor, Typeface.DEFAULT, dialogSize, dialogDuration, withAnimation);
    }

    public static void showWithImage(Context context, String message, int imageResId, float textSize, float imageSize, int textColor, int dialogSize, int dialogDuration, boolean withAnimation) {
        showDialog(context, message, imageResId, 0, textSize, imageSize, true, false, textColor, Typeface.DEFAULT, dialogSize, dialogDuration, withAnimation);
    }

    public static void showWithBackgroundColor(Context context, String message, int bgColor, float textSize, int textColor, int dialogSize, int dialogDuration, boolean withAnimation) {
        showDialog(context, message, 0, bgColor, textSize, 0, false, true, textColor, Typeface.DEFAULT, dialogSize, dialogDuration, withAnimation);
    }

    public static void showCustomFont(Context context, String message, float textSize, int textColor, Typeface typeface, int dialogSize, int dialogDuration, boolean withAnimation) {
        showDialog(context, message, 0, 0, textSize, 0, false, false, textColor, typeface, dialogSize, dialogDuration, withAnimation);
    }

    private static void showDialog(Context context, String message, int imageResId, int bgColor, float textSize, float imageSize, boolean isImage, boolean isBackground, int textColor, Typeface typeface, int dialogSize, int dialogDuration, boolean withAnimation) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.toast_custom, null);

        TextView textView = customView.findViewById(R.id.toast_text);
        textView.setText(message);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        textView.setTypeface(typeface);

        if (isImage) {
            ImageView imageView = customView.findViewById(R.id.toast_image);
            imageView.setImageResource(imageResId);
            imageView.setVisibility(View.VISIBLE);
            if (imageSize > 0) {
                imageView.getLayoutParams().width = (int) (imageSize * context.getResources().getDisplayMetrics().density);
                imageView.getLayoutParams().height = (int) (imageSize * context.getResources().getDisplayMetrics().density);
                imageView.requestLayout();
            }
        }

        if (isBackground) {
            customView.setBackgroundColor(bgColor);
        }

        Dialog dialog = new Dialog(context);
        dialog.setContentView(customView);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        // Set dialog size
        if (dialogSize > 0) {
            dialog.getWindow().setLayout(dialogSize, dialogSize);
        }

        Button closeButton = customView.findViewById(R.id.toast_close_button);
        closeButton.setVisibility(View.VISIBLE);
        closeButton.setOnClickListener(v -> dialog.dismiss());

        if (withAnimation) {
            // Animation for slide-in and slide-out
            Animation slideIn = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            slideIn.setDuration(500);
            customView.startAnimation(slideIn);

            customView.postDelayed(() -> {
                Animation slideOut = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                slideOut.setDuration(500);
                customView.startAnimation(slideOut);
                slideOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }, dialogDuration); // Close the dialog after the specified duration
        } else {
            customView.postDelayed(dialog::dismiss, dialogDuration);
        }
    }

}
