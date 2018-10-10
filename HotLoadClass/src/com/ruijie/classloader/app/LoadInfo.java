package com.ruijie.classloader.app;

import com.ruijie.classloader.MyClassLoader;

public class LoadInfo {
	// �Զ�����������
	private MyClassLoader myClassLoader;

	// ��¼Ҫ���ص����ʱ��� --���ص�ʱ��
	private long loadTime;

	private BaseManager baseManager;

	public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
		this.myClassLoader = myClassLoader;
		this.loadTime = loadTime;
	}

	public void setBaseManager(BaseManager baseManager) {
		this.baseManager = baseManager;
	}

	public MyClassLoader getMyClassLoader() {
		return myClassLoader;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public BaseManager getBaseManager() {
		return baseManager;
	}
}
