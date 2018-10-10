package com.stream.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class DiskClassLoader extends ClassLoader{

    private String libPath;
    
    public DiskClassLoader(String path) {
        libPath = path;
    }
    
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1) {
            return name + ".class";
        }else {
            return name.substring(index+1) + ".class";
        }
        
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // TODO Auto-generated method stub
        String fileName = getFileName(name);
        
        File file = new File(libPath, fileName);
        
        try {
            FileInputStream is = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            
            int len = 0;
            try {
                while((len = is.read()) != -1) {
                    bos.write(len);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            
            byte[] data = bos.toByteArray();
            is.close();
            bos.close();
            
            return defineClass(name, data, 0, data.length);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return super.findClass(name);
    }
}
