package com.chris.plt;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class $
{
	public static DataInputStream getDataInputStream(String path)
	{
		try
		{
			return new DataInputStream(new FileInputStream(path));
		}
		catch (FileNotFoundException e)
		{
			$.die("file not found:"+path);
			return null;
		}
	}

	public static void die(String message)
	{
		throw new RuntimeException(message);
	}
}
