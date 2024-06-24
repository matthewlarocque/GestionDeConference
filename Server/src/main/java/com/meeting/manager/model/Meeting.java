
package com.meeting.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MEETING")
public class Meeting extends BaseEntity {

    /**
     * 会议室名
     */
    @Column(name = "NAME_", length = 60)
    private String name;

    /**
     * 规格
     */
    @Column(name = "TYPE_", length = 60)
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
