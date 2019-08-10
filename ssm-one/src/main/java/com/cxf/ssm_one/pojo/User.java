package com.cxf.ssm_one.pojo;

import lombok.Data;

/**
 * @author always_on_the_way
 * @date 2019-06-26
 */
@Data
public class User {

    private Long id;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String telephone;

    private String username;
    private String password;
    //盐值
    private String salt;


}
