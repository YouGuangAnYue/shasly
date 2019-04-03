package com.shasly.common.bean;

public class GoodsEvaluate {

  private long id;
  private long gid;
  private long star;
  private double point;
  private long sales_volume;
  private long total_collection;
  private long total_comment;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getGid() {
    return gid;
  }

  public void setGid(long gid) {
    this.gid = gid;
  }


  public long getStar() {
    return star;
  }

  public void setStar(long star) {
    this.star = star;
  }

  public double getPoint() {
    return point;
  }

  public void setPoint(double point) {
    this.point = point;
  }


  public long getSales_volume() {
    return sales_volume;
  }

  public void setSales_volume(long sales_volume) {
    this.sales_volume = sales_volume;
  }

  public long getTotal_collection() {
    return total_collection;
  }

  public void setTotal_collection(long total_collection) {
    this.total_collection = total_collection;
  }

  public long getTotal_comment() {
    return total_comment;
  }

  public void setTotal_comment(long total_comment) {
    this.total_comment = total_comment;
  }

  @Override
  public String toString() {
    return "GoodsEvaluate{" +
            "id=" + id +
            ", gid=" + gid +
            ", star=" + star +
            ", point=" + point +
            ", sales_volume=" + sales_volume +
            ", total_collection=" + total_collection +
            ", total_comment=" + total_comment +
            '}';
  }
}
