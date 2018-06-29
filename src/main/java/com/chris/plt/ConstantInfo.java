package com.chris.plt;

public class ConstantInfo
{
	public enum ConstantType
	{
		UTF8(1), Integer(3), Float(4), Long(5),
		Double(6), Class(7), String(8), Fieldref(9),
		Methodref(10), InterfaceMethodref(11),
		NameAndType(12);

		int value;

		ConstantType(int value)
		{
			this.value = value;
		}

		public static ConstantType getTypeEnum(int value)
		{
			for (ConstantType constantType : ConstantType.values())
				if (value == constantType.value)
					return constantType;
			$.die("constant type not supported");
			return null;
		}
	}

	private ConstantType type;
	private int length;
	private int index1;
	private int index2;
	private byte[] bytes;
	private Object value;


	public ConstantInfo(ConstantType type, int index1, int index2)
	{
		this.type = type;
		this.index1 = index1;
		this.index2 = index2;
	}

	public ConstantInfo(ConstantType type, int length, byte[] bytes)
	{
		this.type = type;
		this.length = length;
		this.bytes = bytes;
	}

	public ConstantInfo(ConstantType type, byte[] bytes)
	{
		this.type = type;
		this.bytes = bytes;
	}

	public ConstantInfo(ConstantType type, int index1)
	{
		this.type = type;
		this.index1 = index1;
	}

	public ConstantInfo(ConstantType type, Object value)
	{
		this.type = type;
		this.value = value;
	}

	public ConstantType getType()
	{
		return type;
	}

	public int getLength()
	{
		return length;
	}

	public int getIndex1()
	{
		return index1;
	}

	public int getIndex2()
	{
		return index2;
	}

	public byte[] getBytes()
	{
		return bytes;
	}

	public Object getValue()
	{
		return value;
	}
}
