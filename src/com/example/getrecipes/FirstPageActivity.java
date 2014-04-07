package com.example.getrecipes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class FirstPageActivity extends Activity {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_page);
		textView = (TextView) findViewById(R.id.hello_world);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_page, menu);
		final SearchView searchView = (SearchView) menu.findItem(
				R.id.action_firstpage_search).getActionView();

		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String arg0) {
				Intent intent = new Intent(FirstPageActivity.this,
						SearchResultActivity.class);
				intent.putExtra("search_keyword", arg0);
				FirstPageActivity.this.startActivity(intent);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String arg0) {
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

}
