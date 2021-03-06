package com.jmucientes.udacity.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.util.SharedPrefsUtil;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget_provider);
        views.setEmptyView(R.id.widget_ingredients_list, R.id.empty_view);
        views.setTextViewText(R.id.recipe_name, SharedPrefsUtil.getRecipeName(context));

        Log.d("Widget::Provider", "Connecting to Remote views");
        // Set up the intent that starts the StackViewService, which will
        // provide the views for this collection.
        Intent ingredientsIntent = new Intent(context, IngredientsWidgetService.class);
        // Add the app widget ID to the intent extras.
        // This is how you populate the data.
        views.setRemoteAdapter(R.id.widget_ingredients_list, ingredientsIntent);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_ingredients_list);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

