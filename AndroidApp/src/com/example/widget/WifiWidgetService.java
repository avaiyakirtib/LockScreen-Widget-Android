package com.example.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RemoteViews;

import com.example.androidapp.R;

/**
 * Service that provides background processing in order to update the info on
 * the widget.
 */
public class WifiWidgetService extends Service
{
	String ipAddress;
	boolean wifiConnected = false;
	Thread widgetUpdateThread;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		widgetUpdateThread = new WidgetUpdateThread(this, intent);
		widgetUpdateThread.start();

		return START_REDELIVER_INTENT;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}

	 
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

	 
	 

	private class WidgetUpdateThread extends Thread
	{
		Context context;

		WidgetUpdateThread(Context context, Intent intent)
		{
			this.context = context;
		}

		@Override
		public void run()
		{
			try
			{
 
				/* PREPARE THE UPDATE */

				// set remote views
				RemoteViews widgetUi = new RemoteViews(
					context.getPackageName(), R.layout.widget_layout);
				//widgetUi.setTextViewText(R.id.tvIpAddress, ipAddress);

				 


				// set the click actions to open wifi settings
				Intent wifiIpSettings = new Intent(
					Settings.ACTION_WIFI_IP_SETTINGS);
				
				PendingIntent pendingIntentIp = PendingIntent.getActivity(
					context, 0, wifiIpSettings,
					PendingIntent.FLAG_UPDATE_CURRENT);
				widgetUi.setOnClickPendingIntent(R.id.update,
					pendingIntentIp);

					 
				
				Intent wifiSettings = new Intent();
				wifiSettings.setAction("android.intent.action.DIAL");
				wifiSettings.setFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);


				PendingIntent pendingIntentImage = PendingIntent
					.getActivity(context, 0, wifiSettings,
						PendingIntent.FLAG_UPDATE_CURRENT);
				widgetUi.setOnClickPendingIntent(R.id.update,
					pendingIntentImage);


				/* UPDATE THE WIDGET INSTANCE */

				try
				{
					ComponentName widgetComponent = new ComponentName(context,
						MyWidgetProvider.class);
					AppWidgetManager widgetManager = AppWidgetManager
						.getInstance(context);
					widgetManager.updateAppWidget(widgetComponent, widgetUi);
				}
				catch (Exception e)
				{
					Log.e("WifiWidget", "Failed to update widget", e);
				}
			}
			catch (Exception e)
			{
				Log.e("WifiWidget", "Failed to update widget", e);
			}
			finally
			{
				// clean up
				WifiWidgetService.this.stopSelf();
			}
		}
	}


}
