package com.meeting.manager.mapper;


import com.meeting.manager.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleMapper {

	List<Schedule> findAll(Schedule query);

	Long count();

}
