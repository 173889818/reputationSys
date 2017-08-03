package cn.ayub.ratingScore.commons.shiro;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import cn.ayub.ratingScore.commons.utils.DigestUtils;

import java.security.PublicKey;

/**
 * shiro密码加密配置
 * @author L.cm
 *
 */
public class PasswordHash implements InitializingBean {
	private String algorithmName;
	private int hashIterations;

	public String getAlgorithmName() {
		return algorithmName;
	}
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
	public int getHashIterations() {
		return hashIterations;
	}
	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasLength(algorithmName, "algorithmName mast be MD5、SHA-1、SHA-256、SHA-384、SHA-512");
	}
	
	public String toHex(Object source, Object salt) {
		//DigestUtils.appendMd5DigestAsHex();
		//System.out.println("密码："+toHex2("123456","admin"));
		String passwordHash=DigestUtils.hashByShiro(algorithmName, source, salt, hashIterations);
		return passwordHash==null?null:passwordHash.toUpperCase();
	}

	public  String toHex2(Object source, Object salt) {
		return DigestUtils.hashByShiro(algorithmName, source, salt, hashIterations).toUpperCase();
	}

	public static void main(String[] args) {
		System.out.print(DigestUtils.hashByShiro("md5", "123456", "admin", 2).toUpperCase());
	}
}
