package com;

public class View {
    ListenerInfo mListenerInfo;
   // ListTestInfo mListTestInfo;
    ListenerInfo getListenerInfo(){
    	if (mListenerInfo != null) {
              return mListenerInfo;
         }
         mListenerInfo = new ListenerInfo();
         return mListenerInfo;
    }
    
	static class ListenerInfo{
		public OnClickListener mOnClickListener;
	}
	
	public void setmOnClickListener(OnClickListener mOnClickListener) {
		getListenerInfo().mOnClickListener = mOnClickListener;
		callOnClick();
	}
	
    public boolean callOnClick() {
        ListenerInfo li = getListenerInfo();
        if (li != null && li.mOnClickListener != null) {
            li.mOnClickListener.onClick();
            return true;
        }
        return false;
    }

}
