package com.jmucientes.udacity.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IngredientsWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d("Widget::RVFactory", "onGetViewFactory");
        return new IngredientsRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private final Context mContext;
        private List<String> mIngredientsList;

        public IngredientsRemoteViewsFactory(Context applicationContext, Intent intent) {
            mContext = applicationContext;
            Set<String> ingredientsSet = SharedPrefsUtil.fetchSavedIngredientsSet(applicationContext);
            if (ingredientsSet != null) {
                mIngredientsList = new ArrayList<>(ingredientsSet);
            }
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            Set<String> ingredientsSet = SharedPrefsUtil.fetchSavedIngredientsSet(mContext);
            if (ingredientsSet != null) {
                mIngredientsList = new ArrayList<>(ingredientsSet);
            }
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
