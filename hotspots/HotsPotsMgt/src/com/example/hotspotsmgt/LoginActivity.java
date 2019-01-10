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
import org.json.JSONArray;
import org.json.JSONObject;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
   EditText et1,et2;
   Button btn;
   TextView t1;
   HttpClient httpClient;
	 HttpPost httpPost;
	  ArrayList<NameValuePair> nameValuePairs;
	  Global global;
	  String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		et1=(EditText) findViewById(R.id.et1);
		et2=(EditText) findViewById(R.id.et2);
		btn=(Button) findViewById(R.id.btnLogin);
		t1=(TextView) findViewById(R.id.txtReg);
		global=(Global) getApplication();
		url=global.ip;
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String unm=et1.getText().toString();
				final String pass=et2.getText().toString();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						checkLogin(unm,pass);
					}
				}).start();
			}
			
			
		});
		t1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,Register_Activity.class);
				startActivity(intent);
				
			}
		});
	}
	public void checkLogin(String unm,String pass)
	{
		 try
		 {			
			 //String url="http://10.0.0.2:80/MandarServices/login.php";
			 httpClient=new DefaultHttpClient();
			 
			 httpPost=new HttpPost(url+"login.php");
			 Log.w("Check", "1");
			   nameValuePairs=new ArrayList<NameValuePair>(2);
			   nameValuePairs.add(new BasicNameValuePair("Username", unm));
			   nameValuePairs.add(new BasicNameValuePair("Password", pass));
			   httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			   Log.w("Check", "2");
			   ResponseHandler<String> responseHandler = new BasicResponseHandler();
			   
			   final String response = httpClient.execute(httpPost, responseHandler);
			   Log.w("Response", "3"+" "+response);
			   runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try{
						
						if(response.equalsIgnoreCase("0"))
							Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_LONG).show();
						else
						{
						JSONObject jsonObj = new JSONObject(response);
						 JSONArray data = jsonObj.getJSONArray("data");
						 for(int i=0;i<data.length();i++)
						 {
							 JSONObject c = data.getJSONObject(i);
							 String dl=c.getString("dl");
							 global.dataLimit=dl;
							Log.w("DL", dl);
						 }
						 Intent intent=new Intent(LoginActivity.this,MainActivity.class);
						  startActivity(intent);
						}
						}
						catch (Exception e) {
							// TODO: handle exception
							Log.w("Jason Error", e);
						}
					/*if(response.equalsIgnoreCase("0"))
						Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_LONG).show();
					else{
						//Toast.makeText(getApplicationContext(), "Login Sucess", Toast.LENGTH_LONG).show();
					  Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					  startActivity(intent);
					}*/
				}
			});
			 
		 }catch (Exception e) {
			// TODO: handle exception
			 Log.w("Mandar Error", e); 
		}

	
}

}
