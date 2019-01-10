package com.example.hotspotsmgt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
  public void showH(View v)
  {
	  Intent intent=new Intent(MainActivity.this,HistoryActivity.class);
	  startActivity(intent);
  }
  public void dataL(View v)
  {
	  Intent intent=new Intent(MainActivity.this,DataUActivity.class);
	  startActivity(intent);
  }
} 
