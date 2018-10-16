package com.SqlExamp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class fetchData extends Activity {
	String fname,lname,email;
	int con;
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_show);
       
        lv=(ListView) findViewById(R.id.listView1);
        lv.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, al));
        Cursor c=SqlExampActivity();
        ShowData(c);
        
        lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> set, View view, int pos,long num) {
				
				
			String s=al.get(pos);
			Cursor c=SqlExampActivity(s);
			ShowImpData(c);
			}

			

			
		});
        
    }
	private void ShowImpData(Cursor c) {
		while(c.moveToNext()){
		fname=(c.getString(0));
		lname=(c.getString(1));
		con=(c.getInt(2));
		
		email=(c.getString(3));
		
		insert ssd=new insert();
		ssd.putData(fname, lname, con, email);
			
		Intent i=new Intent(fetchData.this, insert.class);
		i.putExtra(fname, true);
		i.putExtra(lname, true);
		//i.putExtra(con, true);
		i.putExtra(email, true);
		startActivity(i);
		
		}
	}
	private Cursor SqlExampActivity(String s) {
		String name=s;
		
		Cursor cc;
		DbHandler dbh= new DbHandler(fetchData.this);
		SQLiteDatabase sd=dbh.getReadableDatabase();
		cc=sd.query(dbh.TABLE_NAME, null, dbh.FIRSTN+"="+'"'+name+'"', null, null, null, null);
		startManagingCursor(cc);
		return cc;
	}
	private void ShowData(Cursor c) {
		Boolean a=c.moveToFirst();
		if(a==null){
			String s="No Information Yet Inserted";
			al.add(s);
			lv.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, al));
		}
		else{
		do{
			String FName=c.getString(0);
			al.add(FName);
		}while(c.moveToNext());
		 lv.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, al));
	}}

	private Cursor SqlExampActivity() {
		Cursor cc;
		DbHandler dbh= new DbHandler(fetchData.this);
		SQLiteDatabase sd=dbh.getReadableDatabase();
		cc=sd.query(dbh.TABLE_NAME, null, null, null, null, null, null);
		startManagingCursor(cc);
		return cc;
	}
}
