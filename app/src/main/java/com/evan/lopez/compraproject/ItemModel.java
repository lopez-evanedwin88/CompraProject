package com.evan.lopez.compraproject;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by Administrator on 7/14/2015.
 */
public class ItemModel {

    public void put(final String name, final ItemPresenter listen, final Context context) {

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(name))
                {
                    listen.ItemError();
                    error = true;
                }

                if (!error) {

                    ContentValues values = new ContentValues();
                    values.put("_name", name);
                    Uri uri = context.getContentResolver().insert(Provider.CONTENT_URI,values);
                    listen.Success(name);

//                    main.list.add(name);
//                    main.list.notifyDataSetChanged();
                }

            }
        },2000);
    }

}
