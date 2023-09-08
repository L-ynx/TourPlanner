package org.fancylynx.application.BL.service;

import org.fancylynx.application.BL.model.tour.RouteModel;
import org.fancylynx.application.BL.model.tour.TourModelNew;

public interface RouteService {
    public RouteModel getRoute(TourModelNew tour);
    public String getStaticMap(String sessionId);
}
