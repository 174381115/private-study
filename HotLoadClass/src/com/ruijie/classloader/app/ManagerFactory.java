package com.ruijie.classloader.app;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.ruijie.classloader.MyClassLoader;

public class ManagerFactory {
	// �����ȼ�����ļ�����Ϣ
	private static final Map<String, LoadInfo> loadTimeMap = new HashMap<String, LoadInfo>();

	// Ҫ���ص����classpath
	public static final String CLASS_PATH = "F:/zhou/eclipse-workspace/HotLoadClass/bin/";

	// ʵ���ȼ��ص����ȫ����,����+����
	public static final String MY_MANAGER = "com.ruijie.classloader.app.MyManager";

	/**
	 * ��ȡmanager
	 * 
	 * @param className
	 * @return
	 */
	public static BaseManager getManager(String className) {
		File loadFile = new File(CLASS_PATH + className.replace(".", "/") + ".class");
		
		long lastModified = loadFile.lastModified();
		// ������classNameΪkey��loadinfo��Ϣ,֤��û�б�����,��Ҫ������ô�ൽJVM,���¼���,
		if (loadTimeMap.get(className) == null) {
			load(className, lastModified);
			// �������ʱ����仯��,ͬ�����¼�������ൽJVM
		} else if (loadTimeMap.get(className).getLoadTime() != lastModified) {
			load(className, lastModified);
		}
		return loadTimeMap.get(className).getBaseManager();
	}

	public static void load(String className, long lastModified) {
		MyClassLoader myClassLoader = new MyClassLoader(CLASS_PATH);
		Class<?> loadClass = null;
		try {
			loadClass = myClassLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		BaseManager manager = newInstance(loadClass);
		LoadInfo loadInfo = new LoadInfo(myClassLoader, lastModified);
		loadInfo.setBaseManager(manager);
		loadTimeMap.put(className, loadInfo);

	}

	/**
	 * �Է���ķ�ʽ����baseManager�������
	 * 
	 * @param loadClass
	 * @return
	 */
	private static BaseManager newInstance(Class<?> loadClass) {
		try {
			return (BaseManager) loadClass.getConstructor(new Class[] {}).newInstance(new Object[] {});
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
}
