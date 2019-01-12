package com.jmucientes.udacity.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.recipedetailslist.RecipeDetailListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.jmucientes.udacity.bakingapp.recipedetailslist.RecipeDetailListFragment.INGREDIENTS_PREFS_NAME;

public class IngredientsWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d("Widget::RVFactory", "onGetViewFactory");
        return new IngredientsRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private final Context mContext;
        private List<String> mIngredientsList = Arrays.asList("chocolate", "butter", "suggar", "flour", "egg");

        public IngredientsRemoteViewsFactory(Context applicationContext, Intent intent) {
            mContext = applicationContext;
            SharedPreferences recipeIngredients = getSharedPreferences(INGREDIENTS_PREFS_NAME, MODE_PRIVATE);

            Set<String> ingredientsSet = recipeIngredients.getStringSet(RecipeDetailListFragment.INGR_STRING_SET, null);
            if (ingredientsSet != null) {
                mIngredientsList = new ArrayList<>(ingredientsSet);
            }
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
