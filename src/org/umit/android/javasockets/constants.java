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

interface constants 
{
	//now overridden by the UI element ttl (EditText)
	public static final int thread_sleep = 11;
	public static final int debug_lines = 15;
}

interface ttl {
	public static final int is_reachable = 2000;
	public static final int echo_ping = 5000;
	public static final int socket_ping = 1000;
}

interface ports{
	public static final int[] port = {
		139,
		445,
		22,
		80
	};
}