package utilities;

import java.io.FileInputStream;
import java.io.InputStream;

public class LoadObjectRepository 
{
	public InputStream file;
	
	public LoadObjectRepository(String path) throws Exception
	{
		file = new FileInputStream(path);
	}
}
