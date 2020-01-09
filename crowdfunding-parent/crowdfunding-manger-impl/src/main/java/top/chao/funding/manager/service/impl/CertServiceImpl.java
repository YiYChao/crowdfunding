package top.chao.funding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TCert;
import top.chao.funding.bean.TCertExample;
import top.chao.funding.exception.LoginFailException;
import top.chao.funding.manager.dao.TCertMapper;
import top.chao.funding.manager.service.CertService;
/**
 * @ClassName: CertServiceImpl  
 * @Description: 资质相关的接口实现 
 * @author: YiYChao
 * @date 2020年1月9日 下午5:00:25
 */
@Service
public class CertServiceImpl implements CertService{

	@Autowired
	private TCertMapper tCertMapper;
	
	@Override
	public List<TCert> queryAllList() {
		TCertExample example = new TCertExample();
		return tCertMapper.selectByExample(example);
	}

	@Override
	public int insertCert(String name) {
		TCert record = new TCert();
		record.setName(name);
		int rst = tCertMapper.insert(record);
		return rst;
	}

	@Override
	public TCert queryCertById(Integer id) {
		TCert cert = tCertMapper.queryCertById(id);
		if(cert == null) {
			throw new LoginFailException("资质信息有误！");
		}
		return cert;
	}

	@Override
	public int updateCert(TCert cert) {
		return tCertMapper.updateCert(cert);
	}

	@Override
	public int deleteCertById(Integer id) {
		return tCertMapper.deleteCertById(id);
	}

}
