package com.android.leanback.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class RecentsActivity extends Activity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recents_layout);
	}
}
