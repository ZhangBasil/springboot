package com.xlh.converter;

/**
 * @author zhangbin
 * @Type UserDO
 * @Desc
 * @date 2017-12-18
 * @Version V1.0
 */
public class UserDO extends BaseDO {
    private boolean man;

    private UserDO self;


    public boolean getMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public UserDO getSelf() {
        return self;
    }

    public void setSelf(UserDO self) {
        this.self = self;
    }
}
