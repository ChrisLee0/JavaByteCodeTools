package com.chris.plt;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String path="D:\\Home\\Work\\IDEA\\JavaByteCodeTools\\target\\classes\\com\\chris\\plt\\$.class";

        ClassFile classFile = ByteCodeReader.read(path);

        System.out.println(classFile);
    }
}
