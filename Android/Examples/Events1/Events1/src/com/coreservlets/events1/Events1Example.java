package com.coreservlets.events1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/** Gives a simple event handling example. This first example
 *  (of six) uses a separate (non-inner) class for the event handler.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class Events1Example extends Activity {
    // The Android coding style conventions say that non-public instance variables should start with "m".
    // "m" for "member" (as in "data member" or "member variable").
    private View mColorRegion;
    
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mColorRegion = findViewById(R.id.color_region);
        Button b1 = (Button)findViewById(R.id.button1);
        Button b2 = (Button)findViewById(R.id.button2);
        Button b3 = (Button)findViewById(R.id.button3);
        RadioButton r1 = 
                (RadioButton)findViewById(R.id.radio_button1);
        RadioButton r2 = 
                (RadioButton)findViewById(R.id.radio_button2);
        RadioButton r3 = 
                (RadioButton)findViewById(R.id.radio_button3);
        b1.setOnClickListener(new ColorSetter(Color.RED, this));
        b2.setOnClickListener(new ColorSetter(Color.BLUE, this));
        b3.setOnClickListener(new ColorSetter(Color.YELLOW, this));
        r1.setOnClickListener(new ColorSetter(Color.RED, this));
        r2.setOnClickListener(new ColorSetter(Color.BLUE, this));
        r3.setOnClickListener(new ColorSetter(Color.YELLOW, this));
    }
    
    /** Sets the color of the color region. */
    
    public void setRegionColor(int color) {
        mColorRegion.setBackgroundColor(color);
    }
}