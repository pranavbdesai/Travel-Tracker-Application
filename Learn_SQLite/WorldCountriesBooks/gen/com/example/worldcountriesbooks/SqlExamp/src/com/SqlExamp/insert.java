package com.SqlExamp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert extends Activity {
	
	EditText et1,et2,et3,et4;
	Button but1,but2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_add);
		
		et1=(EditText) findViewById(R.id.etfname);
		et2=(EditText) findViewById(R.id.etlname);
		et3=(EditText) findViewById(R.id.etno);
		et4=(EditText) findViewById(R.id.etemail);
		but1=(Button) findViewById(R.id.SubBut);
		but2=(Button) findViewById(R.id.Showbut);
		
		
		but1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				DbHandler dbh=new DbHandler(insert.this);
				SQLiteDatabase sd=dbh.getWritableDatabase();
				
				ContentValues cv=new ContentValues();
				cv.put(dbh.FIRSTN, et1.getText().toString());
				cv.put(dbh.LASTN, et2.getText().toString());
				cv.put(dbh.NO, et3.getText().toString());
				cv.put(dbh.EMAIL, et4.getText().toString());
				
				sd.insert(dbh.TABLE_NAME, null, cv);
				Toast.makeText(insert.this, "successfully inserted data", Toast.LENGTH_SHORT).show();
				
				et1.setText("");
				et2.setText("");
				et3.setText("");
				et4.setText("");
			}
		});
		
		but2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent i= new Intent(insert.this, fetchData.class);
				startActivity(i);
			}
		});
		 
		
	}
	

	public void putData(String fname, String lname, int con, String email) {
		et1=(EditText) findViewById(R.id.etfname);
		et2=(EditText) findViewById(R.id.etlname);
		et3=(EditText) findViewById(R.id.etno);
		et4=(EditText) findViewById(R.id.etemail);
		et1.setText(fname);
		et2.setText(lname);
		et3.setText(con);
		et4.setText(email);
		
	}
	
	

}
