package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mysite.vo.boardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<boardVo> getlist(int endNum,int startNum) {
		Map<String, Object> pageMap=new HashMap<String,Object>();
		pageMap.put("endNum", endNum);
		pageMap.put("startNum", startNum);
		return sqlSession.selectList("board.getlist",pageMap);
	}
	
	public int gettotalcount() {
		return sqlSession.selectOne("board.gettotalcount");
	}
	
	public boardVo getboard(boardVo boardvo) {
		return sqlSession.selectOne("board.getboard", boardvo);
	}
		
	public int insert(boardVo boardvo) {
		return sqlSession.insert("board.insert", boardvo);
	}
	
	public int update(boardVo boardvo) {
		return sqlSession.update("board.update", boardvo);
	}
	
	public int delete(int num) {
		return sqlSession.delete("board.delete", num);
	}
	
	public int count(boardVo boardvo) {
		return sqlSession.update("board.count", boardvo);
	}
	
	public int searchcount(String name) {
		Map<String, Object> pageMap=new HashMap<String,Object>();
		pageMap.put("name", '%'+name+'%');
		return sqlSession.selectOne("board.searchcount",pageMap);
	}
	
	public List<boardVo> search(String name,int startNum,int endNum) {
		Map<String, Object> pageMap=new HashMap<String,Object>();
		pageMap.put("endNum", endNum);
		pageMap.put("startNum", startNum);
		pageMap.put("name", '%'+name+'%');
		List<boardVo> list=sqlSession.selectList("board.search", pageMap);
		return list;
	}

}
