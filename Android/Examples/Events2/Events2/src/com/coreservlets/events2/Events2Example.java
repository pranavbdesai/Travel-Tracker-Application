package com.coreservlets.events2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

/** Gives a simple event handling example. This second example
 *  (of six) uses a named inner class for the event handler.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class Events2Example extends Activity {
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
        b1.setOnClickListener(new ColorSetter(Color.RED));
        b2.setOnClickListener(new ColorSetter(Color.BLUE));
        b3.setOnClickListener(new ColorSetter(Color.YELLOW));
        r1.setOnClickListener(new ColorSetter(Color.RED));
        r2.setOnClickListener(new ColorSetter(Color.BLUE));
        r3.setOnClickListener(new ColorSetter(Color.YELLOW));
    }
    
    /** Sets the color of the color region. */
    
    private void setRegionColor(int color) {
        mColorRegion.setBackgroundColor(color);
    }
    
    private class ColorSetter implements OnClickListener {
        private int regionColor;
        
        /** Constructs a ColorSetter event handler that stores
         *  the color. The onClick method will use the color to call
         *  back to setRegionColor method in the outer class.
         */
        public ColorSetter(int regionColor) {
            this.regionColor = regionColor;
        }

        /** Calls back to the outer class to set the color of View at the bottom. */
        @Override
        public void onClick(View v) {
            setRegionColor(regionColor);
        }
    }
}