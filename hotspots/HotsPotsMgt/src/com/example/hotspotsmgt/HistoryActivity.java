package com.example.hotspotsmgt;

import android.os.Bundle;
import android.provider.Browser;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HistoryActivity extends Activity {
    TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		 textView=(TextView) findViewById(R.id.tvH);
		 getData();
	}
	
	public void getBrowserHist()  {
        Cursor mCur = managedQuery(Browser.BOOKMARKS_URI,
                Browser.HISTORY_PROJECTION, null, null, null);
        mCur.moveToFirst();
        if (mCur.moveToFirst() && mCur.getCount() > 0) {
            while (mCur.isAfterLast() == false) {
                Log.v("titleIdx", mCur
                        .getString(Browser.HISTORY_PROJECTION_TITLE_INDEX));
                Log.v("urlIdx", mCur
                        .getString(Browser.HISTORY_PROJECTION_URL_INDEX));
                mCur.moveToNext();
           }
      }
}
public void getData()
{
	String[] projection = new String[] {
			Browser.BookmarkColumns.TITLE
			, Browser.BookmarkColumns.URL
			};
			Cursor mCur = managedQuery(android.provider.Browser.BOOKMARKS_URI,
			projection, null, null, null
			);
			mCur.moveToFirst();
			int titleIdx = mCur.getColumnIndex(Browser.BookmarkColumns.TITLE);
			int urlIdx = mCur.getColumnIndex(Browser.BookmarkColumns.URL);
			while (mCur.isAfterLast() == false) {
			textView.append("n" + mCur.getString(titleIdx));
			textView.append("n" + mCur.getString(urlIdx));
			/*String a = mCur.getString(titleIdx);
			String b = mCur.getString(urlIdx);
			
			TableRow tr=new TableRow(MainActivity.this);
			TextView tv0=new TextView(MainActivity.this);
			tv0.setText(b);
			
			TextView tv1=new TextView(MainActivity.this);
			tv1.setText(a);
			
			tr.addView(tv0);							
			tr.addView(tv1);
			
			tableLayout.addView(tr);*/
			mCur.moveToNext();
			}
         }
     
}
