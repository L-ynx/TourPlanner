package org.fancylynx.application.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fancylynx.application.BL.model.tour.TourModel;
import org.fancylynx.application.BL.model.tourlog.TourLogModel;
import org.fancylynx.application.BL.service.TourLogService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TourLogOverviewViewModel {
    private static final Logger logger = LogManager.getLogger(TourLogOverviewViewModel.class);
    private final TourLogService tourLogService;
    private final List<SelectionChangedListener> listeners = new ArrayList<>();
    private final ObservableList<TourLogModel> tourLogModels = FXCollections.observableArrayList();
    
    @Getter
    private TourModel tour;

    public TourLogOverviewViewModel(TourLogService tourLogService) {
        this.tourLogService = tourLogService;
    }

    public ObservableList<TourLogModel> getObservableTourLogs() {
        return tourLogModels;
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public ChangeListener<TourLogModel> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    private void notifyListeners(TourLogModel newValue) {
        for (var listener : listeners) {
            listener.changeSelection(newValue);
        }
    }

    public void setTour(TourModel tour) {
        if (tour == null) {
            tourLogModels.clear();
            return;
        }

        this.tour = tour;
        tour.setTourLogs(getTourLogs(tour.getTourId()));
        setTourLogs(getTourLogs(tour.getTourId()));
    }

    public List<TourLogModel> getTourLogs(long tourId) {
        return tourLogService.getAllTourLogs(tourId);
    }

    public void setTourLogs(List<TourLogModel> tourLogs) {
        tourLogModels.clear();
        tourLogModels.addAll(tourLogs);
    }

    public void addTourLog() {
        if (tour == null) {
            return;
        }

        var tourLog = tourLogService.createNewTourLog(tour);
        tourLogModels.add(tourLog);

        logger.info("Added new tour log with id=[{}]", tourLog.getTourLogId());
    }

    public void deleteTourLog(TourLogModel tourLog) {
        tourLogService.deleteTourLog(tourLog);
        tourLogModels.remove(tourLog);

        logger.info("Deleted tour log with id=[{}]", tourLog.getTourLogId());
    }

    public interface SelectionChangedListener {
        void changeSelection(TourLogModel tourLogModel);
    }
}
