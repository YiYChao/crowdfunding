package top.chao.funding.vo;

import org.springframework.web.multipart.MultipartFile;

import top.chao.funding.bean.TMemberCert;

public class MemberCertVO extends TMemberCert{
    
    private MultipartFile imgfile;

	public MultipartFile getImgfile() {
		return imgfile;
	}

	public void setImgfile(MultipartFile imgfile) {
		this.imgfile = imgfile;
	}
    
    
}