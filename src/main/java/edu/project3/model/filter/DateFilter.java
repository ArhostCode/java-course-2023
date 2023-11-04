package edu.project3.model.filter;

import edu.project3.model.log.NginxLog;
import java.time.OffsetDateTime;

public record DateFilter(OffsetDateTime fromDate, OffsetDateTime toDate) implements LogFilter {

    @Override
    public boolean isSuitable(NginxLog log) {
        if (fromDate == null && toDate == null) {
            return true;
        }
        if (fromDate == null && (toDate.isAfter(log.timeStamp()) || toDate.isEqual(log.timeStamp()))) {
            return true;
        }
        if (toDate == null && (fromDate.isBefore(log.timeStamp()) || fromDate.isEqual(log.timeStamp()))) {
            return true;
        }
        return fromDate != null
            && toDate != null
            && (fromDate.isBefore(log.timeStamp()) || fromDate.isEqual(log.timeStamp()))
            && (toDate.isAfter(log.timeStamp()) || toDate.isEqual(log.timeStamp()));
    }
}
