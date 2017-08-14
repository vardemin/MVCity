package com.vardemin.vcity.eventbus;

public class NavigationEvent {
    private Routes previousRoute;
    private Routes nextRoute;

    public NavigationEvent(Routes previousRoute, Routes nextRoute) {
        this.previousRoute = previousRoute;
        this.nextRoute = nextRoute;
    }

    public Routes getNextRoute() {
        return nextRoute;
    }

    public Routes getPreviousRoute() {
        return previousRoute;
    }
}
