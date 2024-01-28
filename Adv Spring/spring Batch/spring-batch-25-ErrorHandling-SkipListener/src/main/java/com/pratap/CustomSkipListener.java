package com.pratap;

import org.springframework.batch.core.SkipListener;

public class CustomSkipListener implements SkipListener{

	@Override
	public void onSkipInRead(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInWrite(Object item, Throwable t) {
		System.out.println(">> Skipping " +item + "because writing it caused the error "+t.getCause());
	}

	@Override
	public void onSkipInProcess(Object item, Throwable t) {
		System.out.println(">> Skipping " +item + "because processing it caused the error "+t.getCause());
	}

}
