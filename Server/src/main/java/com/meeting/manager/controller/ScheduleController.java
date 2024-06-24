package com.meeting.manager.controller;


import com.meeting.manager.dao.ScheduleRepository;
import com.meeting.manager.dao.UserRepository;
import com.meeting.manager.mapper.ScheduleMapper;
import com.meeting.manager.model.Schedule;
import com.meeting.manager.model.RoomPage;
import com.meeting.manager.model.User;
import com.meeting.manager.utils.DateUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ScheduleMapper scheduleMapper;

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RoomPage<Schedule> find(@RequestBody Schedule query) {
		query.setIsPage(true);
		RoomPage result = new RoomPage();
		List<Schedule> scheduleList=scheduleMapper.findAll(query);
		result.setList(scheduleList);
		result.setTotal(scheduleMapper.count());
		int totalPage=scheduleMapper.count().intValue()/query.getSizePerPage();
		if(scheduleMapper.count().intValue()%query.getSizePerPage()>0){
			totalPage++;
		}
		result.setTotalPages(totalPage);
		return result;
	}

	/**
	 * @function:添加
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/saveSchedule", method = RequestMethod.POST)
	public Schedule saveSchedule(@RequestBody Schedule dataset) {
		dataset.setCreatetime(DateUtil.getSimpleFormatedDateNow());
		return scheduleRepository.save(dataset);
	}


	@GetMapping({"detail/{id}"})
	public Schedule findDetail(@PathVariable("id") String id) {
		Schedule dataset = scheduleRepository.findById(id).orElse(new Schedule());
		User user = userRepository.findById(dataset.getUserid()).orElse(new User());
		dataset.setUsername(user.getRealname());
		dataset.setPhone(user.getPhone());
		return dataset;
	}

	/**
	 * 删除
	 * 
	 * @author  下午12:37:51
	 */
	@RequestMapping(value="/deleteSchedule",method=RequestMethod.POST)
	public JSONObject deleteSchedule(@RequestBody Schedule dataset) {
		JSONObject jo = new JSONObject();
		scheduleRepository.delete(dataset);
		jo.put("success",true);
		return jo;
	}

	/**
	 * @function:更改状态
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public Schedule updateStatus(@RequestBody Schedule dataset) {
		Schedule schedule = scheduleRepository.findById(dataset.getId()).get();
		if(dataset.getStatus().equals("审核未通过")){
			schedule.setRemark(dataset.getRemark());
		}
		schedule.setStatus(dataset.getStatus());
		return scheduleRepository.save(schedule);
	}
}
