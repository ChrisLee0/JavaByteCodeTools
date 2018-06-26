package com.chris.plt;

public class CpInfo
{
	public enum ConstantEnum
	{
		UTF8(1), Integer(3), Float(4), Long(5),
		Double(6), Class(7), String(8), Fieldref(9),
		Methodref(10), InterfaceMethodref(11),
		NameAndType(12);

		int value;

		ConstantEnum(int value)
		{
			this.value = value;
		}

		public static ConstantEnum getTypeEnum(int value)
		{
			for (ConstantEnum constantEnum : ConstantEnum.values())
				if (value == constantEnum.value)
					return constantEnum;
			throw new RuntimeException("constant type not supported");
		}
	}

	private ConstantEnum type;
	private int length;
	private int index1;
	private int index2;
	private byte[] bytes;
}
