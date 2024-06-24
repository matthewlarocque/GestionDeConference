package com.meeting.manager.controller;

import com.meeting.manager.dao.UserRepository;
import com.meeting.manager.mapper.UserMapper;
import com.meeting.manager.model.RoomPage;
import com.meeting.manager.model.User;
import com.meeting.manager.service.MyUserDetailsService;
import com.meeting.manager.service.UserService;
import com.meeting.manager.utils.TokenUtils;
import com.meeting.manager.utils.PasswordEncoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
    UserRepository userRepository;
	@Autowired
    UserMapper userMapper;
	@Autowired
    MyUserDetailsService userDetailsService;
	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public JSONObject login(@RequestBody User loginUser, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		User user = new User();
		UserDetails loginUserAfter = userDetailsService.loadUserByUsername(loginUser.getUsername());
		if(loginUserAfter==null||!new PasswordEncoder().matches(loginUser.getPassword(), loginUserAfter.getPassword())){
			jsonObject.put("code", 200);
			jsonObject.put("message", "失败");
			return jsonObject;
		}
		user.setUsername(loginUserAfter.getUsername());
		String token = tokenUtils.generateToken(user);
		jsonObject.put("code", 200);
		jsonObject.put("message", "成功");
		jsonObject.put("token", token);
		jsonObject.put("user", loginUserAfter);
		return jsonObject;
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RoomPage<User> find(@RequestBody User query) {
		query.setIsPage(true);
		RoomPage result = new RoomPage();
		List<User> userList=userMapper.findAll(query);
		result.setList(userList);
		result.setTotal(userMapper.count());
		int totalPage=userMapper.count().intValue()/query.getSizePerPage();
		if(userMapper.count().intValue()%query.getSizePerPage()>0){
			totalPage++;
		}
		result.setTotalPages(totalPage);
		return result;
	}

	/**
	 * @function:添加新用户
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public User saveUser(@RequestBody User dataset) {
		if(dataset.getId()==null){
			dataset.setPassword(new PasswordEncoder().encode("111111"));
			return userService.saveOrUpdate(dataset);
		}else{
			User user = userRepository.findById(dataset.getId()).orElse(new User());
			user.setUsername(dataset.getUsername());
			user.setRealname(dataset.getRealname());
			user.setRole(dataset.getRole());
			user.setCode(dataset.getCode());
			return userService.saveOrUpdate(user);
		}
	}

	/**
	 * @function:更改状态
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public User updateStatus(@RequestBody User dataset) {
		User user = userRepository.findById(dataset.getId()).get();
		user.setStatus(dataset.getStatus());
		return userService.saveOrUpdate(user);
	}

	@GetMapping({"detail/{id}"})
	public User findDetail(@PathVariable("id") String id) {
		User dataset = userRepository.findById(id).orElse(new User());
		return dataset;
	}

	@RequestMapping(value = "/findByRole", method = RequestMethod.POST)
	public List<User> findByRole(@RequestBody User dataset) {
		return 	userRepository.findByRole(dataset.getRole());

	}

	/**
	 * 删除
	 *
	 * @author  下午12:37:51
	 */
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	public JSONObject deleteUser(@RequestBody User dataset) {
		JSONObject jo = new JSONObject();
		userService.delete(dataset);
		jo.put("success",true);
		return jo;
	}

	/**
	 * 检查登录名是否存在
	 *
	 * @author  下午12:37:51
	 */
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	public JSONObject checkUser(@RequestBody User dataset) {
		JSONObject jo = new JSONObject();
		User user = userDetailsService.checkUserByUsername(dataset.getUsername());
		if(user!=null&&!user.getId().equals(dataset.getId())){
			jo.put("success",false);
		}else{
			jo.put("success",true);
		}
		return jo;
	}

	@RequestMapping(value = "/importUser", method = RequestMethod.POST)
	public JSONArray importUser(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		JSONArray infos=userService.importUser(file);
		return infos;
	}

	/**
	 * @function:修改联系方式
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
	public User updatePhone(@RequestBody User dataset) {
		User user = userRepository.findById(dataset.getId()).orElse(new User());
		user.setPhone(dataset.getPhone());
		return userService.saveOrUpdate(user);
	}

	/**
	 * @function:修改密码
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public User updatePassword(@RequestBody User dataset) {
		User user = userRepository.findById(dataset.getId()).orElse(new User());
		user.setPassword(new PasswordEncoder().encode(dataset.getPassword()));
		return userService.saveOrUpdate(user);
	}

	/**
	 * @function:获取加密后的密码
	 * @author  下午12:59:46
	 */
	@RequestMapping(value = "/getPassword", method = RequestMethod.POST)
	public String getPassword(@RequestBody JSONObject dataset) {
		return new PasswordEncoder().encode(dataset.getString("oldpassword"));
	}

}
