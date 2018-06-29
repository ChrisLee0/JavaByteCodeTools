package com.chris.plt;

/**
 * Hello world!
 */
public class App
{
	public static void main(String[] args) throws Exception
	{
		String path = "D:\\bytecodes\\TestClass.class";

		ClassFile classFile = ByteCodeReader.read(path);

		System.out.println(classFile);
	}
}
