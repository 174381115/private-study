package com.ruijie.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {
	// 要加载的java类的classpath路径
	private String classPath;

	public MyClassLoader(String classPath) {
		super(ClassLoader.getSystemClassLoader());
		this.classPath = classPath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = this.loadClassData(name);
		
		if(data == null) {
			System.out.println("未找到类文件");
			throw new ClassNotFoundException("FileName: " + name);
		}
		
		return defineClass(name, data, 0, data.length);
	}

	private byte[] loadClassData(String name) {
		try {
			name = name.replace(".", "/");
			FileInputStream is = new FileInputStream(new File(classPath + name + ".class"));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int b = 0;
			while ((b = is.read()) != -1) {
				bos.write(b);
			}
			is.close();
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
