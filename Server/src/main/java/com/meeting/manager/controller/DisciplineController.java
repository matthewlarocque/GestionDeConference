package com.meeting.manager.controller;

import com.meeting.manager.dao.DisciplineRepository;
import com.meeting.manager.dao.ScheduleRepository;
import com.meeting.manager.dao.UserRepository;
import com.meeting.manager.mapper.DisciplineMapper;
import com.meeting.manager.model.Discipline;
import com.meeting.manager.model.RoomPage;
import com.meeting.manager.model.User;
import com.meeting.manager.utils.DateUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/discipline")
public class DisciplineController {

	@Autowired
    DisciplineRepository disciplineRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DisciplineMapper disciplineMapper;

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RoomPage<Discipline> find(@RequestBody Discipline query) {
		query.setIsPage(true);
		RoomPage result = new RoomPage();
		List<Discipline> scheduleList=disciplineMapper.findAll(query);
		result.setList(scheduleList);
		result.setTotal(disciplineMapper.count());
		int totalPage=disciplineMapper.count().intValue()/query.getSizePerPage();
		if(disciplineMapper.count().intValue()%query.getSizePerPage()>0){
			totalPage++;
		}
		result.setTotalPages(totalPage);
		return result;
	}

	/**
	 * @function:添加
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/saveDiscipline", method = RequestMethod.POST)
	public Discipline saveDiscipline(@RequestBody Discipline dataset) {
		dataset.setCreatetime(DateUtil.getSimpleFormatedDateNow());
		return disciplineRepository.save(dataset);
	}

	@GetMapping({"detail/{id}"})
	public Discipline findDetail(@PathVariable("id") String id) {
		Discipline dataset = disciplineRepository.findById(id).orElse(new Discipline());
		User user = userRepository.findById(dataset.getUserid()).orElse(new User());
		dataset.setRealname(user.getRealname());
		return dataset;
	}

	/**
	 * 删除
	 * 
	 * @author  下午12:37:51
	 */
	@RequestMapping(value="/deleteDiscipline",method=RequestMethod.POST)
	public JSONObject deleteDiscipline(@RequestBody Discipline dataset) {
		JSONObject jo = new JSONObject();
		disciplineRepository.delete(dataset);
		jo.put("success",true);
		return jo;
	}

}
