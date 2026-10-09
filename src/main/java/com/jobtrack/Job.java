package com.jobtrack;
import jakarta.persistence.*;
@Entity
public class Job {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  public Long id;
  public String company, role, status;
  public Job(){}
  public Job(String c,String r,String s){company=c; role=r; status=s;}
}
