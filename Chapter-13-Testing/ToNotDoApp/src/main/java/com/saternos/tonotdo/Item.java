package com.saternos.tonotdo;

import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@XmlRootElement
public class Item {

  // JodaTime is thread safe.  Java 8 will have a new Date/Time API by its author
  private DateTimeFormatter fmt;

// http://stackoverflow.com/questions/4428109/jersey-jackson-json-date-serialization-format-problem-how-to-change-the-form
// http://stackoverflow.com/questions/5591967/jackson-date-deserialization

  private DateTime internalLastUpdated;  // returns a long - used internally for storage while lastUpdated is formatted

  private String lastUpdated;
  private String description;
  private Integer priority;
  private String externalKey;

  public Item(){
	 fmt = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss-SSSS");
  }

  public void setLastUpdated(String lastUpdated){
	this.lastUpdated = lastUpdated;
	this.internalLastUpdated = fmt.parseDateTime(this.lastUpdated);
  }

  public String getLastUpdated() {
	
	if (this.internalLastUpdated==null){
		return null;
	}
	
	return fmt.print(this.internalLastUpdated);
  }

  public void setExternalKey(String externalKey) {
    this.externalKey = externalKey;
  }

  public String getExternalKey() {
    return externalKey;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

}