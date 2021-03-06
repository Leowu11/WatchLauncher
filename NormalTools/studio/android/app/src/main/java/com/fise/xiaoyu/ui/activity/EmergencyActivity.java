package com.fise.xiaoyu.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fise.xiaoyu.DB.entity.DeviceEntity;
import com.fise.xiaoyu.DB.entity.UserEntity;
import com.fise.xiaoyu.DB.entity.WhiteEntity;
import com.fise.xiaoyu.R;
import com.fise.xiaoyu.config.DBConstant;
import com.fise.xiaoyu.config.IntentConstant;
import com.fise.xiaoyu.imservice.event.DeviceEvent;
import com.fise.xiaoyu.imservice.service.IMService;
import com.fise.xiaoyu.imservice.support.IMServiceConnector;
import com.fise.xiaoyu.protobuf.IMDevice.SettingType;
import com.fise.xiaoyu.ui.adapter.WhiteListAdspter;
import com.fise.xiaoyu.ui.base.TTBaseFragmentActivity;
import com.fise.xiaoyu.ui.widget.WhiteDialog.Dialogcallback;
import com.fise.xiaoyu.utils.Logger;
import com.fise.xiaoyu.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置设备的紧急号码界面
 */
public class EmergencyActivity extends TTBaseFragmentActivity {
	private Logger logger = Logger.getLogger(EmergencyActivity.class);
	private IMService imService;
	private int currentUserId;
	List<WhiteEntity> alarmList = new ArrayList<>();
	private AlertDialog myDialog = null;
	private ListView listView = null;
	private WhiteListAdspter adapter;
	private DeviceEntity device;
	private UserEntity currentUser;
    private final int SEL_SOS_NUMBER = 1;
    private final int  RESULT_CODE_DEVICE_WHITE_NUMBER = 1001;
	private IMServiceConnector imServiceConnector = new IMServiceConnector() {
		@Override
		public void onServiceDisconnected() {

		}

		@Override
		public void onIMServiceConnected() {
			logger.d("login#onIMServiceConnected");
			imService = imServiceConnector.getIMService();
			if (imService == null) {
				// 后台服务启动链接失败
				return;
			}

			currentUserId = EmergencyActivity.this.getIntent().getIntExtra(
					IntentConstant.KEY_PEERID, 0);

			if (currentUserId == 0) {
				logger.e("detail#intent params error!!");
				return;
			}
			currentUser = imService.getContactManager().findDeviceContact(
					currentUserId);

			device = imService.getDeviceManager().findDeviceCard(currentUserId);
			if (device == null) {
				return;
			}



			alarmList = imService.getDeviceManager().getAlarmListContactList(
					currentUserId);
			listView = (ListView) findViewById(R.id.list);
            adapter = new WhiteListAdspter(EmergencyActivity.this, alarmList,
                    currentUserId, SettingType.SETTING_TYPE_ALARM_MOBILE, device);
            listView.setAdapter(adapter);
            listView.setOnItemLongClickListener(adapter);

            if (device != null && device.getMasterId() != imService.getLoginManager().getLoginId()) {
                RelativeLayout add_zhang = (RelativeLayout) findViewById(R.id.add_zhanghu);
                add_zhang.setVisibility(View.GONE);
                findViewById(R.id.add_zhanghu_line).setVisibility(View.GONE);
                if(alarmList.size() == 0){
                    findViewById(R.id.show_zhanghu_line).setVisibility(View.GONE);
                }
            }
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		imServiceConnector.connect(EmergencyActivity.this);

		setContentView(R.layout.tt_activity_urgency_list);

		TextView left_text = (TextView) findViewById(R.id.left_text);
		left_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EmergencyActivity.this.finish();
			}
		});
        findViewById(R.id.icon_arrow).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EmergencyActivity.this.finish();
			}
		});
		RelativeLayout add_zhang = (RelativeLayout) findViewById(R.id.add_zhanghu);
		add_zhang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (alarmList.size() >= DBConstant.SESSION_GROUP_ALARM_NUM) {
					Utils.showToast(
							EmergencyActivity.this,
							"紧急号码最多" + DBConstant.SESSION_GROUP_ALARM_NUM + "个");
					return;
				}

//				if(currentUser.getUserType() == IMBaseDefine.ClientType.CLIENT_TYPE_FISE_WATCH_VALUE) {
//					Intent intent = new Intent(EmergencyActivity.this, WhitePhoneNumberSetActivity.class);
//					intent.putExtra(IntentConstant.KEY_PEERID, currentUserId);
//                    intent.putExtra(IntentConstant.SEL_PHONE_NUMBER, SEL_SOS_NUMBER);
//					startActivity(intent);
//				}else if(currentUser.getUserType() == IMBaseDefine.ClientType.CLIENT_TYPE_FISE_DEVICE_VALUE){
                showOperationType();

//				}
//
//				final PassDialog myDialog = new PassDialog(EmergencyActivity.this,PassDialog.PASS_DIALOG_TYPE.PASS_DIALOG_WITHOUT_MESSAGE);
//				myDialog.setTitle("请输入你要添加的号码");//设置标题
//				myDialog.dialog.show();//显示
//				//确认按键回调，按下确认后在此做处理
//				myDialog.setMyDialogOnClick(new PassDialog.MyDialogOnClick() {
//					@Override
//					public void ok() {
//
//						boolean isPhone = Utils.isMobileNO(myDialog.getEditText()
//								.getText().toString());
//						if (isPhone == false) {
//							Utils.showToast(EmergencyActivity.this,
//									"输入的号码不正确");
//
//							return;
//						}
//
//						boolean isExisted = false;
//						alarmList = imService.getDeviceManager()
//								.getAlarmListContactList(currentUserId);
//						for (int i = 0; i < alarmList.size(); i++) {
//							if (alarmList
//									.get(i)
//									.getPhone()
//									.equals(myDialog.getEditText().getText()
//											.toString())) {
//								isExisted = true;
//								break;
//							}
//						}
//
//						if (isExisted) {
//							Utils.showToast(EmergencyActivity.this,
//									"您的输入手机号码已经存在");
//							return;
//						}
//
//						imService.getDeviceManager().settingWhite(
//								currentUserId,
//								myDialog.getEditText().getText().toString(),
//								SettingType.SETTING_TYPE_ALARM_MOBILE,
//								DBConstant.ADD);
//						myDialog.dialog.dismiss();
//
//					}
//				});

			}
		});

	}


	private void showOperationType() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(this,
						android.R.style.Theme_Holo_Light_Dialog));
		String[] items = new String[] { getString(R.string.input_custom),
				getString(R.string.go_contact_list)};
		builder.setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
					case 0:
						Intent intent = new Intent(EmergencyActivity.this, WhitePhoneNumberSetActivity.class);
						intent.putExtra(IntentConstant.KEY_PEERID, currentUserId);
                        intent.putExtra(IntentConstant.SEL_PHONE_NUMBER, SEL_SOS_NUMBER);
						startActivity(intent);

						break;

					case 1:

						Intent intentT = new Intent(EmergencyActivity.this , GroupSelectActivity.class);
						intentT.putExtra(IntentConstant.KEY_PEERID, currentUserId);
                        intentT.putExtra(IntentConstant.SEL_PHONE_NUMBER, SEL_SOS_NUMBER);
						intentT.putExtra(IntentConstant.HIDE_SELECT_CHECK_BOX, true);
                        startActivityForResult(intentT , RESULT_CODE_DEVICE_WHITE_NUMBER);



						break;
				}
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.show();

	}
	/**
	 * 设置mydialog需要处理的事情
	 */
	Dialogcallback dialogcallback = new Dialogcallback() {
		@Override
		public void dialogdo(String string) {

		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();

		imServiceConnector.disconnect(EmergencyActivity.this);
			}

	// 为什么会有两个这个
	// 可能是 兼容性的问题 导致两种方法onBackPressed
	@Override
	public void onBackPressed() {
		logger.d("login#onBackPressed");
		// imLoginMgr.cancel();
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	/**
	 * ----------------------------event 事件驱动----------------------------
	 */
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageEvent(DeviceEvent event) {
		switch (event) {

		case USER_INFO_DELETE_ALARM_SUCCESS:
			Utils.showToast(EmergencyActivity.this, "SOS号码删除成功");
			updateEmergencyList();
			break;

		case USER_INFO_SETTING_DEVICE_SUCCESS:
            Utils.showToast(EmergencyActivity.this, "SOS号码添加成功");
			renderEmergencyList();
			break;
		case USER_INFO_SETTING_DEVICE_FAILED:
			Utils.showToast(EmergencyActivity.this, "SOS号码设置失败");
			break;
		case USER_INFO_UPDATE_INFO_SUCCESS:
			renderEmergencyList();
			break;
			 
		}
	}

	public void renderEmergencyList() {
		alarmList = imService.getDeviceManager().getAlarmListContactList(
				currentUserId);

		// 没有任何的联系人数据
        adapter.putDeviceList(alarmList);
        if (alarmList.size() == 0) {
        if(device.getMasterId() != imService.getLoginManager().getLoginId()){
            findViewById(R.id.show_zhanghu_line).setVisibility(View.GONE);
        }

		}

	}

	public void updateEmergencyList() {
		alarmList = imService.getDeviceManager().getAlarmListContactList(
				currentUserId);

		adapter.updateDeviceList(alarmList);
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CODE_DEVICE_WHITE_NUMBER){
            String phoneNumber = data.getStringExtra("phone_number");
            if(phoneNumber != null){
                boolean isExisted = false;
                for (int i = 0; i < alarmList.size(); i++) {
                    if (alarmList.get(i).getPhone().equals(phoneNumber)) {
                        isExisted = true;
                        break;
                    }

                }
                if (isExisted) {
                    Utils.showToast(EmergencyActivity.this,
                            "您选择的手机号码已经存在");
                }
            }

        }


    }

}
