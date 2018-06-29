package com.chris.plt;

import java.util.ArrayList;
import java.util.List;

public class ClassFile
{

	int magic;
	short minorVersion;
	short majorVersion;
	ConstantInfo[] constantPool;

	List<AccessFlag> accessFlags;

	String className;
	String superClassName;
	String[] interfaces;
	FieldInfo[] fields;
	MethodInfo[] methods;



	public enum AccessFlag
	{
		ACC_PUBLIC(0x0001),
		ACC_FINAL(0x0010),
		ACC_SUPER(0x0020),
		ACC_INTERFACE(0x0200),
		ACC_ABSTRACT(0x0400),
		ACC_SYNTHETIC(0x1000),
		ACC_ANNOTATION(0x2000),
		ACC_ENUM(0x4000);


		int value;

		AccessFlag(int value)
		{
			this.value = value;
		}

		public static List<AccessFlag> getAccessFlags(int value)
		{
			List<AccessFlag> accessFlags=new ArrayList<>();
			for(AccessFlag accessFlag : AccessFlag.values())
				if((value & accessFlag.value) !=0)
					accessFlags.add(accessFlag);
			return accessFlags;
		}
	}
}
