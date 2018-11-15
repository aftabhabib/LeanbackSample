package com.android.leanback.sample;

import android.view.View;

public interface OnItemCallBack {

    public void onFocusChange(View v, boolean hasFocus, int position);

    public void onItemClick(View v, int position);
}
