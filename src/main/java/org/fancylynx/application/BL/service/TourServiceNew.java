package org.fancylynx.application.BL.service;

import org.fancylynx.application.BL.model.tour.TourModelNew;
import org.fancylynx.application.DAL.entity.Tour;

import java.util.List;

public interface TourServiceNew {
    List<TourModelNew> getAllTours();
    void createNewTour(Tour tour);



}
