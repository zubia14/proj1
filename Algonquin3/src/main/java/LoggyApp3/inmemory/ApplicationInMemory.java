package LoggyApp3.inmemory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import LoggyApp3.beans.Log;
import LoggyApp3.services.ApplicationService;

public class ApplicationInMemory implements ApplicationService {

    private Map<UUID, Log> logs;

    public ApplicationInMemory() {
        this.logs = new LinkedHashMap<UUID, Log>();
 
    }

  
    public Map<UUID, Log> readLogs() {
        return logs;
    }

    public Log readLog(String id) {
        return logs.get(UUID.fromString(id));
    }

    @Override
    public void createLog(Log log) {
        updateLog(log);
    }

    @Override
    public void updateLog(Log log) {
        logs.put(log.getId(), log);
    }

    @Override
    public void deleteLog(String id) {
        logs.remove(UUID.fromString(id));
    }

    @Override
    public void createOrUpdateLog(Log log) {
        Log locallog = readLog(log.getId().toString());
        if (locallog == null) {
            createLog(log);
        } else {
            updateLog(log);
        }
    }

}


