package com.longder.gov.service;

import com.longder.gov.entity.po.Meeting;

import java.util.List;

/**
 * Created by Longder
 */
public interface MeetingManageService {

    /**
     * 查询部门下的所有会议
     * @param deptId
     * @return
     */
    List<Meeting> listForDept(Long deptId);

    /**
     * 添加一个会议
     * @param meeting
     */
    void addOneMeeting(Meeting  meeting);

    /**
     * 查询获取一个会议
     * @param meetingId
     * @return
     */
    Meeting getOneMeeting(Long meetingId);

    /**
     * 总结会议
     * @param summaryContent
     * @param meetingId
     */
    void summaryMeeting(String summaryContent,Long meetingId);


}
