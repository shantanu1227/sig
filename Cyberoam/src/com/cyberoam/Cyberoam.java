package com.cyberoam;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewDatabase;
import android.widget.Button;
import android.widget.TextView;
import android.view.MenuItem;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContextWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Cyberoam extends Activity {

	public static final int MENU_USERNAME =Menu.FIRST+1;
	public static final int MENU_PASSWORD =Menu.FIRST+2;
	public static final int MENU_ABOUTUS =Menu.FIRST+3;
	private WebView mywebview;
	public  WebViewDatabase mydata;
	private WebViewClient myclient;
	private static final String userfile ="user";
    private static final String passfile ="pass";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyberoam);
        
        
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
      
       
       
        if (mWifi.isConnected()) {
        	setContentView(R.layout.activity_cyberoam);
        } 	      
        
        else{
        	startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }
        
       
        
        
        WifiManager myWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
        int rssi = myWifiInfo.getRssi();
        int level = WifiManager.calculateSignalLevel(rssi, 10);
        int percentage = (int) ((level/10.0)*100);
        System.out.println(percentage);
        
        
        
        
      StringBuffer fileContent = new StringBuffer("");
      FileInputStream fis;
      int length;
      try {
		fis = openFileInput( userfile );
	
       byte[] buffer = new byte[1024];
      
      while ((length = fis.read(buffer)) != -1) {
          fileContent.append(new String(buffer));
      }} 
	catch (FileNotFoundException e1) {
  		// TODO Auto-generated catch block
  		e1.printStackTrace();
  	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	
      StringBuffer fileContent2 = new StringBuffer("");
      FileInputStream fis2;
      int length2;
      try {
		fis2 = openFileInput( passfile );
	
       byte[] buffer = new byte[1024];
      
      while ((length2 = fis2.read(buffer)) != -1) {
          fileContent2.append(new String(buffer));
      }} 
	catch (FileNotFoundException e1) {
  		// TODO Auto-generated catch block
  		e1.printStackTrace();
  	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
   
    	
    	final StringBuffer finuser= fileContent;
    	final StringBuffer finpass = fileContent2;
        mywebview =(WebView) findViewById(R.id.webview);
        mywebview.getSettings().setJavaScriptEnabled(true);
       mywebview.setWebViewClient(myclient);
        
       
      // mywebview.loadUrl("file:///android_asset/httpclient.html");
       mywebview.loadUrl("http://10.100.56.55:8090/httpclient.html");
      
       mywebview.setWebViewClient(new WebViewClient() {
    	   	
        	   public void onPageFinished(WebView view, String url) {
        //		   mywebview.loadUrl("javascript:document.forms[0].autocomplete= '"+on+"';");
        	        mywebview.loadUrl("javascript:document.forms[0].username.value= '"+finuser+"';");
       // 	        mywebview.loadUrl("javascript:document.forms[0].password.autocomplete= '"+on+"';");
        	        mywebview.loadUrl("javascript:document.forms[0].password.value= '"+finpass+"';");
        	        mywebview.loadUrl("javascript:document.forms[0].btnSubmit.click();");	
        	       	
       /* 	       	int i=1;
             	   wifisig a =new wifisig();
                    while(i<200){
                    int sig=a.getWifiStrength(getApplicationContext());
                    System.out.println(sig);
                    		if(sig<70){
                    	mywebview.loadUrl("javascript:document.forms[0].btnSubmit.click();");	
                    }
                    i++;
                    }
        	    */   	
        	   }
        	   
       }) ;
       			
       
      
    }   
    	  
        
      
              
            
           
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      	//  getMenuInflater().inflate(R.menu.activity_cyberoam, menu);
    	menu.add(Menu.NONE,MENU_USERNAME,Menu.NONE,"Username");
    	
    	menu.add(Menu.NONE,MENU_PASSWORD,Menu.NONE,"Password");
    
    	menu.add(Menu.NONE,MENU_ABOUTUS,Menu.NONE,"About Us");
    	return(super.onCreateOptionsMenu(menu));
            
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
    case MENU_USERNAME:
    user();
    return(true);

    case MENU_PASSWORD:
    pass();
    return(true);
    
    case MENU_ABOUTUS:
        aboutus();
        return(true);
    }

    return(super.onOptionsItemSelected(item));
    }
    
    private void user(){
    	final View addView=getLayoutInflater().inflate(R.layout.username,null);
      	new AlertDialog.Builder(this)
    	.setTitle("Set Username")
    	.setView(addView)
    	.setPositiveButton("OK",
    	new DialogInterface.OnClickListener(){
    	public void onClick(DialogInterface dialog,int whichButton){
    
    	EditText title=(EditText)addView.findViewById(R.id.title);
    	String temp1= title.getText().toString();
    	try {
    	FileOutputStream fos = openFileOutput(userfile, Context.MODE_PRIVATE);
       	fos.write(temp1.getBytes());
    	
			fos.close();
		} 
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	}
    	})
    	.setNegativeButton("Cancel",null)
    	.show();
    	}
    private void pass(){
    	final View addView=getLayoutInflater().inflate(R.layout.password,null);
 
    	new AlertDialog.Builder(this)
    	.setTitle("Set Password")
    	.setView(addView)
    	.setPositiveButton("OK",
    	new DialogInterface.OnClickListener(){
    	public void onClick(DialogInterface dialog,int whichButton){
    
    	EditText title2=(EditText)addView.findViewById(R.id.title2);
    	String temp1= title2.getText().toString();
    	try {
    	FileOutputStream fos = openFileOutput(passfile, Context.MODE_PRIVATE);
       	fos.write(temp1.getBytes());
    	
			fos.close();
		} 
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	}
    	})
    	.setNegativeButton("Cancel",null)
    	.show();
    	}
    private void aboutus(){
    	final View addView=getLayoutInflater().inflate(R.layout.aboutus,null);
    	 
    	new AlertDialog.Builder(this)
    	.setTitle("ABOUT US")
    	.setView(addView)
    	.setPositiveButton("OK",null)
    	.show();
    	
    }
   
    
    
}


