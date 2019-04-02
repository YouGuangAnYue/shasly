package com.shasly.common.bean;

import java.sql.Timestamp;

public class UserInfo {

  private long id;
  private long uid;
  private String nickname;
  private String profile;
  private String name;
  private String gender;
  private String ID_number;
  private java.sql.Timestamp birthday;
  private String activation_code;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getID_number() {
    return ID_number;
  }

  public void setID_number(String ID_number) {
    this.ID_number = ID_number;
  }

  public Timestamp getBirthday() {
    return birthday;
  }

  public void setBirthday(Timestamp birthday) {
    this.birthday = birthday;
  }

  public String getActivation_code() {
    return activation_code;
  }

  public void setActivation_code(String activation_code) {
    this.activation_code = activation_code;
  }

  @Override
  public String toString() {
    return "UserInfo{" +
            "id=" + id +
            ", uid=" + uid +
            ", nickname='" + nickname + '\'' +
            ", profile='" + profile + '\'' +
            ", name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", ID_number='" + ID_number + '\'' +
            ", birthday=" + birthday +
            ", activation_code='" + activation_code + '\'' +
            '}';
  }
}
