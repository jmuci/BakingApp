package com.jmucientes.udacity.bakingapp.home.mobius.eventsources;

import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEvent;
import com.spotify.mobius.EventSource;

public class HomeEventSources {
    private HomeEventSources() {
    }

    public static EventSource<HomeEvent> provideEventSource() {
        return null;
    }

}
