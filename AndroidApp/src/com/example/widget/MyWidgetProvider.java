package com.example.widget;

 import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class MyWidgetProvider extends AppWidgetProvider {
	private static final String ACTION_CLICK = "ACTION_CLICK";
	public static String WIDGET_BUTTON = "com.example.widget.WIDGET_BUTTON";
    private static final String SYNC_CLICKED    = "automaticWidgetSyncButtonClick";
    
    @Override
  	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
  	{
  		/*
  		 * AppWidgetProvider extends BroadcastReceiver, so we must not spend
  		 * lots of processing time in this class. Actual processing is done in
  		 * a Service so that this method can return as quickly as possible.
  		 */
  		context.startService(getIntentForService(context));
  		
  	}
      @Override
  	public void onDeleted(Context context, int[] appWidgetIds)
  	{
  		context.stopService(getIntentForService(context));

  		super.onDeleted(context, appWidgetIds);
  	}
      private Intent getIntentForService(Context context)
  	{
  		Intent widgetService = new Intent(context.getApplicationContext(), WifiWidgetService.class);
  		return widgetService;
  	}
/*
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		 RemoteViews remoteViews;
	        ComponentName watchWidget;

	        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
	        watchWidget = new ComponentName(context, MyWidgetProvider.class);

	        remoteViews.setOnClickPendingIntent(R.id.update, getPendingSelfIntent(context, SYNC_CLICKED));
	        appWidgetManager.updateAppWidget(watchWidget, remoteViews);

	/*	// Get all ids
		ComponentName thisWidget = new ComponentName(context,
				MyWidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		//for (int widgetId : allWidgetIds) {
			// create some random data
			int number = (new Random().nextInt(100));

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
			
			Log.w("WidgetExample", String.valueOf(number));
			// Set the text
			remoteViews.setTextViewText(R.id.update, String.valueOf(number));

			// Register an onClickListener
			Intent intent = new Intent();

			intent.setAction("android.intent.action.DIAL");
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, 0);
			remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
			appWidgetManager.updateAppWidget(allWidgetIds, remoteViews);
		//}
	}
	@Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        if (SYNC_CLICKED.equals(intent.getAction())) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            RemoteViews remoteViews;
            ComponentName watchWidget;

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            watchWidget = new ComponentName(context, MyWidgetProvider.class);
        	intent.setAction("android.intent.action.DIAL");
        	
          //  remoteViews.setTextViewText(R.id.update, "TESTING");
         // Register an onClickListener
 
         			/*intent.setAction("android.intent.action.DIAL");
          			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
         					0, new Intent("android.intent.action.DIAL"), Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
         			remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
         			
         			Intent wifiSettings = new Intent(Settings.ACTION_WIFI_SETTINGS);
    				PendingIntent pendingIntentImage = PendingIntent
    					.getActivity(context, 0, wifiSettings,
    						PendingIntent.FLAG_UPDATE_CURRENT);
    				remoteViews.setOnClickPendingIntent(R.id.update,
    					pendingIntentImage);
    				
            appWidgetManager.updateAppWidget(watchWidget, remoteViews);

        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    } */
    
  
}
