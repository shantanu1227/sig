package com.android.personalca;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.Toast;


public class Start extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		databasehandler db =new databasehandler(this);
		
		//---add 2 titles---
      /*  db.open();        
        long id;
        id = db.insertAccount("manan",-20);
        		
        id = db.insertAccount(
        		"047017661X",
        		40
        		);
        db.close();
		*/
        db.open();
    Cursor d= db.getdata("manan");
    String id1;
    String name1;
   int Amount =25;
    id1=d.getString(0);
       name1=d.getString(1);
       long id2= Long.parseLong(id1);
       db.updateAccount(id2,name1,Amount);
        db.close();
        
            
       db.open();
        Cursor c = db.getAllAccounts();
        if (c.moveToFirst())
        {
            do {          
                DisplayAccount(c);
            } while (c.moveToNext());
        }
        db.close();
    /*
        db.open();
        if (db.deleteAccount(1))
            Toast.makeText(this, "Delete successful.", 
                Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", 
                Toast.LENGTH_LONG).show();   
        if (db.deleteAccount(5))
            Toast.makeText(this, "Delete successful.", 
                Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", 
                Toast.LENGTH_LONG).show(); 
        if (db.deleteAccount(6))
            Toast.makeText(this, "Delete successful.", 
                Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", 
                Toast.LENGTH_LONG).show(); 
        
        db.close();
	*/
	}    
    
	
	
        public void DisplayAccount(Cursor c)
        {
            Toast.makeText(this, 
                    "Id: " + c.getString(0) + "\n" +
                    "name: " + c.getString(1) + "\n" + 
                    "amount: " + c.getString(2) +"\n",
                     Toast.LENGTH_LONG).show();        
        
        } 
		
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_start, menu);
		return true;
	}

}
