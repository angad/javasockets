/*
Various methods of Android Ping
Network Information, Host Discovery

Copyright (C) 2011 Adriano Monteiro Marques

Author: Angad Singh <angad@angad.sg>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

 */

package org.umit.android.javasockets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.os.AsyncTask;

public class arp_async extends AsyncTask<Void, String, Integer> {

		String ipAddress;
		@Override
		protected Integer doInBackground(Void... params)
		{	
	    	int hosts_found = 0;
	    	try {
				BufferedReader br = new BufferedReader(new FileReader("/proc/net/arp"));
				String line;
				try
				{
					while((line = br.readLine()) != null)
					{
						 String[] splitted = line.split(" +");
						 if (splitted != null && splitted.length >= 4) 
						 {
							 // Basic sanity check
							 String mac = splitted[3];
							 String ip = splitted[0];
							 if (mac.matches("..:..:..:..:..:..") && !mac.equals("00:00:00:00:00:00")) 
							 {
								 hosts_found ++;
								 publishProgress(ip, mac);
							 }
						 }
					}
					publishProgress("0", hosts_found + "");
					br.close();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			} 
	    	catch (FileNotFoundException e) 
	    	{
				e.printStackTrace();
			}	
	    	return hosts_found;
		}
		
		protected void onProgressUpdate(String... params) 
		{	
			String ip = params[0];
			String mac = params[1];
			
			if(ip.equals("0"))
			{
				javasockets.fillProgressBar();
				javasockets.showResult("ARP" , "found " + params[1] + " hosts");
			}
			else {
				javasockets.showResult(ip, mac);
				javasockets.updateProgressBar(1);
			}
		}
		
		protected void onPostExecute(Integer... params)
		{
			//...
		}
}
