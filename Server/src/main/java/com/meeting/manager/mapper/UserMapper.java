package com.meeting.manager.mapper;


import com.meeting.manager.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {

	List<User> findAll(User query);

	Long count();

}
