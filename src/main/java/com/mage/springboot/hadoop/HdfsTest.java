package com.mage.springboot.hadoop;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class HdfsTest {
    private FileSystem fileSystem;
    private Configuration config;

    @Before
    public void begin() throws IOException {
        config = new Configuration();
        fileSystem = FileSystem.get(config);
    }

    @Test
    public void upload1() throws IOException {
        Path dst = new Path("/tmp/1.dat");
        Path src = new Path("/home/mamh/Downloads/1.dat");
        fileSystem.copyFromLocalFile(src, dst);
    }

    @Test
    public void upload2() throws IOException {
        Path dst = new Path("/tmp/2.dat");
        FileUtil.copy(new File("/home/mamh/Downloads/1.dat"), fileSystem, dst, false, new Configuration());
    }

    @Test
    public void list() throws IOException {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/tmp"));
        for (FileStatus status : fileStatuses) {
            System.out.println(status.getAccessTime() + "  " + status.getPath().toString() + "   " + status.getBlockSize());
        }
    }

    @Test
    public void upload3() throws IOException {
        Path path = new Path("/tmp/seq.dat");
        SequenceFile.Writer writer = SequenceFile.createWriter(fileSystem, config, path, Text.class, Text.class);
        traverseFolder(".", writer);

    }

    public static void traverseFolder(String src, SequenceFile.Writer writer) throws IOException {
        File srcFile = new File(src);
        if (srcFile.exists()) {
            File[] fileArray = srcFile.listFiles();
            if (null == fileArray || fileArray.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File subFile : fileArray) {
                    if (subFile.isDirectory()) {
                        System.out.println("文件夹:" + subFile.getAbsolutePath());
                        traverseFolder(subFile.getAbsolutePath(), writer);
                    } else {
                        System.out.println("文件:" + subFile.getAbsolutePath());
                        Object key = new Text(subFile.getAbsolutePath());
                        Object valule = new Text(FileUtils.readFileToString(subFile));
                        writer.append(key, valule);
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    @Test
    public void download() throws IOException {
        Path path = new Path("/tmp/seq.dat");
        SequenceFile.Reader reader = new SequenceFile.Reader(fileSystem, path, config);
        Writable key = new Text();

        Writable value = new Text();
        while (reader.next(key, value)) {
            System.out.println(key);
        }

    }

    @Test
    public void mkdir() throws IOException {
        Path p = new Path("/tmp");
        fileSystem.mkdirs(p);
    }

    @After
    public void end() throws IOException {
        fileSystem.close();
    }
}
