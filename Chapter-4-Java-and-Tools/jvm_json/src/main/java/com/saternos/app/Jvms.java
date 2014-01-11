package com.saternos.app;

import javax.script.*;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.charset.*;
import java.nio.channels.*;

/**
 * Super minimal - no error handling, tests , etc
 */
public class Jvms {
    public static void main(String[] args) throws Exception {
        for (String fileName : args) {

            if (!fileName.trim().equals("")) {
                File file = new java.io.File(fileName);

                if (!file.exists())
                    fileName = "src/main/resources/scripts/" + fileName;

                String ext = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
                new ScriptEngineManager().getEngineByExtension(ext).eval(new java.io.FileReader(fileName));

            }
        }
    }

    public static String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }
    }

}