package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.ReplyBoardDao;
import com.mysite.vo.PageVo;
import com.mysite.vo.ReplyBoardVo;
import com.mysite.vo.BoardVo;

@Service
public class ReplyBoardService {
	
	@Autowired
	private ReplyBoardDao replyboardao;

	
	public List<ReplyBoardVo> getlist(int currNo,PageVo pageVo) {		
		int endNum=pageVo.getTotalCount()-pageVo.getPageNo()*(pageVo.getCurrNo()-1);
		int startNum=pageVo.getTotalCount()-pageVo.getPageNo()*pageVo.getCurrNo();		
		return replyboardao.getlist(endNum,startNum);
	}
	
	public PageVo getPage(int currNo) {
		int totalCount=replyboardao.gettotalcount();
		PageVo pageVo=new PageVo();
		pageVo.setting(currNo, totalCount);
		return pageVo;
	}
	
	public ReplyBoardVo getboard(ReplyBoardVo replyboardvo,int flag) {
		ReplyBoardVo vo=replyboardao.getboard(replyboardvo);
		if(flag==1) {
			replyboardao.count(replyboardvo);
		}
		vo.setContent(vo.getContent().replace("\r\n", "<br/>"));
		return vo;
	}
	
	public ReplyBoardVo getboardinfo(ReplyBoardVo replyboardvo) {
		ReplyBoardVo vo=replyboardao.getboard(replyboardvo);
		return vo;
	}
	
	public int insert(ReplyBoardVo replyboardvo) {
		return replyboardao.insert(replyboardvo);
	}
	
	public int update(ReplyBoardVo replyboardvo) {
		return replyboardao.update(replyboardvo);
	}
	
	public int delete(int no,int groupNo,int depth,int orderNo) {
		int rn=replyboardao.deletern(no,groupNo,depth);
		System.out.println("deletern 성공");
		String check=replyboardao.deletecheck(rn,groupNo,depth);
		if(check==null) {
			System.out.println("delete null 접근");
			replyboardao.delete2(orderNo, depth, groupNo);
			return replyboardao.delete(orderNo,orderNo+1,groupNo);
		} else {
			
			System.out.println("delete null 아님");
			int orderEnd=replyboardao.deleteinfo(rn,groupNo,depth);
			System.out.println("deleteinfo 성공");
			System.out.println(rn);
			System.out.println(orderNo);
			System.out.println(orderEnd);
			return replyboardao.delete(orderNo,orderEnd,groupNo);	
		}
		
	}
	
	public int count(ReplyBoardVo replyboardvo) {
		return replyboardao.count(replyboardvo);
	}	
	
	public List<ReplyBoardVo> search(int currNo,String name) {
		PageVo pageVo=new PageVo();
		int totalCount=replyboardao.searchcount(name);
		pageVo.setting(currNo, totalCount);
		int endNum=pageVo.getTotalCount()-pageVo.getPageNo()*(pageVo.getCurrNo()-1);
		int startNum=pageVo.getTotalCount()-pageVo.getPageNo()*pageVo.getCurrNo();		
		return replyboardao.search(name,startNum,endNum);
	}
	
	public PageVo searchPage(int currNo,String name) {
		int totalCount=replyboardao.searchcount(name);
		PageVo pageVo=new PageVo();
		pageVo.setting(currNo, totalCount);
		return pageVo;
	}
	
	public ReplyBoardVo inforeply(int no) {
		System.out.println("service");
		return replyboardao.inforeply(no);
	}

	public int reply(ReplyBoardVo replyboardvo) {
		System.out.println("service");
		replyboardao.replyupdate(replyboardvo);
		replyboardao.reply(replyboardvo);		
		return 0;
	}
	
	
	
}
