package com.example.hotspotsmgt;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.net.TrafficStats;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.TrafficStats;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class DataUActivity extends Activity {

private long mStartRX = 0;
private long mStartTX = 0;
long totMb,rMb,tMb,rxBytes,txBytes,limit;

TextView RX,TX,TO,TL;
Timer myTimer;
Global global;
int dataL;
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_data_u);
			mStartRX = TrafficStats.getTotalRxBytes();
			mStartTX = TrafficStats.getTotalTxBytes();
			 final Handler mHandler = new Handler();
			 global=(Global) getApplication();
			 dataL=Integer.parseInt(global.dataLimit);
					  RX = (TextView)findViewById(R.id.RX);
					  TX = (TextView)findViewById(R.id.TX);
					  TO = (TextView)findViewById(R.id.total);
					  TL = (TextView)findViewById(R.id.limit);
				      checkData();
			
     }
public void checkData()
{

    myTimer=new Timer();
	myTimer.schedule(new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			DataUActivity.this.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					 rxBytes = TrafficStats.getTotalRxBytes()- mStartRX;
					
					 txBytes = TrafficStats.getTotalTxBytes()- mStartTX;
					
					 rMb = rxBytes / (1024 * 1024);
					 tMb = txBytes / (1024 * 1024);
					RX.setText("Byte: "+rxBytes+" MB: "+rMb+" ");
					TX.setText("Byte: "+txBytes+" MB: "+tMb+"");
					totMb=rMb+tMb;
					TO.setText("Total MB="+(totMb));
					TL.setText("Total Limit="+dataL+" Mb");
					checkLimit(totMb);
				}
			});
			}
	}, 100,100);


}
public void checkLimit(long totMb)
  {
	if(totMb>dataL){
		
		totMb=0;
		rMb=0;
		tMb=0;
		txBytes=0;
		rxBytes=0;
		myTimer.cancel();
		Toast.makeText(getApplicationContext(), "Limit Reach", Toast.LENGTH_LONG).show();
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifi.setWifiEnabled(false);
		
		Intent intent=new Intent(DataUActivity.this,AddActivity.class);
		startActivity(intent);
		/*AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
		 
        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete...");
 
        // Setting Dialog Message
        alertDialog.setMessage("You Exceed Max Limit of WIFI");
 
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_launcher);
 
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
 
            // Write your code here to invoke YES event
            //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
            	WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            	wifi.setWifiEnabled(false);
            }
        });
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Continoue", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            // Write your code here to invoke NO event
            Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
            dialog.cancel();
            }
        });
 
        // Showing Alert Message
        alertDialog.show();*/
	}
  }
}