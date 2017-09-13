package com.fise.xiaoyu.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fise.xiaoyu.DB.entity.UserEntity;
import com.fise.xiaoyu.R;
import com.fise.xiaoyu.imservice.event.UserInfoEvent;
import com.fise.xiaoyu.imservice.manager.IMContactManager;
import com.fise.xiaoyu.imservice.manager.IMLoginManager;
import com.fise.xiaoyu.imservice.service.IMService;
import com.fise.xiaoyu.imservice.support.IMServiceConnector;
import com.fise.xiaoyu.protobuf.IMBaseDefine.ChangeDataType;
import com.fise.xiaoyu.ui.base.TTBaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 *  登录的个人 个性签名界面
 */
public class LoginInfoSigned extends   TTBaseActivity {
	private  static IMService imService;
	private UserEntity loginInfo;
	private IMLoginManager imLoginManager = IMLoginManager.instance();
	private ImageView female_right;
	private ImageView man_right;
	private EditText signed_name;
	private TextView max_lengthTh;
	//问题最大字数
	private int num = 30;
	
	 private IMServiceConnector imServiceConnector = new IMServiceConnector(){
	        @Override
	        public void onIMServiceConnected() {
	            logger.d("config#onIMServiceConnected");
	            imService = imServiceConnector.getIMService();
				if (imService == null) {
					// 后台服务启动链接失败
					return ;
				}
				   
	        }
	        @Override
	        public void onServiceDisconnected() {

	        }
	    };
	 
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
  
		setContentView(R.layout.tt_activity_set_signature);
		 
	    imServiceConnector.connect(this);

	    max_lengthTh  =(TextView)findViewById(R.id.max_length);  
	    
	    
		TextView left_text =(TextView)findViewById(R.id.left_text);  
		left_text.setOnClickListener(new View.OnClickListener() {

	         public void onClick(View v) { 
	        	 LoginInfoSigned.this.finish();
	         } 
         });
		
		TextView right_text =(TextView)findViewById(R.id.right_text);  
		right_text.setOnClickListener(new View.OnClickListener() {

	         public void onClick(View v) { 
	        	 UserEntity user =  IMLoginManager.instance().getLoginInfo();
	        	 String data = signed_name.getText().toString(); 
	        	 IMContactManager.instance().ChangeUserInfo(user.getPeerId(),ChangeDataType.CHANGE_USERINFO_SIGNINFO,data);

	         } 
         });
		           
		
	   signed_name =(EditText)findViewById(R.id.signed_name);   
        		 
		signed_name.addTextChangedListener(new TextWatcher() {
		    private CharSequence temp;
		    private int selectionStart;
		    private int selectionEnd;

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		    }

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        temp = s;
		    }

		    @Override
		    public void afterTextChanged(Editable s) {
		    	
		        int number = num - s.length();
		        max_lengthTh.setText(number+"");
		        selectionStart = signed_name.getSelectionStart();
		        selectionEnd = signed_name.getSelectionEnd();
		        //删除多余输入的字（不会显示出来）
		        if (temp.length() > num) {
		            s.delete(selectionStart - 1, selectionEnd);
		            signed_name.setText(s);
		        }
		        //设置光标在最后
		       // signed_name.setSelection(s.length());
		    }
		});
		
		
		Button icon_arrow =(Button)findViewById(R.id.icon_arrow);  
		icon_arrow.setOnClickListener(new View.OnClickListener() {

	         public void onClick(View v) { 
	        	 LoginInfoSigned.this.finish();
	         } 
         });
		
		
		loginInfo = imLoginManager.getLoginInfo(); 
		signed_name.setText(loginInfo.getSign_info());
	
	}

	
	  @Subscribe(threadMode = ThreadMode.MAIN)
	  public void onMessageEvent(UserInfoEvent event){
	        switch (event){ 
	        
	        case USER_INFO_DATA_UPDATE:  
	        	LoginInfoSigned.this.finish();
	        	break; 
	                
	        }
	    }
	   
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		   	       imServiceConnector.disconnect(this);
	       super.onDestroy();
	}

}
