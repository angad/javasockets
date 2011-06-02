package org.umit.android.javasockets;

import android.os.AsyncTask;

public class scan_async extends AsyncTask<Object[], Integer, String> {
	String[] all;
	
	@Override
	protected String doInBackground(Object[]... params)
	{
		all = (String[])params[0];
		String success = "scanned";
    	
		for(int i = 0; i < all.length; i++)
		{
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AsyncTask<String, String, String> sa = new ping_async();
	    	sa.execute(all[i]);
	    	publishProgress((int) ((i * 100.0 / (float) all.length)));
		}
		return success;
	}
	
	protected void onProgressUpdate(Integer... progress) 
	{
		javasockets.updateProgressBar(progress[0]);
	}
	
}
