package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.ReplyBoardVo;
import com.mysite.vo.boardVo;

@Repository
public class ReplyBoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<ReplyBoardVo> getlist(int endNum,int startNum) {
		Map<String, Object> pageMap=new HashMap<String,Object>();
		pageMap.put("endNum", endNum);
		pageMap.put("startNum", startNum);
		return sqlSession.selectList("replyboard.getlist",pageMap);
	}
	
	public int gettotalcount() {
		return sqlSession.selectOne("replyboard.gettotalcount");
	}
	
	public ReplyBoardVo getboard(ReplyBoardVo replyboardvo) {
		return sqlSession.selectOne("replyboard.getboard", replyboardvo);
	}
		
	public int insert(ReplyBoardVo replyboardvo) {
		return sqlSession.insert("replyboard.insert", replyboardvo);
	}
	
	public int update(ReplyBoardVo replyboardvo) {
		return sqlSession.update("replyboard.update", replyboardvo);
	}
	
	public int delete(int num) {
		return sqlSession.delete("replyboard.delete", num);
	}
	
	public int count(ReplyBoardVo replyboardvo) {
		return sqlSession.update("replyboard.count", replyboardvo);
	}
	
	public int searchcount(String name) {
		Map<String, Object> pageMap=new HashMap<String,Object>();
		pageMap.put("name", '%'+name+'%');
		return sqlSession.selectOne("replyboard.searchcount",pageMap);
	}
	
	public List<ReplyBoardVo> search(String name,int startNum,int endNum) {
		Map<String, Object> pageMap=new HashMap<String,Object>();
		pageMap.put("endNum", endNum);
		pageMap.put("startNum", startNum);
		pageMap.put("name", '%'+name+'%');
		List<ReplyBoardVo> list=sqlSession.selectList("replyboard.search", pageMap);
		return list;
	}

}
