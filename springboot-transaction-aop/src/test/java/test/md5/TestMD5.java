package test.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.util.DigestUtils;

public class TestMD5 {

	public static void main(String[] args) {
		File file1 = new File("C:\\Users\\Yan\\Desktop\\证书\\冻结环境\\10.10.1.65\\serverstore.jks");
		File file2 = new File("D:\\program\\develop\\Java\\jdk1.7.0_75\\bin\\serverstore.jks");
		File file3 = new File("C:\\Users\\Yan\\Desktop\\wangcheng-serverstore\\serverstore.jks");
		File file4 = new File("D:\\program\\develop\\Java\\jdk1.5.0_22\\bin\\serverstore.jks");
		
		String md5Hex = null;
		try {
			md5Hex = DigestUtils.md5DigestAsHex(new FileInputStream(file1));
			System.out.println(md5Hex);
			
			md5Hex = DigestUtils.md5DigestAsHex(new FileInputStream(file2));
			System.out.println(md5Hex);
			
			md5Hex = DigestUtils.md5DigestAsHex(new FileInputStream(file3));
			System.out.println(md5Hex);
			
			md5Hex = DigestUtils.md5DigestAsHex(new FileInputStream(file4));
			System.out.println(md5Hex);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
