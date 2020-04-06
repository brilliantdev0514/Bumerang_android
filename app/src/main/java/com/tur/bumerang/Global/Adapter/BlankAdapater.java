package com.tur.bumerang.Global.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class BlankAdapater extends BaseAdapter {
    Context context1;
    ArrayList<String> strings1= new ArrayList<>();

    public BlankAdapater(Context context, ArrayList<String> strings) {
        context1= context;
        strings1 = strings;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
