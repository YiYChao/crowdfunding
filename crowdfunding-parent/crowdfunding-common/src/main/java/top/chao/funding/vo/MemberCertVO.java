package top.chao.funding.vo;

import org.springframework.web.multipart.MultipartFile;

import top.chao.funding.bean.TMemberCert;

public class MemberCertVO extends TMemberCert{
    
    private MultipartFile imgfile;
    
    private String certname;	// 资质名称

	public MultipartFile getImgfile() {
		return imgfile;
	}

	public void setImgfile(MultipartFile imgfile) {
		this.imgfile = imgfile;
	}

	public String getCertname() {
		return certname;
	}

	public void setCertname(String certname) {
		this.certname = certname;
	}
}