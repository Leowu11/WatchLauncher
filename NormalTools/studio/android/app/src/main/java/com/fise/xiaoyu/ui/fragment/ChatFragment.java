package com.fise.xiaoyu.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;

import com.fise.xiaoyu.DB.entity.GroupEntity;
import com.fise.xiaoyu.DB.entity.UserEntity;
import com.fise.xiaoyu.R;
import com.fise.xiaoyu.config.DBConstant;
import com.fise.xiaoyu.imservice.entity.RecentInfo;
import com.fise.xiaoyu.imservice.event.GroupEvent;
import com.fise.xiaoyu.imservice.event.LoginEvent;
import com.fise.xiaoyu.imservice.event.ReconnectEvent;
import com.fise.xiaoyu.imservice.event.SessionEvent;
import com.fise.xiaoyu.imservice.event.SocketEvent;
import com.fise.xiaoyu.imservice.event.UnreadEvent;
import com.fise.xiaoyu.imservice.event.UserInfoEvent;
import com.fise.xiaoyu.imservice.manager.IMLoginManager;
import com.fise.xiaoyu.imservice.manager.IMUnreadMsgManager;
import com.fise.xiaoyu.imservice.service.IMService;
import com.fise.xiaoyu.imservice.support.IMServiceConnector;
import com.fise.xiaoyu.ui.activity.MainActivity;
import com.fise.xiaoyu.ui.activity.SearchFriednsActivity;
import com.fise.xiaoyu.ui.adapter.ChatAdapter;
import com.fise.xiaoyu.ui.widget.ListLayoutDialog;
import com.fise.xiaoyu.utils.IMUIHelper;
import com.fise.xiaoyu.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 最近联系人Fragment页
 * 
 */
public class ChatFragment extends MainFragment implements
		OnItemSelectedListener, OnItemClickListener, OnItemLongClickListener {

	private static final int REQUEST_CODE_SCAN = 0x0000;
	private static final String DECODED_CONTENT_KEY = "codedContent";
	private static final String DECODED_BITMAP_KEY = "codedBitmap";

	private ChatAdapter contactAdapter;
	private ListView contactListView;
	private View curView = null;
	//private View noNetworkView;
	private View noChatView;
	//private ImageView notifyImage;
	//private TextView displayView;
	//private ProgressBar reconnectingProgressBar;
	private IMService imService;
	// 是否是手动点击重练。fasle:不显示各种弹出小气泡. true:显示小气泡直到错误出现
	private volatile boolean isManualMConnect = false;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	private IMServiceConnector imServiceConnector = new IMServiceConnector() {

		@Override
		public void onServiceDisconnected() {
			if (EventBus.getDefault().isRegistered(ChatFragment.this)) {
				EventBus.getDefault().unregister(ChatFragment.this);
			}
		}

		@Override
		public void onIMServiceConnected() {
			// TODO Auto-generated method stub
			logger.d("chatfragment#recent#onIMServiceConnected");
			imService = imServiceConnector.getIMService();
			if (imService == null) {
				// why ,some reason
				return;
			}
			// 依赖联系人回话、未读消息、用户的信息三者的状态
			onRecentContactDataReady();
			contactAdapter.setService(imService);
			if (!EventBus.getDefault().isRegistered(ChatFragment.this)) {
				EventBus.getDefault().register(ChatFragment.this);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		closeAutoEventbus();
		super.onCreate(savedInstanceState);
		imServiceConnector.connect(getActivity());
		logger.d("chatfragment#onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle bundle) {
		logger.d("onCreateView");
		if (null != curView) {
			logger.d("curView is not null, remove it");
			((ViewGroup) curView.getParent()).removeView(curView);
		}
		curView = inflater.inflate(R.layout.tt_fragment_chat, topContentView);
		// 多端登录也在用这个view
		//noNetworkView = curView.findViewById(R.id.layout_no_network);
		noChatView = curView.findViewById(R.id.layout_no_chat);
		//reconnectingProgressBar = (ProgressBar) curView
		//		.findViewById(R.id.progressbar_reconnect);
//		displayView = (TextView) curView.findViewById(R.id.disconnect_text);
//		notifyImage = (ImageView) curView.findViewById(R.id.imageWifi);

		super.init(curView);
		initTitleView();// 初始化顶部view
		initContactListView(); // 初始化联系人列表视图
		showProgressBar();// 创建时没有数据，显示加载动画
		return curView;
	}

	/**
	 * @Description 设置顶部按钮
	 */
	private void initTitleView() {
		// 设置标题
		// setTopTitleBold(getActivity().getString(R.string.chat_title_name));
		hideTopTitle();
		setTopChatName(getActivity().getString(R.string.chat_title_name));
	}

	private void initContactListView() {
		contactListView = (ListView) curView.findViewById(R.id.ContactListView);
		contactListView.setOnItemClickListener(this);
		contactListView.setOnItemLongClickListener(this);
		contactAdapter = new ChatAdapter(getActivity(),this);
		contactListView.setAdapter(contactAdapter);

		// this is critical, disable loading when finger sliding, otherwise
		// you'll find sliding is not very smooth
		contactListView.setOnScrollListener(new PauseOnScrollListener(
				ImageLoader.getInstance(), true, true));

		contactListView = (ListView) curView.findViewById(R.id.ContactListView);



		//小雨的功能
		 View lineView = curView.findViewById(R.id.top_bar_split_line);
		 lineView.setVisibility(View.VISIBLE);

		Button addfriend_button = (Button) curView.findViewById(R.id.addfriend_button);
		addfriend_button.setVisibility(View.GONE);


	}

	@Override
	public void onStart() {
		logger.d("chatfragment#onStart");
		super.onStart();
	}

	@Override
	public void onStop() {
		logger.d("chatfragment#onStop");
		super.onStop();
	}

	@Override
	public void onPause() {
		logger.d("chatfragment#onPause");
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		imServiceConnector.disconnect(getActivity());
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	// 这个地方跳转一定要快
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//
//		if (position != 0
//			&&position != 1) {
		if (position != 0) { // postion 0 为网络异常的Item选项

			RecentInfo recentInfo = contactAdapter.getItem(position);
			if (recentInfo == null) {
				logger.e("recent#null recentInfo -> position:%d", position);
				return;
			}
			IMUIHelper.openChatActivity(getActivity(),
					recentInfo.getSessionKey());
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onMessageEvent(SessionEvent sessionEvent) {
		logger.d("chatfragment#SessionEvent# -> %s", sessionEvent);
		switch (sessionEvent) {
		case RECENT_SESSION_LIST_UPDATE:
		case RECENT_SESSION_GRPUP_LIST_UPDATE:
		case RECENT_SESSION_LIST_SUCCESS:
		case SET_SESSION_TOP:
			onRecentContactDataReady();
			break;
		case SET_SESSION_NO_DISTURB:
			onRecentContactDataReady();
			break;
			
		case SET_SESSION_MUTE_TOP: 
			onRecentContactDataReady(); 
			break;

		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onMessageEvent(GroupEvent event) {
		switch (event.getEvent()) {
		case GROUP_INFO_OK:
		case CHANGE_GROUP_MEMBER_SUCCESS:
			onRecentContactDataReady();
			searchDataReady();
			break;

		case GROUP_INFO_UPDATED:
			case USER_GROUP_DELETE_SUCCESS:
		case CHANGE_GROUP_DELETE_SUCCESS:
			onRecentContactDataReady();
			searchDataReady();
			break;
		case SHIELD_GROUP_OK:
			// 更新最下栏的未读计数、更新session
			onShieldSuccess(event.getGroupEntity());
			break;

		case CHANGE_GROUP_NICK_SUCCESS:
			onRecentContactDataReady();
			break;

		case SHIELD_GROUP_FAIL:
		case SHIELD_GROUP_TIMEOUT:
			onShieldFail();
			break;

		case CHANGE_GROUP_MODIFY_FAIL: {
			Utils.showToast(getActivity(), "修改失败");
		}
			break;
		case CHANGE_GROUP_MODIFY_TIMEOUT: {
			Utils.showToast(getActivity(), "修改超时");
		}
			break;
		case CHANGE_GROUP_MODIFY_SUCCESS: {
			onModifyberChangeSuccess(event.getGroupEntity());

		}
			break;
		}
	}

	private void onModifyberChangeSuccess(GroupEntity entity) {

		if (entity == null) {
			return;
		}
		// 更新某个sessionId
		onRecentContactDataReady();

	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onMessageEvent(UnreadEvent event) {
		switch (event.event) {
		case UNREAD_MSG_RECEIVED:
		case UNREAD_MSG_LIST_OK:
		case SESSION_READED_UNREAD_MSG:
			onRecentContactDataReady();
			break;
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onMessageEvent(UserInfoEvent event) {
		switch (event) {
		case USER_INFO_UPDATE:
		case USER_INFO_OK:
			onRecentContactDataReady();
			searchDataReady();
			break;
		case USER_INFO_DATA_UPDATE:
			onRecentContactDataReady();
			searchDataReady();
			break;
		case USER_MUTE_NOTIFICATION: 
			contactAdapter.updateSetData(); 
			break; 
			 

		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onMessageEvent(LoginEvent loginEvent) {
		logger.d("chatfragment#LoginEvent# -> %s", loginEvent);
		switch (loginEvent) {
		case LOCAL_LOGIN_SUCCESS:
		case LOGINING: {
			logger.d("chatFragment#login#recv handleDoingLogin event");
//			if (reconnectingProgressBar != null) {
//				reconnectingProgressBar.setVisibility(View.VISIBLE);
//			}
			contactAdapter.putProgress(true);
		}
			break;

		case LOCAL_LOGIN_MSG_SERVICE:
		case LOGIN_OK: {
			isManualMConnect = false;
			logger.d("chatfragment#loginOk");
			//noNetworkView.setVisibility(View.GONE);
			contactAdapter.putTouch(1,true,false);

			onRecentContactDataReady();
			searchDataReady();
			
		}
			break;

		case LOGIN_AUTH_FAILED:
		case LOGIN_INNER_FAILED: {
			onLoginFailure(loginEvent);
		}
			break;

		case PC_OFFLINE:
		case KICK_PC_SUCCESS:
			onPCLoginStatusNotify(false);
			break;

		case KICK_PC_FAILED:
			Utils.showToast(getActivity(), getString(R.string.kick_pc_failed));
			break;
		case PC_ONLINE:
			onPCLoginStatusNotify(true);
			break;

		default:
			//reconnectingProgressBar.setVisibility(View.GONE);
			contactAdapter.putProgress(false);
			break;
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onMessageEvent(SocketEvent socketEvent) {
		switch (socketEvent) {
		case MSG_SERVER_DISCONNECTED:

			handleServerDisconnected();
			break;

		case CONNECT_MSG_SERVER_FAILED:
		case REQ_MSG_SERVER_ADDRS_FAILED:
			handleServerDisconnected();
			onSocketFailure(socketEvent);
			break;
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onMessageEvent(ReconnectEvent reconnectEvent) {
		switch (reconnectEvent) {
		case DISABLE: {
			handleServerDisconnected();
		}
			break;
		}
	}

	private void onLoginFailure(LoginEvent event) {
		if (!isManualMConnect) {
			return;
		}
		isManualMConnect = false;
		// String errorTip = getString(IMUIHelper.getLoginErrorTip(event));
		String errorTip = IMLoginManager.instance().getError();

		logger.d("login#errorTip:%s", errorTip);
		//reconnectingProgressBar.setVisibility(View.GONE);
		contactAdapter.putProgress(false);
		Utils.showToast(getActivity(), errorTip);
	}

	private void onSocketFailure(SocketEvent event) {
		if (!isManualMConnect) {
			return;
		}
		isManualMConnect = false;
		String errorTip = getString(IMUIHelper.getSocketErrorTip(event));
		logger.d("login#errorTip:%s", errorTip);
		//reconnectingProgressBar.setVisibility(View.GONE);
		contactAdapter.putProgress(false);
		Utils.showToast(getActivity(), errorTip);
	}
	

	// 更新页面以及 下面的未读总计数
	private void onShieldSuccess(GroupEntity entity) {
		if (entity == null) {
			return;
		}
		// 更新某个sessionId
		contactAdapter.updateRecentInfoByShield(entity);
		IMUnreadMsgManager unreadMsgManager = imService.getUnReadMsgManager();

		int totalUnreadMsgCnt = unreadMsgManager.getTotalUnreadCount();
		logger.d("unread#total cnt %d", totalUnreadMsgCnt);
		((MainActivity) getActivity()).setUnreadMessageCnt(totalUnreadMsgCnt);
	}

	private void onShieldFail() {
		Utils.showToast(getActivity(), R.string.req_msg_failed);
	}

	/**
	 * 搜索数据OK 群组数据与 user数据都已经完毕
	 * */
	public void searchDataReady() {
		if (imService.getContactManager().isUserDataReady()
				&& imService.getGroupManager().isGroupReady()) {
			showSearchFrameLayout();
		}
	}

	/**
	 * 多端，PC端在线状态通知
	 * 
	 * @param isOnline
	 */
	public void onPCLoginStatusNotify(boolean isOnline) {
		logger.d("chatfragment#onPCLoginStatusNotify");
		if (isOnline) {
		//	reconnectingProgressBar.setVisibility(View.GONE);
			//noNetworkView.setVisibility(View.VISIBLE);
			contactAdapter.putProgress(false);
			//contactAdapter.putNetwork(true);
			
//			notifyImage.setImageResource(R.drawable.pc_notify);
//			displayView.setText(R.string.pc_status_notify);
			/** 添加踢出事件 */
//			noNetworkView.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					reconnectingProgressBar.setVisibility(View.VISIBLE);
//					imService.getLoginManager().reqKickPCClient();
//				}
//			});
			
			contactAdapter.putTouch(1,isOnline,isOnline);
			
		} else {
			//noNetworkView.setVisibility(View.GONE);
			contactAdapter.putNetwork(false);
		}
	}

	private void handleServerDisconnected() {
		logger.d("chatfragment#handleServerDisconnected");

//		if (reconnectingProgressBar != null) {
//			reconnectingProgressBar.setVisibility(View.GONE);
//		}
		//contactAdapter.putNetwork(true);
		contactAdapter.putTouch(2,imService.getLoginManager().isKickout(),true);


	}
	
	public void setManualMConnect(boolean isManualMConnect){
		this.isManualMConnect = isManualMConnect;
	}

	/**
	 * 这个处理有点过于粗暴
	 */
	private void onRecentContactDataReady() {
		boolean isUserData = imService.getContactManager().isUserDataReady();
		boolean isSessionData = imService.getSessionManager()
				.isSessionListReady();
		boolean isGroupData = imService.getGroupManager().isGroupReady();

		if (!(isUserData && isSessionData && isGroupData)) {
			return;
		}

		IMUnreadMsgManager unreadMsgManager = imService.getUnReadMsgManager();

		List<RecentInfo> recentSessionList = imService.getSessionManager()
				.getRecentListInfo();
		List<RecentInfo> SessionList = new ArrayList<>();
		for (int i = 0; i < recentSessionList.size(); i++) {

			if (recentSessionList.get(i).getSessionType() == DBConstant.SESSION_TYPE_SINGLE) {
				int peedId = recentSessionList.get(i).getPeerId();
				UserEntity user = imService.getContactManager().findContact(
						peedId);

				if ((user != null) && (user.getAuth() != DBConstant.AUTH_TYPE_BLACK)) {
					SessionList.add(recentSessionList.get(i));
				} else {
					user = imService.getContactManager().findDeviceContact(
							peedId);
					if (user != null) {
						SessionList.add(recentSessionList.get(i));
					}
				}
			} else {
				SessionList.add(recentSessionList.get(i));
			}

		}
		
		
		//没有会话窗口 不弹出 屏蔽
		//setNoChatView(SessionList);// recentSessionList  

		contactAdapter.setData(SessionList);// recentSessionList 
		 

		int totalUnreadMsgCnt = unreadMsgManager.getTotalUnreadCount();
		logger.d("unread#total cnt %d", totalUnreadMsgCnt);
		((MainActivity) getActivity()).setUnreadMessageCnt(totalUnreadMsgCnt);
		
		hideProgressBar();
		showSearchFrameLayout();     
	}      

	private void setNoChatView(List<RecentInfo> recentSessionList) {
		if (recentSessionList.size() == 0) {
			noChatView.setVisibility(View.VISIBLE);
		} else {
			noChatView.setVisibility(View.GONE);
		}
	} 

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {

		RecentInfo recentInfo = contactAdapter.getItem(position);
		if (recentInfo == null) {
			logger.e("recent#onItemLongClick null recentInfo -> position:%d",
					position);
			return false;
		}
		if (recentInfo.getSessionType() == DBConstant.SESSION_TYPE_SINGLE) {
			handleContactItemLongClick(getActivity(), recentInfo);
		} else {
			handleGroupItemLongClick(getActivity(), recentInfo);
		}
		return true;
	}

	private void handleContactItemLongClick(final Context ctx,
			final RecentInfo recentInfo) {

//		AlertDialog.Builder builder = new AlertDialog.Builder(
//				new ContextThemeWrapper(ctx,
//						android.R.style.Theme_Holo_Light_Dialog));

		ListLayoutDialog listLayoutDialog = new ListLayoutDialog(ctx);
		//builder.setTitle(recentInfo.getName()); //暂时屏蔽

		final boolean isTop = imService.getConfigSp().isTopSession(
				recentInfo.getSessionKey());
 
		int topMessageRes = isTop ? R.string.cancel_top_message
				: R.string.top_message;
		String[] items = new String[] { ctx.getString(R.string.check_profile),
				ctx.getString(R.string.delete_session),
				ctx.getString(topMessageRes) };

		listLayoutDialog.setItems(items, new ListLayoutDialog.onItemClickListener() {
			@Override
			public void onClick(int item, Dialog dialog) {
				switch (item) {
				case 0:
					// guanweile
					IMUIHelper.openUserProfileActivity(ctx,
							recentInfo.getPeerId(), false);
					break;
				case 1:
					imService.getSessionManager().reqRemoveSession(recentInfo,
							DBConstant.SESSION_ALL);
					break;
				case 2: {
					imService.getConfigSp().setSessionTop(
							recentInfo.getSessionKey(), !isTop);
				}
					break;
				}
                dialog.dismiss();
			}
		});
//		AlertDialog alertDialog = builder.create();
//		alertDialog.setCanceledOnTouchOutside(true);
//		alertDialog.show();
	}

	// 现在只有群组存在免打扰的
	private void handleGroupItemLongClick(final Context ctx,
			final RecentInfo recentInfo) {

		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(ctx,
						android.R.style.Theme_Holo_Light_Dialog));
		//builder.setTitle(recentInfo.getName());

		final boolean isTop = imService.getConfigSp().isTopSession(
				recentInfo.getSessionKey());
		int topMessageRes = isTop ? R.string.cancel_top_message
				: R.string.top_message;
		 
	//	int forbidMessageRes = isForbidden ? R.string.cancel_forbid_group_message
		//		: R.string.forbid_group_message;

		String[] items = new String[] { ctx.getString(R.string.delete_session),
				ctx.getString(topMessageRes)}; //, ctx.getString(forbidMessageRes) 

		builder.setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					imService.getSessionManager().reqRemoveSession(recentInfo,
							DBConstant.SESSION_ALL);
					break;
				case 1: {
					imService.getConfigSp().setSessionTop(
							recentInfo.getSessionKey(), !isTop);
				}
					break;
				}
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.show();
	}

	@Override
	protected void initHandler() {
		// TODO Auto-generated method stub
	}

	/**
	 * 滚动到有未读消息的第一个联系人 这个还是可行的
	 */
	public void scrollToUnreadPosition() {
		if (contactListView != null) {
			int currentPosition = contactListView.getFirstVisiblePosition();
			int needPosition = contactAdapter
					.getUnreadPositionOnView(currentPosition);
			// 下面这个不管用!!
			// contactListView.smoothScrollToPosition(needPosition);
			contactListView.setSelection(needPosition);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_SCAN
				&& resultCode == Activity.RESULT_OK) {

			if (data != null) {

				String content = data.getStringExtra(DECODED_CONTENT_KEY);
				UserEntity contact = imService.getContactManager()
						.getSearchContact(content);

				if (contact == null) {
					imService.getUserActionManager().reqFriends(content);

				} else {

					// contact.setFriend(1);
					imService.getUserActionManager().setSearchInfo(contact);

					Intent intent = new Intent(ChatFragment.this.getActivity(),
							SearchFriednsActivity.class);
					ChatFragment.this.getActivity().startActivity(intent);
				}

			}
		}
	}
}
