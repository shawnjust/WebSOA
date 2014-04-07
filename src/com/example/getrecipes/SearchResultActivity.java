package com.example.getrecipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.data.DisplayData;
import com.example.entity.BigOvenRecipeInfoEntity;
import com.example.entity.RecipeInfoEntity;

public class SearchResultActivity extends Activity {

	String searchKeyWord;
	ProgressDialog progressDialog;
	ListView resultListView;
	
	SimpleAdapter adapter;
	List<Map<String, Object>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		resultListView = (ListView) findViewById(R.id.search_result_listview);
		
		searchKeyWord = SearchResultActivity.this.getIntent().getExtras()
				.getString("search_keyword");

		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("Loading...");
//		progressDialog
//				.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {
//
//					@Override
//					public boolean onKey(DialogInterface dialog, int keyCode,
//							KeyEvent event) {
//						return true;
//					}
//				});
		progressDialog.show();

		
		int[] to = new int[]{R.id.search_result_item_recipe_name, R.id.search_result_item_recipe_comefrom};
		String[] from = new String[]{"RecipeName", "ComeFrom"};
		list = new ArrayList<Map<String,Object>>();
		adapter = new SimpleAdapter(this, list, R.layout.search_result_item, from , to);
		resultListView.setAdapter(adapter);

		new Thread(new Runnable() {

			@Override
			public void run() {
				DisplayData displayData = new DisplayData();
				final List<RecipeInfoEntity> result = displayData
						.getDataBySearchKeyWord(searchKeyWord);
				SearchResultActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						for (RecipeInfoEntity entity: result) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("RecipeName", entity.getTitle());
							map.put("ComeFrom", entity.getComeFrom());
							list.add(map);
						}
						adapter.notifyDataSetChanged();
						progressDialog.dismiss();
					}
				});
			}
		}).start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result, menu);
		return true;
	}

}
