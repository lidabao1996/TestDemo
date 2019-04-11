package others;

/*import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;*/

public class Test {
	
	private String vipInfo;
	private static final int HANDLER_MSG_VIPINFO = 1;
	private static final String VIPINFO = "vipInfo";
	/*Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_MSG_VIPINFO:
				
				Bundle bundle = msg.getData();
				String vipInfo = bundle.getString(VIPINFO);
				//更新view,以及其他信息...
				
				break;

			default:
				break;
			}
		}
	};
	
	public void uploadUserInfo(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//网络请求 
				String vipInfo = VIPSyncApi.getVipExpire();
				
				Message msg = new Message();
				Bundle bundle = new Bundle();
				bundle.putString(VIPINFO, vipInfo);
				msg.setData(bundle);
				msg.what = HANDLER_MSG_VIPINFO;
				handler.sendMessage(msg);
			}
		});
	}*/
}
