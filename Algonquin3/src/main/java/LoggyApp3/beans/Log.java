package LoggyApp3.beans;

import java.util.Date;
import java.util.UUID;

public abstract class Log {
    private UUID id;
    private String title;
    private String content;
    private Date createTimestamp;
    
    public UUID getId() {
    	return id;   	
    }
    
    public void setId(UUID id) {
    	this.id=id;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public void setTitle(String title) {
    	this.title=title;
    }
    
    public String getContent() {
    	return content;
    }
    
    public void setContent(String content) {
    	this.content=content;
    }
    
    public Date getCreateTimestamp() {
    	return createTimestamp;
    }
    
    public void setCreateTimestamp(Date createTimestamp) {
    	this.createTimestamp=createTimestamp;
    }
}