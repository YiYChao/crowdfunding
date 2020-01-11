package top.chao.funding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TAccountTypeCert;
import top.chao.funding.bean.TCert;
import top.chao.funding.manager.dao.TAccountTypeCertMapper;
import top.chao.funding.manager.service.AccTypeCertService;

/**
 * @ClassName: AccTypeCertServiceImpl  
 * @Description: 用户类型资质关系相关接口实现 
 * @author: YiYChao
 * @date 2020年1月10日 下午4:52:29
 */
@Service
public class AccTypeCertServiceImpl implements AccTypeCertService{

	@Autowired
	private TAccountTypeCertMapper tAccountTypeCertMapper;
	
	@Override
	public int insertAccTypeCert(TAccountTypeCert actypcert) {
		return tAccountTypeCertMapper.insert(actypcert);
	}

	@Override
	public int deleteAccTypeCert(TAccountTypeCert actypcert) {
		return tAccountTypeCertMapper.deleteByAccTypeCert(actypcert);
	}

	@Override
	public List<TAccountTypeCert> queryAll() {
		return tAccountTypeCertMapper.queryAll();
	}

	@Override
	public List<TCert> queryCertNeeded(String accttype) {
		return tAccountTypeCertMapper.queryCertNeeded(accttype);
	}

}
