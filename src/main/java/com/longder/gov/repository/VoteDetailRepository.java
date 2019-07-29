package com.longder.gov.repository;

import com.longder.gov.entity.po.VoteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteDetailRepository extends JpaRepository<VoteDetail,Long> {

    /**
     * 根据投票id查询
     * @param voteId
     * @return
     */
    @Query("SELECT D FROM VoteDetail D where D.vote.id=:voteId")
    List<VoteDetail> listByVoteId(@Param("voteId") Long voteId);
}
