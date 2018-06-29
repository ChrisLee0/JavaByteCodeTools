package com.chris.plt;

import java.util.ArrayList;
import java.util.List;

public class FieldInfo
{
	private List<AccessFlag> accessFlags;
	private String fieldName;
	private String descriptor;

	public FieldInfo(List<AccessFlag> accessFlags, String fieldName, String descriptor)
	{
		this.accessFlags = accessFlags;
		this.fieldName = fieldName;
		this.descriptor = descriptor;
	}
}
