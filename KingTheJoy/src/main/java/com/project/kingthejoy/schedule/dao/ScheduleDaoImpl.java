package com.project.kingthejoy.schedule.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.schedule.dto.ScheduleDto;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	String NAMESPACE = "schedule.";

	@Override
	public List<ScheduleDto> selectAll(int school_seq) {
		List<ScheduleDto> selectSchedule = sqlSessionTemplate.selectList(NAMESPACE+"selectAll",school_seq);
		System.out.println("selectAll DaoImpl ::::::::"+selectSchedule);
		return selectSchedule;

	}

	@Override
	public ScheduleDto selectOne(int schedule_seq) {
		ScheduleDto scheduleOne = sqlSessionTemplate.selectOne(NAMESPACE+"selectOne",schedule_seq);
		System.out.println("selectOne DaoImpl ::::::::::"+scheduleOne);
		return scheduleOne;

	}

	@Override
	public int scheduleInsert(ScheduleDto scheduleDto) {
		int insertRes = sqlSessionTemplate.insert(NAMESPACE+"insert",scheduleDto);
		System.out.println("입력결과"+insertRes);
		return insertRes;

	}

	@Override
	public int scheduleUpdate(ScheduleDto scheduleDto) {
		int updateRes = sqlSessionTemplate.update(NAMESPACE+"update",scheduleDto);
		System.out.println("수정결과"+updateRes);
		return updateRes;

	}

	@Override
	public int scheduleDelete(int schedule_seq) {
		int res = sqlSessionTemplate.delete(NAMESPACE+"delete",schedule_seq);
		return res;

	}

}

