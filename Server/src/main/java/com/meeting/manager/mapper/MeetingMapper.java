package com.meeting.manager.mapper;


import com.meeting.manager.model.Meeting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeetingMapper {

	List<Meeting> findAll(Meeting query);

	Long count();

}
