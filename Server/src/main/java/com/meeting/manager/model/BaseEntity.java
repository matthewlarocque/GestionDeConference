package com.meeting.manager.model;

import com.meeting.manager.dialect.JsonStringType;
import com.meeting.manager.dialect.StringArrayType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述：所有实体的基类
 */
@MappedSuperclass
@TypeDefs({@TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),})
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -3621575739735747181L;
    /**
     * 实体id，做为数据表的唯一标识
     */
    @Id
    @Column(name = "ID_", nullable = false, updatable = false, length = 32)
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    private String id;
    /**
     * 创建时间(修改后更新)
     */
    @Column(updatable = false, length = 19)
    private String createtime;
    /**
     * 分页起始位置
     */
    @Transient
    private Integer startPosition = 0;
    /**
     * 当前页码
     */
    @Transient
    private Integer currentPage = 0;
    /**
     * 每页数据条数
     */
    @Transient
    private Integer sizePerPage = 10;
    /**
     * 是否分页
     */
    @Transient
    private Boolean isPage = false;

    public BaseEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getSizePerPage() {
        return sizePerPage;
    }

    public void setSizePerPage(Integer sizePerPage) {
        this.sizePerPage = sizePerPage;
    }
    
    public Boolean getIsPage() {
        return isPage;
    }

    public void setIsPage(Boolean isPage) {
        this.isPage = isPage;
    }

    public Integer getStartPosition() {
        return currentPage * sizePerPage;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }
}
