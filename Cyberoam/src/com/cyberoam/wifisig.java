package com.cyberoam;

import android.content.Context;
import android.net.wifi.WifiManager;

public class wifisig {
	
	 public static int getWifiStrength(Context context)
		{
		          try
		          {
		        	  WifiManager mywifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		              int rssi = mywifiManager.getConnectionInfo().getRssi();
		              int level = WifiManager.calculateSignalLevel(rssi, 10);
		              int percentage = (int) ((level/10.0)*100);
		              return percentage;
		          }
		          catch (Exception e) 
		          {
		              return 0;
		          }
		}

}
