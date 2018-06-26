package com.chris.plt;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.RandomAccess;

public final class $
{
	public static DataInputStream getDataInputStream(String path) throws FileNotFoundException
	{
		DataInputStream inputStream=new DataInputStream(new FileInputStream(path));
		return inputStream;
	}
}
