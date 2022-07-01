package LoggyApp3.beans;

import java.util.Date;
import java.util.UUID;

public abstract class Log {
    private UUID id;
    private String title;
    private String content;
    private Date createTimestamp;

    public Log() {
    }

    public Log(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.createTimestamp = new Date();
    }

    // get id
    public UUID getId() {
        return id;
    }

    // set id
    public void setId(UUID id) {
        this.id = id;
    }

    // get title
    public String getTitle() {
        return title;
    }

    // set title
    public void setTitle(String title) {
        this.title = title;
    }

    // get content
    public String getContent() {
        return content;
    }

   // set content
    public void setContent(String content) {
        this.content = content;
    }

   // get createTimestamp
    public Date getCreateTimestamp() {
        return createTimestamp;
    }
    
    // set createTimestamp    
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

}