package com.evan.lopez.compraproject;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by cl5admin on 7/11/2015.
 */
public class ItemPresenter {

    InterfaceItemView view;
    Context context;
    ItemModel model;

    public ItemPresenter (InterfaceItemView item, Context context)
    {
        this.view=item;
        this.context=context;
        this.model = new ItemModel();
    }

    public void validateItem(String name)
    {
        view.showProgress();
        model.put(name,this,context);
    }

    public void ItemError()
    {
        view.setItemError();
        view.hideProgress();
    }

    public void Success(String name)
    {
        Toast.makeText(context,"snackbar ta dri", Toast.LENGTH_SHORT).show();
        view.displayitem(name);
        view.hideProgress();
        //pass to mainactivity sa list

    }



}
