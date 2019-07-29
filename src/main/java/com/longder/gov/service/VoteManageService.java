package com.longder.gov.service;

import com.longder.gov.entity.po.Vote;

import java.util.List;

/**
 * 投片管理的Service
 */
public interface VoteManageService {
    /**
     * 查询所有的投票
     * @param deptId
     * @return
     */
    List<Vote> listAllVote(Long deptId);

    /**
     * 添加一个投票
     * @param vote
     */
    void addOneVote(Vote vote);

    /**
     * 查询获取一个投票（包括选项）
     * @param voteId
     * @return
     */
    Vote getOneVote(Long voteId);

    /**
     * 投票，某个选项值加一
     * @param voteDetailId
     */
    void vote(Long voteDetailId);
}
