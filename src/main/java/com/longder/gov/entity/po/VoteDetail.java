package com.longder.gov.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * 投票详情，选项
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "VOTE_DETAIL")
@Proxy(lazy = false)
public class VoteDetail extends BaseIdEntity{
    /**
     * 关联投票
     */
    @ManyToOne
    @JoinColumn(name = "vote_id_")
    private Vote vote;
    /**
     * 选项值
     */
    @Column(name = "memo_")
    private String memo;
    /**
     * 投票数
     */
    @Column(name = "count_")
    private Integer count;
}
