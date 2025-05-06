package ch.morn.historian.charts.mornCharts.controller.advice;

import ch.morn.historian.charts.mornCharts.exception.StationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StationNotFoundAdvice {
    @ExceptionHandler(StationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String stationNotFoundHandler(StationNotFoundException ex) {
        return ex.getMessage();
    }
}
