package com.SqlExamp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SqlExampActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 1, "Add User Information");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i=new Intent(SqlExampActivity.this, insert.class);
		startActivity(i);
		return true;
	}
	
	
	
    
}