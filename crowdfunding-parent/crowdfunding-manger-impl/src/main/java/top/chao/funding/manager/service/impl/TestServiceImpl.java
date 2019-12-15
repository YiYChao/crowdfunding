package top.chao.funding.manager.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.manager.dao.TestDAO;
import top.chao.funding.manager.service.TestService;
@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDAO testDAO;
	
	@Override
	public void insert() {
		Map map = new HashMap();
		map.put("name", "zhangsan");
		testDAO.insert(map);
	}

}
