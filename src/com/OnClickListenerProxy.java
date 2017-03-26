package com;

import org.omg.CORBA.OMGVMCID;

public class OnClickListenerProxy implements OnClickListener{

	OnClickListener mOnClickListener;
	public OnClickListenerProxy(OnClickListener onClickListener) {
		this.mOnClickListener=onClickListener;
		onClick();
	}
	@Override
	public void onClick() {
		if(mOnClickListener!=null){
			System.out.println("我被替换了");
			mOnClickListener.onClick();
		}
	}

}
