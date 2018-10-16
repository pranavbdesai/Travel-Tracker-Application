package com.coreservlets.events1;

import android.view.View;
import android.view.View.OnClickListener;

/** An event handler in a separate (non-inner) class.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class ColorSetter implements OnClickListener {
    private int mRegionColor;
    private Events1Example mMainActivity;
    
    /** Constructs a ColorSetter event handler stores
     *  the color and the main Activity. The onClick
     *  method will use those two objects to call
     *  back to the main Activity with the specified color.
     */
    public ColorSetter(int regionColor, Events1Example mainActivity) {
        this.mRegionColor = regionColor;
        this.mMainActivity = mainActivity;
    }

    /** Calls back to the main Activity to set the color of View at the bottom. */
    @Override
    public void onClick(View v) {
        mMainActivity.setRegionColor(mRegionColor);
    }
}
