package com.headfirstlabs.nasadailyimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	IotdHandler handler = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		handler = new IotdHandler();
		new ResetDisplayTask().execute();
//		resetDisplay(handler.getTitle(), handler.getDate(), handler.getImage(), handler.getDescription());
	}

	private class ResetDisplayTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			handler.processFeed();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			resetDisplay(handler.getTitle(), handler.getDate(), handler.getImage(), handler.getDescription());
			super.onPostExecute(result);
		}
	}
	
	private void resetDisplay(String title, String date, Bitmap image, StringBuffer description) {
		TextView titleView = (TextView) findViewById(R.id.imageTitle);
		titleView.setText(title);
		TextView dateView = (TextView) findViewById(R.id.imageDate);
		dateView.setText(date);
		ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
		imageView.setImageBitmap(image);
		TextView descriptionView = (TextView) findViewById(R.id.imageDescription);
		descriptionView.setText(description);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
