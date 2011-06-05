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

import android.os.AsyncTask;

public class scan_async extends AsyncTask<Object[], Integer, Void> {
	String[] all;
	
	@Override
	protected Void doInBackground(Object[]... params)
	{
		all = (String[])params[0];
    	
		for(int i = 0; i < all.length; i++)
		{
			publishProgress((int) ((i * 100.0 / (float) all.length)));
			try 
			{
				Thread.sleep(constants.thread_sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AsyncTask<String, String, String> sa = new ping_async();
	    	sa.execute(all[i]);
		}
		return null;
	}

	protected void onProgressUpdate(Integer... progress) 
	{
		javasockets.updateProgressBar(progress[0]);
		if(progress[0] == 99)
		{
			javasockets.showResult("Discovered", javasockets.hosts_found + " hosts");
		}
	}
}
