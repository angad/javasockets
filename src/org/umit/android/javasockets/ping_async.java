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

public class ping_async extends AsyncTask<String, String, String> {

		String ipAddress;
		@Override
		protected String doInBackground(String... params) {	
			ipAddress = params[0];
			boolean success = javasockets.checkReachable(ipAddress);
			if(success)
				return ipAddress;
			else return "";
		}
		
		protected void onPostExecute(String successIp)
		{
			if(!successIp.equals("")){
				javasockets.showResult("isReachable ", successIp + "");
				javasockets.hosts_found++;
			}
		}
}