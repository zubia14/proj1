package LoggyApp3.services;

import java.util.Map;
import java.util.UUID;

import LoggyApp3.beans.Log;

public interface ApplicationService {

	
    public Map<UUID, Log> readLogs();

    public Log readLog(String id);

    public void createLog(Log log);

    public void updateLog(Log log);

    public void deleteLog(String id);

    public void createOrUpdateLog(Log log);

}

