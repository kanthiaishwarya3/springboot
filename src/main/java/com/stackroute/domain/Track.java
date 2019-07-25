package com.stackroute.domain;

import io.swagger.annotations.ApiModelProperty;
//import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
// Implementing Lombok with required annotations
/*
Internally it generates setters,getters,toString...reduces the hard code for users
@Data,@NoArgsConstructor,@AllArgsConstructor,@Builder
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track
{
    // Declaration of fields in table
    @Id
    int id;
    String firstName;
    String lastName;
    int age;


}


