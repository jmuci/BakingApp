package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import com.jmucientes.udacity.bakingapp.data.network.ConnectionState;
import com.spotify.dataenum.DataEnum;
import com.spotify.dataenum.dataenum_case;

@DataEnum
interface HomeEvent_dataenum {

    dataenum_case InternetStateChanged(ConnectionState connectionState);
    dataenum_case RecipeCardClicked();
}
