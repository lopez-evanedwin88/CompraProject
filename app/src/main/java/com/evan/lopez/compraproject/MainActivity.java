package com.evan.lopez.compraproject;

import android.database.Cursor;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements  InterfaceItemView{

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout cool;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind (R.id.text_input_layout)
    TextInputLayout txtlayout;

    @Bind(R.id.edtItem)
    EditText edtItem;

    @Bind(R.id.loadingBar)
    ProgressBar bar;

    @Bind(R.id.listview)
    ListView item;

    ItemPresenter mainpresenter;
    ArrayAdapter<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mainpresenter = new ItemPresenter(this,this);
        bar.setVisibility(View.GONE);
        list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>());
        item.setAdapter(list);
        populateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        bar.setVisibility(View.GONE);
    }

    @Override
    public void setItemError() {
        edtItem.setError(getString(R.string.error));
    }

    @Override
    public void displayitem(String name) {
        list.add(name);
        list.notifyDataSetChanged();
    }

    @OnClick(R.id.fab)
    public void submit (View v)
    {
        mainpresenter.validateItem(edtItem.getText().toString());
        edtItem.setText("");
    }


    public void populateView()
    {
        Cursor c = this.getContentResolver().query(Provider.CONTENT_URI, null, null, null, null);

        //   ArrayList<String> itemList = new ArrayList<String>();

        if (c.moveToFirst())
        {
            do
            {
                list.add(c.getString(c.getColumnIndex("_name")));
            }
            while (c.moveToNext());
        }
        list.notifyDataSetChanged();

    }



}
