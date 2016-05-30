
package com.example.admin.click_counter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.click_counter.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private RelativeLayout Layout;
    int total=0,a,b,c,i,j;
    SharedPreferences prefs;
    int select[]={50,150,250};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        total = prefs.getInt("newtotal", 0);
        a = prefs.getInt("red", 0);
        b = prefs.getInt("green", 0);
        Layout = (RelativeLayout) findViewById(R.id.layout);
        Layout.setBackgroundColor(Color.argb(255, a, b, 150 ));
        TextView mytextView=(TextView) findViewById(R.id.text);
        mytextView.setText(Integer.toString(total));
    }

    protected void onPause()
    {
        super.onPause();
        SharedPreferences.Editor editPrefs = prefs.edit();
        editPrefs.putInt("newtotal", total);
        editPrefs.putInt("red", a);
        editPrefs.putInt("green", b);
        editPrefs.putInt("blue", 150);
        editPrefs.commit();
    }

    public void onButtonClick1(View v)
    {
        TextView mytextView=(TextView) findViewById(R.id.text);
        total++;
        mytextView.setText(Integer.toString(total));
        Layout = (RelativeLayout) findViewById(R.id.layout);
        Random rand = new Random();
        i=rand.nextInt(2);
        j=rand.nextInt(2);
        a=select[i];
        b=select[j];
        Layout.setBackgroundColor(Color.argb(255, a, b, 150 ));
    }

    public void onButtonClick2(View v)
    {
        TextView mytextView=(TextView) findViewById(R.id.text);
        Layout = (RelativeLayout) findViewById(R.id.layout);
        total=0;
        mytextView.setText(Integer.toString(total));
        a=b=c=255;
        Layout.setBackgroundColor(Color.argb(255, a, b, c ));
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        TextView mytextView = (TextView) findViewById(R.id.text);
        savedInstanceState.putCharSequence("myText",mytextView.getText());
        savedInstanceState.putInt("cl",a);
        savedInstanceState.putInt("c2",b);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        TextView mytextView=(TextView) findViewById(R.id.text);
        Layout = (RelativeLayout) findViewById(R.id.layout);
        CharSequence tText=savedInstanceState.getCharSequence("myText");
        a=savedInstanceState.getInt("cl",a);
        b=savedInstanceState.getInt("c2",b);
        Layout.setBackgroundColor(Color.argb(255, a, b, 150 ));
        total = Integer.parseInt((String) tText);
        if (savedInstanceState != null)
        {
            mytextView.setText(Integer.toString(total));
        }
    }
}