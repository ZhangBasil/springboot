package com.xlh.converter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zhangbin
 * @Type BaseDO
 * @Desc
 * @date 2017-12-18
 * @Version V1.0
 */
public class BaseDO {
    private Long id;
    private String name;
    private Date createTime;
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
