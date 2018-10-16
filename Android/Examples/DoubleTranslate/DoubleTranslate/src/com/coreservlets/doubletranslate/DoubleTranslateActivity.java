package com.coreservlets.doubletranslate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/** Translates some text from English into another language,
 *  then back to English again.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */
public class DoubleTranslateActivity extends Activity {
    private EditText mText;
    private Spinner mLanguageChoices;
    private TextView mIntermediateResult, mFinalResult;
    // Note that this key will be regenerated periodically, so the one below will not work long-term.
    // If you want this to work reliably for the long term, download your own key and replace
    // the following value with your own key.
    private String apiKey =
        "AIzaSyDwdDH3Au9jRsgJ0SDuz9LIxVIqSoMkPdo";
    
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mText = (EditText)findViewById(R.id.text);
        mLanguageChoices = (Spinner)findViewById(R.id.language_choices);
        List<Language> supportedLanguages = TranslateUtils.supportedLanguages(apiKey);
        ArrayAdapter<Language> spinner2Adapter =
                new ArrayAdapter<Language>(this, android.R.layout.simple_spinner_item, supportedLanguages);
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguageChoices.setAdapter(spinner2Adapter);
        mIntermediateResult = (TextView)findViewById(R.id.intermediate_result);
        mFinalResult = (TextView)findViewById(R.id.final_result);
    }
    
    public void doubleTranslate(View clickedButton) {
        String sourceLanguageCode = "en";
        Language targetLanguage = (Language)mLanguageChoices.getSelectedItem();
        String targetLanguageCode = targetLanguage.getCode();
        String textToTranslate = mText.getText().toString();
        Translator translator = new Translator(apiKey, sourceLanguageCode, targetLanguageCode, textToTranslate);
        String firstTranslation = translator.getFirstTranslation();
        mIntermediateResult.setText(firstTranslation);
        String secondTranslation = translator.getSecondTranslation();
        mFinalResult.setText(secondTranslation);
    }
}