package com.oxchains.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Transaction
 *
 * @author liuruichao
 * Created on 2016/12/19 10:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer fromId;

    private Integer toId;

    private Date createTime = new Date();

    private Double money;

    private User fromUser;

    private User toUser;
}
