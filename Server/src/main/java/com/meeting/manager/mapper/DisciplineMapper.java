package com.meeting.manager.mapper;


import com.meeting.manager.model.Discipline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DisciplineMapper {

	List<Discipline> findAll(Discipline query);

	Long count();

}
