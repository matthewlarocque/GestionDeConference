package com.meeting.manager.dao;

import com.meeting.manager.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DisciplineRepository extends JpaRepository<Discipline,String> {
}
