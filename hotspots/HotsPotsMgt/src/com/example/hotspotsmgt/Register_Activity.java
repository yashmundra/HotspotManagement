package com.example.hotspotsmgt;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Activity extends Activity {
	EditText username,password,name,contact,address,datalimit;
	Button registr;

	 HttpClient httpClient;
	 HttpPost httpPost;
	  ArrayList<NameValuePair> nameValuePairs;
	  Global global;
	  String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_);
		global=(Global) getApplication();
		url=global.ip;
		username= (EditText) findViewById(R.id.usernametxt);
		password= (EditText) findViewById(R.id.passtxt);
		name= (EditText) findViewById(R.id.nametxt);
		contact= (EditText) findViewById(R.id.Contacttxt);
		address= (EditText) findViewById(R.id.addresstxt);
		datalimit= (EditText) findViewById(R.id.limittxt);
		registr=(Button) findViewById(R.id.registerbtn);
		
		
		
		registr.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						addData();
					}
				}).start();
			}
		});


	}

	public void addData()
	{
		try{
			final String a=username.getText().toString();
			final String b=password.getText().toString();
			final String c=name.getText().toString();
			final String d=contact.getText().toString();
			final String e=address.getText().toString();
			final String f=datalimit.getText().toString();
			
			 httpClient=new DefaultHttpClient();
			 Log.w("Check", "1");
			 httpPost=new HttpPost(url+"register.php");
			 Log.w("Check", "2");  
			 nameValuePairs=new ArrayList<NameValuePair>(2);
			   nameValuePairs.add(new BasicNameValuePair("a", a));
			   nameValuePairs.add(new BasicNameValuePair("b", b));
			   nameValuePairs.add(new BasicNameValuePair("c", c));
			   nameValuePairs.add(new BasicNameValuePair("d", d));
			   nameValuePairs.add(new BasicNameValuePair("e", e));
			   nameValuePairs.add(new BasicNameValuePair("f", f));
			   
			   httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			   Log.w("Check", "3");
			   ResponseHandler<String> responseHandler = new BasicResponseHandler();
			   final String response = httpClient.execute(httpPost, responseHandler);
			   Log.w("Check", "4");
			   Log.w("Check", response);
			   System.out.println("Response : " + response); 
			    runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(response.equals("0"))
					Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
					else
						Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
						
				}
			});
		}catch (Exception e) {
			// TODO: handle exception
			Log.w("Error", e);
		  }
	}

}
