package com.oxchains.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * User
 * 
 * @author liuruichao
 * Created on 2016-01-15 10:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    private Double amount;
}
