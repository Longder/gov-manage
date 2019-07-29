package com.longder.gov.service.impl;

import com.longder.gov.entity.po.Meeting;
import com.longder.gov.entity.po.SysUser;
import com.longder.gov.repository.MeetingRepository;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.MeetingManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Longder
 */
@Service
public class MeetingManageServiceImpl implements MeetingManageService {

    @Resource
    private MeetingRepository meetingRepository;

    /**
     * 查询部门下的所有会议
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Meeting> listForDept(Long deptId) {

        return meetingRepository.listByDeptId(deptId);
    }

    /**
     * 添加一个会议
     *
     * @param meeting
     */
    @Override
    @Transactional
    public void addOneMeeting(Meeting meeting) {
        //set部门
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        meeting.setDepartment(currentUser.getDepartment());
        //时间
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        meeting.setMeetingTime(LocalDateTime.parse(meeting.getTimeString(), df));
        //初始未总结
        meeting.setSummarized(false);
        meetingRepository.save(meeting);

    }

    /**
     * 查询获取一个会议
     *
     * @param meetingId
     * @return
     */
    @Override
    public Meeting getOneMeeting(Long meetingId) {
        return meetingRepository.getOne(meetingId);
    }

    /**
     * 总结会议
     *
     * @param summaryContent
     * @param meetingId
     */
    @Override
    @Transactional
    public void summaryMeeting(String summaryContent, Long meetingId) {
        Meeting meeting = meetingRepository.getOne(meetingId);
        meeting.setSummarized(true);
        meeting.setSummaryContent(summaryContent);
        meetingRepository.save(meeting);
    }
}
