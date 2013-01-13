package com.clarionmedia.infinitumdemo.application;

import android.os.Bundle;
import android.view.Menu;

import com.clarionmedia.infinitum.activity.InfinitumListActivity;
import com.clarionmedia.infinitum.activity.annotation.InjectLayout;
import com.clarionmedia.infinitumdemo.R;

@InjectLayout(R.layout.activity_sqlite)
public class SqliteActivity extends InfinitumListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sqlite, menu);
		return true;
	}

}
