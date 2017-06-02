package com.hongchao.cms.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhijunhu on 2017/6/2.
 */
public class FileUtil {
    public static boolean uploadFile(InputStream is, String path, String name)
    {
        boolean result = true;
        try
        {
            File f = new File(path);
            if (!f.exists())
            {
                f.mkdirs();
            }
            java.io.OutputStream os = new java.io.FileOutputStream(path + name);
            byte buffer[] = new byte[2048];
            int count = 0;
            while ((count = is.read(buffer)) > 0)
            {
                os.write(buffer, 0, count);
            }
            os.close();
            is.close();
        }
        catch (FileNotFoundException e)
        {
            result = false;
            e.printStackTrace();
        }
        catch (IOException e)
        {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
