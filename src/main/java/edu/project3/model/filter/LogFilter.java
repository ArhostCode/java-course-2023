package edu.project3.model.filter;

import edu.project3.model.log.NginxLog;

public interface LogFilter {

    boolean isSuitable(NginxLog log);
}
