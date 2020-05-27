package com.project.kingthejoy.pill.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.project.kingthejoy.pill.dto.PillDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

@Repository
public class PillDaoImpl implements PillDao {

	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public int insert(PillDto pillDto) {

		int res = 0;

		try {
			res = sqlsession.insert("pill.pillInsert", pillDto);
		} catch (Exception e) {
			System.out.println("error:pill.pillInsert(daoimpl)");
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public List<PillDto> selectList(int school_seq,int to, int from) {

		//System.out.println("school_seq->>"+school_seq);
		//System.out.println("to->>"+to);
		//System.out.println("from->>"+from);
		List<PillDto> list =null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("school_seq", school_seq);
		map.put("to", to);
		map.put("from", from);
		
		try {
			list=sqlsession.selectList("pill.pillSelectList", map);
			System.out.println("daoimpl/list->>"+list);
		} catch (Exception e) {
			System.out.println("error:selectList");
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public List<PillPagingDto> selectPaging(int to, int from) {

		List<PillPagingDto> list = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("to", to);
		map.put("from", from);

		try {
			list = sqlsession.selectList("pill.pillSelectPaging", map);
		} catch (Exception e) {
			System.out.println("error:selectPaging");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int totalPage() {
		int res = 0;

		try {
			res = sqlsession.selectOne("pill.totalPaging");
		} catch (Exception e) {
			System.out.println("error:totalpage");
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public PillDto selectOne(int pill_seq) {

		PillDto dto = null;
		try {
			dto = sqlsession.selectOne("pill.pillSelectOne", pill_seq);
			System.out.println("daoimpl-pillselectone-dto->>" + dto);
		} catch (Exception e) {
			System.out.println("error:pillSelectOne");
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public int delete(int pill_seq) {

		int res = 0;
		try {
			res = sqlsession.delete("pill.pillDelete", pill_seq);
		} catch (Exception e) {
			System.out.println("error:pillDelete");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int mulDelete(String[] pill_seq) {
		int res = 0;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("pill_seqs", pill_seq);
		try {
			res = sqlsession.delete("pill.pillMulDelete", map);
		} catch (Exception e) {
			System.out.println("error:pill.muldelete");
			e.printStackTrace();
		}
		return res;
	}

	

	

}
