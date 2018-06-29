package com.chris.plt;

import java.util.ArrayList;
import java.util.List;

public enum AccessFlag
{
	ACC_PUBLIC(0x0001,"public"),
	ACC_PRIVATE(0x0002,"private"),
	ACC_PROTECTED(0x0004,"protected"),
	ACC_STATIC(0x0008,"static"),
	ACC_FINAL(0x0010,"final"),
	ACC_VOILATIE(0x0040,"voilatie"),
	ACC_TRANSIENT(0x0080,"transient"),
	ACC_SYNTHETIC(0x1000,"synthetic"),
	ACC_ENUM(0x4000,"enum");

	int value;
	String name;

	AccessFlag(int value, String name)
	{
		this.value = value;
		this.name = name;
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

