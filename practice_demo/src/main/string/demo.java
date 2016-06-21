/**
 * 
 */
package main.string;

import java.io.UnsupportedEncodingException;

import freemarker.ext.dom.Transform;

/** 
 * @Title: 
 * @Description: 
 * @Team: 技术1部Java开发小组
 * @Author zhuolin ji
 * @Date 2016年6月20日 下午2:08:31
 * @Version V1.0
 * @param <T>   */
public class demo {

	/**
	 * 
	 */
	public demo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		byte[] src= new byte[]{'a','b','c','d'};
		System.out.println(transform(src));;
	}
	private static byte[] transform(byte[] src) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		byte[] str = new String(src,"gbk").getBytes("utf-8");
		return str;
	}
}
