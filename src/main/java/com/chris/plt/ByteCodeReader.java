package com.chris.plt;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ByteCodeReader
{
	public static ClassFile read(String path) throws Exception
	{
		ClassFile classFile=new ClassFile();

		DataInputStream dataInputStream = $.getDataInputStream(path);


		int magicNum = dataInputStream.readInt();
		if(magicNum!=0xCAFEBABE)
			throw new Exception("not a standard byte code file");
		classFile.magic=magicNum;

		classFile.minorVersion=dataInputStream.readShort();
		classFile.majorVersion=dataInputStream.readShort();
		classFile.constantPoolCount=dataInputStream.readShort();

		readCpInfos(dataInputStream,classFile.constantPoolCount);

		return classFile;
	}

	private static List<CpInfo> readCpInfos(DataInputStream dataInputStream,int constantPoolCount) throws IOException
	{
		List<CpInfo> cpInfoList=new ArrayList<>();

		int length;
		int index1;
		int index2;
		byte[] bytes;


		for (int i = 1; i < constantPoolCount; i++)
		{
			byte type=dataInputStream.readByte();

			CpInfo cpInfo=null;

			switch (CpInfo.ConstantEnum.getTypeEnum(type))
			{
				case UTF8:
					break;
				case Integer:
					break;
				case Float:
					break;
				case Long:
					break;
				case Double:
					break;
				case Class:
					break;
				case String:
					break;
				case Fieldref:
					break;
				case Methodref:
					index1=dataInputStream.readShort();
					index2=dataInputStream.readShort();
					cpInfo=
					break;
				case InterfaceMethodref:
					break;
				case NameAndType:
					break;
			}

			cpInfoList.add(cpInfo);
		}

		return cpInfoList;
	}
}
