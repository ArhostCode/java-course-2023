package edu.project3.log;

import edu.project3.model.log.NginxLog;
import java.util.List;

public interface NginxLogParser {

    List<NginxLog> parse(List<String> lines);

}
