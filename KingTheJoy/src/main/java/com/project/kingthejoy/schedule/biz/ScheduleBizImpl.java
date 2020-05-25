package com.project.kingthejoy.schedule.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.schedule.dao.ScheduleDao;
import com.project.kingthejoy.schedule.dto.ScheduleDto;

@Service
public class ScheduleBizImpl implements ScheduleBiz {
	
	@Autowired
	ScheduleDao scheduleDao;

	@Override
	public List<ScheduleDto> selectAll(int school_seq) {
		return scheduleDao.selectAll(school_seq);

	}

	@Override
	public ScheduleDto selectOne(int schedule_seq) {
		return scheduleDao.selectOne(schedule_seq);

	}

	@Override
	public int scheduleInsert(ScheduleDto scheduleDto) {
		return scheduleDao.scheduleInsert(scheduleDto);

	}

	@Override
	public int scheduleUpdate(ScheduleDto scheduleDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int scheduleDelete(int schedule_seq) {
		return scheduleDao.scheduleDelete(schedule_seq);

	}

}
