package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.ReplyBoardVo;
import com.mysite.vo.BoardVo;

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
	
	public int deletern(int no,int groupNo,int depth) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("no", no);
		map.put("depth", depth);
		map.put("groupNo", groupNo);
		return sqlSession.selectOne("replyboard.deletern", map);
	}
	
	public String deletecheck(int rn,int groupNo,int depth) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("rn", rn);
		map.put("depth", depth);
		map.put("groupNo", groupNo);
		return sqlSession.selectOne("replyboard.deletecheck", map);
	
	}
	
	public int deleteinfo(int rn,int groupNo,int depth) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("rn", rn);
		map.put("depth", depth);
		map.put("groupNo", groupNo);
		return sqlSession.selectOne("replyboard.deleteinfo", map);
	
	}
	public int delete(int orderNo,int orderEnd,int groupNo) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("orderFirst", orderNo);
		map.put("orderEnd", orderEnd);
		map.put("groupNo", groupNo);
		return sqlSession.delete("replyboard.delete", map);
	
	}
	
	public int delete2(int orderNo,int depth,int groupNo) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("orderFirst", orderNo);
		map.put("depth", depth);
		map.put("groupNo", groupNo);
		return sqlSession.delete("replyboard.delete2", map);
	
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
	
	public ReplyBoardVo inforeply(int no) {
		System.out.println("service");
		return sqlSession.selectOne("replyboard.inforeply", no);
	}

	public int reply(ReplyBoardVo replyboardvo) {
		System.out.println("dao");
		return sqlSession.insert("replyboard.reply",replyboardvo);
	}
	
	public int replyupdate(ReplyBoardVo replyboardvo) {
		System.out.println("dao");
		sqlSession.update("replyboard.replyupdate",replyboardvo);
		return sqlSession.update("replyboard.replyupdate",replyboardvo);
	}
	
	
	
}
