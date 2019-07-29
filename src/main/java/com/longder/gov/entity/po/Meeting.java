package com.longder.gov.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 会议
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "MEETING")
@Proxy(lazy = false)
public class Meeting extends BaseIdEntity{

    /**
     * 标题
     */
    @Column(name = "title_")
    private String title;

    /**
     * 流程内容
     */
    @Column(name = "content_")
    private String content;

    /**
     * 会议时间
     */
    @Column(name = "meeting_time_")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime meetingTime;

    /**
     * 日期字符串
     */
    @Transient
    private String timeString;

    /**
     * 参会地点
     */
    @Column(name = "place_")
    private String place;

    /**
     * 是否已总结
     * true：已总结
     * false：未总结
     */
    @Column(name = "summarized_")
    private Boolean summarized;

    /**
     * 总结内容
     */
    @Column(name = "summary_content_")
    private String summaryContent;

    /**
     * 所属部门
     */
    @ManyToOne
    @JoinColumn(name = "dept_id_")
    private Department department;

}
