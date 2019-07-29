package com.longder.gov.service.impl;

import com.longder.gov.entity.po.SysUser;
import com.longder.gov.entity.po.Vote;
import com.longder.gov.entity.po.VoteDetail;
import com.longder.gov.repository.VoteDetailRepository;
import com.longder.gov.repository.VoteRepository;
import com.longder.gov.security.SecurityUtil;
import com.longder.gov.service.VoteManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoteMangeServiceImpl implements VoteManageService {
    @Resource
    private VoteRepository voteRepository;
    @Resource
    private VoteDetailRepository voteDetailRepository;

    /**
     * 查询所有的投票
     *
     * @param deptId
     * @return
     */
    @Override
    public List<Vote> listAllVote(Long deptId) {
        return voteRepository.listByDeptId(deptId);
    }

    /**
     * 添加一个投票
     *
     * @param vote
     */
    @Override
    @Transactional
    public void addOneVote(Vote vote) {
        //处理部门
        SysUser currentUser = SecurityUtil.getCurrentUser();
        assert currentUser != null;
        vote.setDepartment(currentUser.getDepartment());
        //存投票
        voteRepository.save(vote);
        //投票选项
        List<VoteDetail> voteDetailList = new ArrayList<>();
        vote.getOpinions().forEach(opinion->{
            VoteDetail voteDetail = new VoteDetail();
            voteDetail.setVote(vote);
            voteDetail.setMemo(opinion);
            voteDetail.setCount(0);
            voteDetailList.add(voteDetail);
        });
        voteDetailRepository.saveAll(voteDetailList);
    }

    /**
     * 查询获取一个投票（包括选项）
     *
     * @param voteId
     * @return
     */
    @Override
    public Vote getOneVote(Long voteId) {
        Vote vote = voteRepository.getOne(voteId);
        //查选项
        List<VoteDetail> detailList = voteDetailRepository.listByVoteId(voteId);
        vote.setVoteDetailList(detailList);
        return vote;
    }

    /**
     * 投票，某个选项值加一
     *
     * @param voteDetailId
     */
    @Override
    @Transactional
    public void vote(Long voteDetailId) {
        VoteDetail voteDetail = voteDetailRepository.getOne(voteDetailId);
        voteDetail.setCount(voteDetail.getCount()+1);
        voteDetailRepository.save(voteDetail);
    }
}
