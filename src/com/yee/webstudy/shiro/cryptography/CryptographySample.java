package com.yee.webstudy.shiro.cryptography;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本类测试了Shiro自带的加密与解密机制。
 * 
 * @author Roger.Yee
 */
public class CryptographySample
{
	private static Logger logger = LoggerFactory.getLogger(CryptographySample.class);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CryptographySample sample = new CryptographySample();

		sample.testBase64();
		sample.testHex();
		sample.testMd5();
	}

	public void testBase64()
	{
		logger.debug("### testBase64 ###");

		String str = "hello";
		String base64Encoded = Base64.encodeToString(str.getBytes());
		String str2 = Base64.decodeToString(base64Encoded);

		logger.debug("Orignial String = " + str);
		logger.debug("After encode String = " + base64Encoded);
		logger.debug("After decode String = " + str2);
	}

	public void testHex()
	{
		logger.debug("### testBase64 ###");

		String str = "hello";
		String base64Encoded = Hex.encodeToString(str.getBytes());
		String str2 = new String(Hex.decode(base64Encoded.getBytes()));

		logger.debug("Orignial String = " + str);
		logger.debug("After encode String = " + base64Encoded);
		logger.debug("After decode String = " + str2);
	}

	public void testMd5()
	{
		logger.debug("### testMd5 ###");

		String str = "hello";
		String salt = "123";
		String md5 = new Md5Hash(str, salt).toString();// 还可以转换为 toBase64()/toHex()

		logger.debug("Orignial String = " + str + ", salt = " + salt);
		logger.debug("After encode md5 = " + md5);
	}

	public void testHash()
	{
		logger.debug("### testSha1 ###");

		String str = "hello";
		String salt = "123";
		String sha1 = new Sha1Hash(str, salt).toString();
		// String sha1 = new Sha256Hash(str, salt).toString();
		// String sha1 = new Sha384Hash(str, salt).toString();
		// String sha1 = new Sha512Hash(str, salt).toString();
		// String sha1 = new SimpleHash("SHA-1", str, salt).toString(); // 其内部使用了Java 的MessageDigest实现。

		logger.debug("Orignial String = " + str + ", salt = " + salt);
		logger.debug("After encode = " + sha1);

	}

}
