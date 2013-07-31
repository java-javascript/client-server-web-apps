package com.oreilly.jython;

import java.io.File;
import java.io.IOException;
import org.python.util.PythonInterpreter;
import org.apache.commons.io.FileUtils;

public class Server 
{
    public static void main( String[] args ) throws IOException
    {
		new PythonInterpreter().exec(
			x`FileUtils.readFileToString(new File("python/http_server.py"))
		);
    }
}
