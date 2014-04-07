package com.example.getrecipes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainPageActivity extends Activity {

	TextView textView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);

		textView = (TextView) findViewById(R.id.textView1);

		new Thread() {
			@Override
			public void run() {
				String httpUrl = "http://api.bigoven.com/recipes?api_key=dvxwX664hy67JXWMljn75t27DDJ9dFG9";
				HttpGet httpRequest = new HttpGet(httpUrl);
				// List<NameValuePair> params = new ArrayList<NameValuePair>();
				// params.add(new BasicNameValuePair("api_key",
				// "dvxwX664hy67JXWMljn75t27DDJ9dFG9"));
				HttpClient httpClient = new DefaultHttpClient();
				try {
					HttpResponse httpResponse = httpClient.execute(httpRequest);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						final String strResult = EntityUtils
								.toString(httpResponse.getEntity());
						MainPageActivity.this.runOnUiThread(new Runnable() {

							@Override
							public void run() {
								textView.setText(strResult);
							}
						});

					} else {
						MainPageActivity.this.runOnUiThread(new Runnable() {

							@Override
							public void run() {
								textView.setText("请求错误!");
							}
						});

					}
				} catch (final ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					MainPageActivity.this.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							textView.setText(e.toString());
						}
					});

				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					MainPageActivity.this.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							textView.setText(e.toString());
						}
					});
				}
			}
		}.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return true;
	}
}
