package com.chris.plt;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class ByteCodeReader
{
	public static ClassFile read(String path) throws Exception
	{
		ClassFile classFile = new ClassFile();

		DataInputStream dataInputStream = $.getDataInputStream(path);

		//读取魔数 class文件以0xCAFEBABE开头
		int magicNum = dataInputStream.readInt();
		if (magicNum != 0xCAFEBABE)
			$.die("not a standard byte code file");
		classFile.magic = magicNum;

		//读取版本号
		classFile.minorVersion = dataInputStream.readShort();
		classFile.majorVersion = dataInputStream.readShort();
		//读取常量池
		classFile.constantPool = readConstantInfos(dataInputStream, dataInputStream.readShort());
		//读取访问修饰标识
		classFile.accessFlags = ClassFile.AccessFlag.getAccessFlags(dataInputStream.readShort());
		//读取类名称
		classFile.className = getClassName(dataInputStream.readShort(), classFile.constantPool, "class name index error");
		//读取父类名称
		classFile.superClassName = getClassName(dataInputStream.readShort(), classFile.constantPool, "super class name index error");
		//读取实现接口
		classFile.interfaces = readInterfaces(dataInputStream, dataInputStream.readShort(), classFile.constantPool);
		//读取字段
		classFile.fields = readFields(dataInputStream, dataInputStream.readShort(), classFile.constantPool);
		//读取方法
		classFile.methods = readMethods(dataInputStream, dataInputStream.readShort(), classFile.constantPool);

		return classFile;
	}

	/**
	 * 读取常量池
	 *
	 * @param dataInputStream
	 * @param constantPoolCount
	 * @return
	 * @throws IOException
	 */
	private static ConstantInfo[] readConstantInfos(DataInputStream dataInputStream, int constantPoolCount) throws IOException
	{
		ConstantInfo[] constantInfos = new ConstantInfo[constantPoolCount - 1];

		int length;
		int index1;
		int index2;
		Object value;

		for (int i = 1; i < constantPoolCount; i++)
		{
			ConstantInfo constantInfo = null;
			ConstantInfo.ConstantType constantType = ConstantInfo.ConstantType.getTypeEnum(dataInputStream.readByte());
			switch (constantType)
			{
				case UTF8:
					length = dataInputStream.readShort();
					byte[] bytes = new byte[length];
					int len = dataInputStream.read(bytes);
					if (len != length)
						$.die("bad class file");
					constantInfo = new ConstantInfo(constantType, new String(bytes, "UTF8"));
					break;
				case Integer:
					value = dataInputStream.readInt();
					constantInfo = new ConstantInfo(constantType, value);
					break;
				case Float:
					value = dataInputStream.readFloat();
					constantInfo = new ConstantInfo(constantType, value);
					break;
				case Long:
					value = dataInputStream.readLong();
					constantInfo = new ConstantInfo(constantType, value);
					break;
				case Double:
					value = dataInputStream.readDouble();
					constantInfo = new ConstantInfo(constantType, value);
					break;
				case Class:
				case String:
					index1 = dataInputStream.readShort();
					constantInfo = new ConstantInfo(constantType, index1);
					break;
				case Fieldref:
				case Methodref:
				case InterfaceMethodref:
				case NameAndType:
					index1 = dataInputStream.readShort();
					index2 = dataInputStream.readShort();
					constantInfo = new ConstantInfo(constantType, index1, index2);
					break;
			}

			constantInfos[i - 1] = constantInfo;
		}

		return constantInfos;
	}

	private static String getClassName(int index, ConstantInfo[] constantPool, String message)
	{
		ConstantInfo constantInfo = constantPool[index - 1];
		if (constantInfo.getType() != ConstantInfo.ConstantType.Class)
			$.die(message);
		constantInfo = constantPool[constantInfo.getIndex1() - 1];
		if (constantInfo.getType() != ConstantInfo.ConstantType.UTF8)
			$.die(message);
		return (String) constantInfo.getValue();
	}

	private static String getUtf8(int index, ConstantInfo[] constantPool, String message)
	{
		ConstantInfo constantInfo = constantPool[index - 1];
		if (constantInfo.getType() != ConstantInfo.ConstantType.UTF8)
			$.die(message);
		return (String) constantInfo.getValue();
	}

	private static String[] readInterfaces(DataInputStream dataInputStream, int interfaceCount, ConstantInfo[] constantPool) throws IOException
	{
		String[] interfaces = new String[interfaceCount];

		for (int i = 0; i < interfaceCount; i++)
			interfaces[i] = getClassName(dataInputStream.readShort(), constantPool, "interface name index error");

		return interfaces;
	}

	private static FieldInfo[] readFields(DataInputStream dataInputStream, int fieldCount, ConstantInfo[] constantPool) throws IOException
	{
		FieldInfo[] fieldInfos = new FieldInfo[fieldCount];
		for (int i = 0; i < fieldCount; i++)
		{
			List<AccessFlag> accessFlags = AccessFlag.getAccessFlags(dataInputStream.readShort());
			String fieldName = getUtf8(dataInputStream.readShort(), constantPool, "field descriptor index error");
			String descriptorName = getUtf8(dataInputStream.readShort(), constantPool, "field descriptor index error");

			int attributeCount = dataInputStream.readShort();
			for (int j = 0; j < attributeCount; j++)
			{
				dataInputStream.readShort();
				//TODO:读取属性表
			}

			fieldInfos[i] = new FieldInfo(accessFlags, fieldName, descriptorName);
		}

		return fieldInfos;
	}

	private static MethodInfo[] readMethods(DataInputStream dataInputStream, int methodCount, ConstantInfo[] constantPool) throws IOException
	{
		MethodInfo[] methodInfos = new MethodInfo[methodCount];
		for (int i = 0; i < methodCount; i++)
		{
			List<AccessFlag> accessFlags = AccessFlag.getAccessFlags(dataInputStream.readShort());
			String methodName = getUtf8(dataInputStream.readShort(), constantPool, "method name index error");
			String descriptorName = getUtf8(dataInputStream.readShort(), constantPool, "method descriptor index error");

			int attrituteCount=dataInputStream.readShort();

		}

		return methodInfos;
	}
}
