package com.xlh.demo;

/**
 * @author zhangbin
 * @Type User
 * @Desc
 * @date 2017-07-11
 * @Version V1.0
 */
public class User {
    private Long id;
    @BeanUtil.PropertyIgnore(ignore = true)
    private String name;
    private Integer age;

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
