package com.meeting.manager.mapper;

import com.meeting.manager.model.Analyse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnalyseMapper {
    List<Analyse> statReservation(); // 统计各会议室预定人数
    List<Analyse> statPeriod(); // 统计各会议室总使用时长
    List<Analyse> statVioByChamber(); // 统计各会议室违规次数
    List<Analyse> statVioByUser(); // 统计每个员工的违规次数
    List<Analyse> statFreq(); // 统计每个员工使用所有会议室的次数
}
