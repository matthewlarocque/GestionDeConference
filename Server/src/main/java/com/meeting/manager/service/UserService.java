package com.meeting.manager.service;

import com.meeting.manager.model.User;
import com.meeting.manager.dao.UserRepository;
import com.meeting.manager.utils.DateUtil;
import com.meeting.manager.utils.PasswordEncoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
    UserRepository userRepository;

	@Transactional
	public User saveOrUpdate(User zb) {
		zb.setCreatetime(DateUtil.getSimpleFormatedDateNow());
		return userRepository.save(zb);
	}

	public void delete(User dataset){
		userRepository.delete(dataset);
	}

	public JSONArray importUser(MultipartFile file){
		JSONArray objects = new JSONArray();
		JSONObject jsonObject = null;
		// 创建输入工作流
		InputStream is = null;
		try {
			is = file.getInputStream();
			Workbook wb;
			try {
				wb = new XSSFWorkbook(is);
			} catch (Exception ex) {
				wb = new HSSFWorkbook(is);
			}
			Sheet sheet=wb.getSheetAt(0);
			List<User> userList=new ArrayList<>();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				User user=new User();
				if(row.getCell(0)==null|| StringUtils.isEmpty(row.getCell(0).toString())){
					jsonObject = new JSONObject(); // 存放错误提示
					jsonObject.put("index", i + 1);
					jsonObject.put("message", "登录名不能为空");
					objects.add(jsonObject);
				}
				if(row.getCell(1)==null|| StringUtils.isEmpty(row.getCell(1).toString())){
					jsonObject = new JSONObject(); // 存放错误提示
					jsonObject.put("index", i + 1);
					jsonObject.put("message", "角色不能为空");
					objects.add(jsonObject);
				}
				if(row.getCell(2)==null|| StringUtils.isEmpty(row.getCell(2).toString())){
					jsonObject = new JSONObject(); // 存放错误提示
					jsonObject.put("index", i + 1);
					jsonObject.put("message", "密码不能为空");
					objects.add(jsonObject);
				}
				if(row.getCell(3)==null|| StringUtils.isEmpty(row.getCell(3).toString())){
					jsonObject = new JSONObject(); // 存放错误提示
					jsonObject.put("index", i + 1);
					jsonObject.put("message", "工号不能为空");
					objects.add(jsonObject);
				}
				if(row.getCell(4)==null|| StringUtils.isEmpty(row.getCell(4).toString())){
					jsonObject = new JSONObject(); // 存放错误提示
					jsonObject.put("index", i + 1);
					jsonObject.put("message", "员工姓名不能为空");
					objects.add(jsonObject);
				}
				if(objects.size()==0){
					user.setUsername(row.getCell(0).toString());
					user.setRealname(row.getCell(4).toString());
					user.setRole(row.getCell(1).toString());
					user.setCode(row.getCell(3).toString());
					user.setPassword(new PasswordEncoder().encode(row.getCell(2).toString()));
					user.setStatus("1");
					user.setCreatetime(DateUtil.getSimpleFormatedDateNow());
					userList.add(user);
				}
			}
			if(objects.size()>0) {
				return objects;
			}
			userRepository.saveAll(userList);
		} catch (Exception e) {
			jsonObject = new JSONObject(); // 存放错误提示
			jsonObject.put("message", "导入失败，请检查是否存在重复登录名");
			objects.add(jsonObject);
			e.printStackTrace();
		}
		return objects;
	}

	public void deleteAll(List<User> dataset){
		userRepository.deleteAll(dataset);
	}

}
