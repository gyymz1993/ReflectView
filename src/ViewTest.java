import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.OnClickListener;
import com.OnClickListenerProxy;
import com.View;

public class ViewTest {
	
	public static void main(String[] args) {
			View view=new View();
			view.setmOnClickListener(new OnClickListener() {
				@Override
				public void onClick() {
					System.out.println("我被点击");
				}
			});
		try {
			Class clazView=Class.forName("com.View");
			//获取View类中getListenerInfo方法
			Method methodListenerInfo=clazView.getDeclaredMethod("getListenerInfo");
			if(!methodListenerInfo.isAccessible()){
				methodListenerInfo.setAccessible(true);
			}
			/*调用*/
            Object listenerInfoObj  = methodListenerInfo.invoke(view);
			System.out.println("clazView   :"+clazView.newInstance());
			System.out.println("listenerInfoObj  :"+listenerInfoObj);
            /*得到 static class ListenerInfo类 */
            Class listenerInfoClazz = Class.forName("com.View$ListenerInfo");
            /*得到View$ListenerInfo类中mOnClickListener属性*/
            Field mOnClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");
            if (!mOnClickListenerField.isAccessible()){
            	mOnClickListenerField.setAccessible(true);
            }
            System.out.println("mOnClickListenerField  "+mOnClickListenerField);
            OnClickListener mOnClickListener = (OnClickListener) mOnClickListenerField.get(listenerInfoObj);
            /*使用自定义代理方法*/
            System.err.println("mOnClickListener   :"+mOnClickListener);
            OnClickListener mOnClickListenerProxy=new OnClickListenerProxy(mOnClickListener);
            System.out.println("mOnClickListenerProxy    :"+mOnClickListenerProxy);
            //更换
            mOnClickListenerField.set(listenerInfoObj, mOnClickListenerProxy);
			
			
        
    		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
