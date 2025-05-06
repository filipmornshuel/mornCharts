package ch.morn.historian.charts.mornCharts.exception;

public class StationNotFoundException extends RuntimeException{
    public StationNotFoundException(Long id) {
        super("Could not find station " + id);
    }
}
