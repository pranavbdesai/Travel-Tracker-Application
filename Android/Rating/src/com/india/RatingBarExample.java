package com.india;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class RatingBarExample extends Activity
{

                RatingBar ratingbar;
                TextView ratings;

                public void onCreate(Bundle savedInstanceState)
{
                                super.onCreate(savedInstanceState);
                                setContentView(R.layout.main);

                                ratings = (TextView) findViewById(R.id.rating);
                                ratingbar = (RatingBar) findViewById(R.id.ratingbar);

                                ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener()
{
                                                public void onRatingChanged(RatingBar ratingBar, float rating,
                                                                                boolean fromUser)
{
                                                                // TODO Auto-generated method stub
                                                                ratings.setText("The Rating is " + rating);
                                                }
                                });
                }
}