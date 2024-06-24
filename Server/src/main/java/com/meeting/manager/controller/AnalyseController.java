package com.meeting.manager.controller;

import com.meeting.manager.mapper.AnalyseMapper;
import com.meeting.manager.model.Analyse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/analyse")
public class AnalyseController {
    @Autowired
    AnalyseMapper analyseMapper;

    // 统计各会议室预定人数
    @RequestMapping(value="stat_reserve")
    public JSONArray statReserve() {
        JSONArray jsonArray = new JSONArray();
        List<Analyse> analyses = analyseMapper.statReservation();
        for(Analyse analyse : analyses) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", analyse.getVal());
            jsonObject.put("name", analyse.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    // 列出各个会议室的名称
    @RequestMapping(value = "list_chambers")
    public JSONArray listChambers() {
        JSONArray jsonArray = new JSONArray();
        List<Analyse> analyses = analyseMapper.statReservation();
        for(Analyse analyse : analyses) {
            jsonArray.add(analyse.getName());
        }
        return jsonArray;
    }

    // 列出各个会议室对应的总使用时长
    @RequestMapping(value="list_period")
    public JSONArray listPeriod() {
        JSONArray jsonArray = new JSONArray();
        List<Analyse> analyses = analyseMapper.statPeriod();
        for(Analyse analyse : analyses) {
            jsonArray.add(analyse.getVal());
        }
        return jsonArray;
    }

    // 统计各会议室违规次数
    @RequestMapping(value="stat_viobc")
    public JSONArray statVioBC() {
        JSONArray jsonArray = new JSONArray();
        List<Analyse> analyses = analyseMapper.statVioByChamber();
        for(Analyse analyse : analyses) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", analyse.getVal());
            jsonObject.put("name", analyse.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    // 统计每个员工的违规次数
    @RequestMapping(value="stat_viobu")
    public JSONArray statVioBU() {
        JSONArray jsonArray = new JSONArray();
        List<Analyse> analyses = analyseMapper.statVioByUser();
        for(Analyse analyse : analyses) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", analyse.getVal());
            jsonObject.put("name", analyse.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    // 统计每个员工使用所有会议室的次数
    @RequestMapping(value="stat_freq")
    public JSONArray statFreq() {
        JSONArray jsonArray = new JSONArray();
        List<Analyse> analyses = analyseMapper.statFreq();
        for(Analyse analyse : analyses) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", analyse.getVal());
            jsonObject.put("name", analyse.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
