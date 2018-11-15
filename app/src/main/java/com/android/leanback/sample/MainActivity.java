package com.android.leanback.sample;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VerticalGridView verticalGridView;
    private static final ArrayList<String> mdatas = new ArrayList<>();
    private static final ArrayList<PackageInfo> mPackageInfoList = new ArrayList<>();
    private static final String TAG = "MainActivity";
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;

        getAppList();
        verticalGridView = findViewById(R.id.verticalGridView);
        adapter = new MyAdapter(this, mPackageInfoList);
        adapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void onFocusChange(View v, boolean hasFocus, int position) {

            }

            @Override
            public void onItemClick(View v, int position) {

            }
        });
        verticalGridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void getAppList() {
        PackageManager pm = getPackageManager();
        // Return a List of all packages that are installed on the device.
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        mPackageInfoList.clear();
        for (PackageInfo packageInfo : packages) {
            Log.d(TAG, "getAppList: " + packageInfo.applicationInfo.flags + " packagesName" + packageInfo.packageName);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                mPackageInfoList.add(packageInfo);
        }
    }
}
