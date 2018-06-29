package com.chris.plt;

import java.io.Serializable;
import java.util.Comparator;

public class TestClass implements Comparator<Integer>,Serializable
{
	public static transient final String testField="TEST";
	private  String name="Name";
	private Integer value;

	public int compare(Integer o1, Integer o2)
	{
		return 0;
	}

	enum Week
	{
		Monday,
		Tusday,
		WednesDay,
		Thurday,
		Friday,
		Saturday,
		Sunday
	}

}
