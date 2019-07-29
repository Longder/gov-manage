package com.longder.gov.repository;

import com.longder.gov.entity.po.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Longder
 */
public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
}
