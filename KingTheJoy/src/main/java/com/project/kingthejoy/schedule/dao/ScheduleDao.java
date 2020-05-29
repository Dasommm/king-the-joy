package com.project.kingthejoy.schedule.dao;

import java.util.List;

import com.project.kingthejoy.schedule.dto.ScheduleDto;

public interface ScheduleDao {
	
	//전체 리스트 뽑기
	public List<ScheduleDto> selectAll(int school_seq);
	
	//selectOne
	public ScheduleDto selectOne(int schedule_seq);
	
	//insert
	public int scheduleInsert(ScheduleDto scheduleDto);
	
	//update
	public int scheduleUpdate(ScheduleDto scheduleDto);
	
	//delete
	public int scheduleDelete(int schedule_seq);
	

}
