package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.GuestBookDao;
import com.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	GuestBookDao guestBookDao;
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list= guestBookDao.getList();
		for (GuestBookVo vo : list) {
			vo.setContent(vo.getContent().replace("\r\n", "<br/>"));
		}
		return list;
	}
		
	public int insert(GuestBookVo guestBookVo) {
		return guestBookDao.insert(guestBookVo);
	}
	
	public int delete(int no, String password) {
		return guestBookDao.delete(no, password);
	}
}
