package com.meeting.manager.controller;


import com.meeting.manager.dao.MeetingRepository;
import com.meeting.manager.dao.UserRepository;
import com.meeting.manager.mapper.MeetingMapper;
import com.meeting.manager.model.Meeting;
import com.meeting.manager.model.RoomPage;
import com.meeting.manager.utils.DateUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/meeting")
public class MeetingController {

	@Autowired
	MeetingRepository meetingRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MeetingMapper meetingMapper;

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RoomPage<Meeting> find(@RequestBody Meeting query) {
		query.setIsPage(true);
		RoomPage result = new RoomPage();
		List<Meeting> scheduleList=meetingMapper.findAll(query);
		result.setList(scheduleList);
		result.setTotal(meetingMapper.count());
		int totalPage=meetingMapper.count().intValue()/query.getSizePerPage();
		if(meetingMapper.count().intValue()%query.getSizePerPage()>0){
			totalPage++;
		}
		result.setTotalPages(totalPage);
		return result;
	}

	/**
	 * @function:添加
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/saveMeeting", method = RequestMethod.POST)
	public Meeting saveMeeting(@RequestBody Meeting dataset) {
		dataset.setCreatetime(DateUtil.getSimpleFormatedDateNow());
		return meetingRepository.save(dataset);
	}

	/**
	 * @function:添加
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/getMeeting", method = RequestMethod.POST)
	public List<Meeting> getMeeting() {
		return meetingRepository.findAll();
	}

	/**
	 * 检查会议室名是否存在
	 *
	 * @author  下午12:37:51
	 */
	@RequestMapping(value="/checkMeeting",method=RequestMethod.POST)
	public JSONObject checkMeeting(@RequestBody Meeting dataset) {
		JSONObject jo = new JSONObject();
		List<Meeting> meetings = meetingRepository.findByName(dataset.getName());
		if(meetings.size()>0&&!meetings.get(0).getId().equals(dataset.getId())){
			jo.put("success",false);
		}else{
			jo.put("success",true);
		}
		return jo;
	}


	/**
	 * 通过id查找会议室
	 *
	 * @author  下午12:37:51
	 */
	@GetMapping({"detail/{id}"})
	public Meeting findDetail(@PathVariable("id") String id) {
		Meeting meeting = meetingRepository.findById(id).orElse(new Meeting());
		return meeting;
	}

	/**
	 * 删除
	 * 
	 * @author  下午12:37:51
	 */
	@RequestMapping(value="/deleteMeeting",method=RequestMethod.POST)
	public JSONObject deleteMeeting(@RequestBody Meeting dataset) {
		JSONObject jo = new JSONObject();
		meetingRepository.delete(dataset);
		jo.put("success",true);
		return jo;
	}

}
