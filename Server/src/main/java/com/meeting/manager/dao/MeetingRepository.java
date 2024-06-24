package com.meeting.manager.dao;

import com.meeting.manager.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MeetingRepository extends JpaRepository<Meeting,String> {
    List<Meeting> findByName(String name);
}
