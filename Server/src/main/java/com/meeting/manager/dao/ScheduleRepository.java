package com.meeting.manager.dao;

import com.meeting.manager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ScheduleRepository extends JpaRepository<Schedule,String> {
    List<Schedule> findByUserid(String userid);
}
