package com.cigproject;


import java.io.IOException;
import java.sql.SQLException;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;



public class MainActivity extends ActionBarActivity {

    
    private DbHelper myDbHelper;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        myDbHelper = new DbHelper(this);
         
        try {
         	myDbHelper.createDataBase();
        } 
        catch (IOException ioe) {
         	throw new Error("Unable to create database");
         }
         try {
         	myDbHelper.openDataBase();
         }
        catch(SQLException sqle){
         	throw new Error(sqle);
 
        }
        
        populateListFromDB();
        
    }

	// 	
    private void populateListFromDB(){
        
    	Cursor myCursor = myDbHelper.getData();
        startManagingCursor(myCursor);
        
        String[] from = new String[] {myDbHelper.KEY_NAME, myDbHelper.KEY_FILE};
        int[] to = new int[] { R.id.cigName, R.id.cigFile };
		SimpleCursorAdapter adapter;
        adapter=new SimpleCursorAdapter(this , R.layout.dbtolist , myCursor , from, to, 0);
        ListView list1=(ListView)findViewById(R.id.listView1);
        list1.setAdapter(adapter);        	
    }

        
        
 
}
