package com.mybatis.generator;
import org.mybatis.generator.api.ShellRunner;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 args = new String[] { "-configfile", "src\\main\\resources\\generator.xml", "-overwrite" };
	        ShellRunner.main(args);
	}
	
}
