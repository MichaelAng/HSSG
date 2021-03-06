/************************************************************************
 *   Copyright 2013 Derek Li, Michael Ang
 *
 *   This file is part of The High School Study Guide (HSSG).
 *
 *   HSSG is free software created by Seidenberg Creative Laboratory 
 *   for non-commercial use.
 *   
 *   Github account: https://github.com/MichaelAng/HSSG
 ************************************************************************/

package com.example.chemistry;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class FileChooser extends ListActivity {
    
    private File currentDir;
    private FileArrayAdapter adapter;
    SharedPreferences someData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDir = new File("/sdcard/");
        fill(currentDir);

    }
    private void fill(File f)
    {
        File[]dirs = f.listFiles();
         this.setTitle("Current Dir: "+f.getName());
         List<Option>dir = new ArrayList<Option>();
         List<Option>fls = new ArrayList<Option>();
         try{
             for(File ff: dirs)
             {
                if(ff.isDirectory()){
                    dir.add(new Option(ff.getName(),"Folder",ff.getAbsolutePath()));
                }
                else
                {
                	if(ff.getName().contains(".txt"))
                		fls.add(new Option(ff.getName(),"File Size: "+ff.length(),ff.getAbsolutePath()));
                }
             }
         }catch(Exception e)
         {
             
         }
         Collections.sort(dir);
         Collections.sort(fls);
         dir.addAll(fls);
         if(!f.getName().equalsIgnoreCase("sdcard"))
             dir.add(0,new Option("..","Parent Directory",f.getParent()));
         adapter = new FileArrayAdapter(FileChooser.this,R.layout.file_view,dir);
         this.setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Option o = adapter.getItem(position);
        if(o.getData().equalsIgnoreCase("folder")||o.getData().equalsIgnoreCase("parent directory")){
                currentDir = new File(o.getPath());
                fill(currentDir);
        }
        else
        {
            onFileClick(o);
        }
    }
    private void onFileClick(Option o)
    {
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    	SharedPreferences.Editor editor = preferences.edit();
    	editor.putString("fileName", o.getPath()); // value to store
    	editor.commit();
    	
		Toast toast = Toast.makeText(getApplicationContext(),
				"You have selected " + o.getName() + " as your file.",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();
    	startActivity(new Intent(this, MainPage.class));
    }
    
	@Override
	public void onBackPressed() {// Restatcks the return function
		Intent backToMA = new Intent(this, MainPage.class);
		backToMA.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(backToMA);
	}// Ends onBackPressed method
}
