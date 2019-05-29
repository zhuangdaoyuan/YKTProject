package com.nesql;

@DbTable("tb_user")
public class User {

    @DbField("id")
    private Integer id;

    @DbField("username")
    private String userName;

    @DbField("psd")
    private String psd;

    public User(Integer id, String userName, String psd) {
        this.id = id;
        this.userName = userName;
        this.psd = psd;
    }

}
