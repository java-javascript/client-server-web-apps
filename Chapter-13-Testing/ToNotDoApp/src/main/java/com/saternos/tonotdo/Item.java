package com.saternos.tonotdo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Item is the basic resource type being returned in XML or JSON format,
 * hence the XmlRootElement to allow Jersey to do this serialization
 * automatically
 */
@XmlRootElement
public class Item {

    /**
     * Using JodaTime since it is thread safe.
     * Java 8 will have a new Date/Time API by the author of JodaTime, so
     * this is more in line with future development.
     */
    private DateTimeFormatter fmt;

    /**
     * returns a long - used internally for storage while lastUpdated is formatted
     */
    private DateTime internalLastUpdated;

    // The date format behavior can be affected by adding a chunk of "magic code."   See:
    // http://stackoverflow.com/questions/4428109/jersey-jackson-json-date-serialization-format-problem-how-to-change-the-form
    // http://stackoverflow.com/questions/5591967/jackson-date-deserialization

    private String lastUpdated;
    private String description;
    private Integer priority;
    private String externalKey;

    /**
     * Set the default DateTimeFormat
     */
    public Item() {
        fmt = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
    }

    /**
     * Set the internal and formatted dates
     *
     * @param lastUpdated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
        this.internalLastUpdated = fmt.parseDateTime(this.lastUpdated);
    }

    /**
     * Return the formatted date or null
     *
     * @return
     */
    public String getLastUpdated() {

        if (this.internalLastUpdated == null) {
            return null;
        }

        return fmt.print(this.internalLastUpdated);
    }

    /**
     * Set the external key.  It is defined externally but used internally as a keu as well to ensure uniqueness.
     *
     * @param externalKey
     */
    public void setExternalKey(String externalKey) {
        this.externalKey = externalKey;
    }

    /**
     * Get the external key.
     *
     * @return
     */
    public String getExternalKey() {
        return externalKey;
    }

    /**
     * Get the description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the priority
     *
     * @return
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Set the priority
     *
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}