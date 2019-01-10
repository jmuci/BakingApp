package com.jmucientes.udacity.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.jmucientes.udacity.bakingapp.R;

import java.util.Arrays;
import java.util.List;

public class IngredientsWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d("Widget::RVFactory", "onGetViewFactory");
        return new IngredientsRemoteViewsFactory(this.getApplicationContext(), intent);
    }

/*    private void handleActionUpdateIngredientWidgets() {
        //TODO Get new ingredients from Shared Prefts

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsWidgetProvider.class));

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_ingredients_list);
    }*/

    class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private final Context mContext;
        private final List<String> mIngredientsList = Arrays.asList("chocolate", "butter", "suggar", "flour", "egg");

        public IngredientsRemoteViewsFactory(Context applicationContext, Intent intent) {
            mContext = applicationContext;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            //TODO Fetch ingredients from Shared Prefs
        }

        @Override
        public void onDestroy() {
            mIngredientsList.clear();
        }

        @Override
        public int getCount() {
            return mIngredientsList.size();
        }

        @Override
        public RemoteViews getViewAt(int i) {
            Log.d("Widget::Service", "getViewAt " + i);
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_layout_item);
            views.setTextViewText(R.id.widget_list_item, mIngredientsList.get(i));

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
