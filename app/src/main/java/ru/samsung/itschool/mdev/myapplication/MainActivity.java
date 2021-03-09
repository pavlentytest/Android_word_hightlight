package ru.samsung.itschool.mdev.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private TextView tv;
    private ClickableSpan clickableSpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.hello);

        ArrayList<String> wordsToHighlight = new ArrayList<>();
        String fullText = "A Zoom spokesperson said the transfers were related to Mr Yuan's estate planning practices. The distributions were made in accordance with the terms of Eric Yuan and his wife's trusts. Zoom's shares have nearly tripled in the past 12 months and the company has a market valuation of around $100bn.";
        String[] words = fullText.split(" ");
        wordsToHighlight.addAll(Arrays.asList(words));

        SpannableString spannableString = new SpannableString(fullText);

        ArrayList<String> brokenDownFullText = new ArrayList<>(Arrays.asList(fullText.split(" ")));
        brokenDownFullText.retainAll(wordsToHighlight);
        int i = 0;
        for(i=0; i<wordsToHighlight.size(); i++) {
            int indexOfWord = fullText.indexOf(wordsToHighlight.get(i));
            int finalI = i;
            clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    Snackbar.make(findViewById(R.id.root),wordsToHighlight.get(finalI),Snackbar.LENGTH_LONG).show();
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };
            spannableString.setSpan(clickableSpan, indexOfWord, indexOfWord + wordsToHighlight.get(i).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tv.setText(spannableString);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setHighlightColor(Color.BLACK);


    }
}