package com.fise.xw.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fise.xw.R;
import com.fise.xw.DB.entity.GroupEntity;
import com.fise.xw.config.DBConstant;
import com.fise.xw.config.IntentConstant;
import com.fise.xw.imservice.event.GroupEvent;
import com.fise.xw.imservice.event.UserInfoEvent;
import com.fise.xw.imservice.manager.IMContactManager;
import com.fise.xw.imservice.service.IMService;
import com.fise.xw.imservice.support.IMServiceConnector;
import com.fise.xw.ui.adapter.NewGroupAdspter;
import com.fise.xw.ui.base.TTBaseFragmentActivity;
import com.fise.xw.utils.IMUIHelper;

import de.greenrobot.event.EventBus;


/**
 *  选择群列表界面
 * @author weileiguan
 *
 */
public class SelectGroupListActivity extends TTBaseFragmentActivity {

	private ListView listView = null;
	private SelectGroupListActivity activity;
	private NewGroupAdspter adapter;
	private static IMService imService;
	private IMContactManager contactMgr;
	private List<GroupEntity> groupList;
	private boolean groupWei;
	
	private IMServiceConnector imServiceConnector = new IMServiceConnector() {
		@Override
		public void onIMServiceConnected() {
			logger.d("config#onIMServiceConnected");
			imService = imServiceConnector.getIMService();
			if (imService == null) {
				// 后台服务启动链接失败
				return;
			}

			if(groupWei){
				groupList = imService.getGroupManager().getNormalWeiGroupList();
			}else{ 
				groupList = imService.getGroupManager().getNormalGroupSortedList();
			}

			adapter = new NewGroupAdspter(activity, groupList, imService);
			listView.setAdapter(adapter);

			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					Object object = groupList.get(arg2);
					if (object instanceof GroupEntity) {
						GroupEntity groupEntity = (GroupEntity) object;
						IMUIHelper.openChatActivity(
								SelectGroupListActivity.this,
								groupEntity.getSessionKey());
					}

				}
			});

		}

		@Override
		public void onServiceDisconnected() {
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tt_activity_select_group);
		imServiceConnector.connect(this);
		Intent intent = this.getIntent();
		 
		groupWei =  intent.getBooleanExtra(IntentConstant.KEY_SESSION_SELETC_GROUP, false); 
		listView = (ListView) findViewById(R.id.list_group);

		activity = this;
		EventBus.getDefault().register(this);

		TextView weiwang = (TextView) findViewById(R.id.weiwang);
		weiwang.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SelectGroupListActivity.this.finish();
			}
		});

		Button icon_arrow = (Button) findViewById(R.id.icon_arrow);
		icon_arrow.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SelectGroupListActivity.this.finish();
			}
		});

	}

	private void renderGroupList() {

		
		if(groupWei){
			groupList = imService.getGroupManager().getNormalWeiGroupList();
		}else{ 
			groupList = imService.getGroupManager().getNormalGroupSortedList();
		}
//		groupList = imService.getGroupManager()
//				.getNormalGroupSortedList();
		// 没有任何的联系人数据
		adapter.putGroupList(groupList);
	}

	public void onEventMainThread(GroupEvent event) {
		switch (event.getEvent()) {

		case CREATE_GROUP_OK:
		case CHANGE_GROUP_DELETE_SUCCESS: {
			renderGroupList(); 
			}
			break;

		}
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);
		imServiceConnector.disconnect(this);
		super.onDestroy();
	}
}
