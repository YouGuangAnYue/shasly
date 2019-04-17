package com.shasly.common.bean;

public class UserInfo {

  private Integer id;
  private Integer uid;
  private String nickname;
  private String profile;
  private String name;
  private String gender;
  private String ID_number;
  private Long birthday;
  private String activation_code;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
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

  public Long getBirthday() {
    return birthday;
  }

  public void setBirthday(Long birthday) {
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
