package com.shun.blog.model.log;

import com.shun.blog.model.common.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_ACCESS_LOG")
public class AccessLog extends AbstractCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCESS_LOG_ID")
    private Long id;

    @Column(name = "ACCESS_LOG_IP", length = 30, nullable = false)
    private String ip;

    @Column(name = "ACCESS_LOG_URL", length = 80, nullable = false)
    private String url;

    @Column(name = "ACCESS_LOG_TIME_ZONE", length = 80)
    private String timeZone;

    //add or minus for data
    @Transient
    private int calculator;

    @Transient
    private Date date;
}
