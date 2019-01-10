package com.example.hotspotsmgt;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class AddActivity extends Activity {
	int mFlipping = 0 ; // Initially flipping is off
	Button mButton ; // Reference to button available in the layout to start and stop the flipper

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);
        if(mFlipping==0){
			//** Start Flipping **//
			flipper.startFlipping();
			mFlipping=1;
			
		}
		else{
			//** Stop Flipping *//*
			flipper.stopFlipping();					
			mFlipping=0;
			
		}
	}
	 public void clapp(View v)
	   {
		   finish();
	       System.exit(0);
	   }
}
