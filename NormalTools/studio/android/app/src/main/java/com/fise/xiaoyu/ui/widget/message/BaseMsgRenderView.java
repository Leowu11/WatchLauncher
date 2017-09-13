package com.fise.xiaoyu.ui.widget.message;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fise.xiaoyu.DB.entity.GroupEntity;
import com.fise.xiaoyu.DB.entity.GroupNickEntity;
import com.fise.xiaoyu.DB.entity.MessageEntity;
import com.fise.xiaoyu.DB.entity.PeerEntity;
import com.fise.xiaoyu.DB.entity.UserEntity;
import com.fise.xiaoyu.R;
import com.fise.xiaoyu.config.DBConstant;
import com.fise.xiaoyu.config.MessageConstant;
import com.fise.xiaoyu.imservice.manager.IMGroupManager;
import com.fise.xiaoyu.ui.activity.MessageActivity;
import com.fise.xiaoyu.ui.widget.IMBaseImageView;
import com.fise.xiaoyu.utils.IMUIHelper;
import com.fise.xiaoyu.utils.Utils;

/**
 * BaseMsgRenderView
 */
public abstract class BaseMsgRenderView extends RelativeLayout {

    /**
     * 头像
     */
    protected IMBaseImageView portrait;
    /**
     * 消息状态
     */
    protected ImageView messageFailed;
    protected ProgressBar loadingProgressBar;
    protected TextView name;

    /**
     * 渲染的消息实体
     */
    protected MessageEntity messageEntity;
    protected boolean isMine;
    protected ViewGroup parentView;


    protected BaseMsgRenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 渲染之后做的事情 子类会调用到这个地方嘛?
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        portrait = (IMBaseImageView) findViewById(R.id.user_portrait);
        messageFailed = (ImageView) findViewById(R.id.message_state_failed);
        loadingProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        name = (TextView) findViewById(R.id.name);

    }

    // 消息失败绑定事件 三种不同的弹窗
    // image的load状态就是 sending状态的一个子状态
    public void msgSendinging(final MessageEntity messageEntity) {
        messageFailed.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    public void msgFailure(final MessageEntity messageEntity) {
        messageFailed.setVisibility(View.VISIBLE);
        loadingProgressBar.setVisibility(View.GONE);
    }

    public void msgSuccess(final MessageEntity messageEntity) {
        messageFailed.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.GONE);
    }

    public void msgStatusError(final MessageEntity messageEntity) {
        messageFailed.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.GONE);
    }

    /**
     * 控件赋值
     */
    public void render(final MessageEntity entity, UserEntity userEntity,
                       final Context ctx, PeerEntity peerEntity) {
        this.messageEntity = entity;
        if (userEntity == null) {
            // 没有找到对应的用户信息 todo
            // 请求用户信息 设定默认头像、默认姓名、
            userEntity = new UserEntity();
            userEntity.setMainName("");
            userEntity.setRealName("");
            userEntity.setComment("");
        }

        String avatar = userEntity.getAvatar();
        int msgStatus = messageEntity.getStatus();

        if (userEntity != null
                && (Utils.isClientType(userEntity))) {

            portrait.setDefaultImageRes(R.drawable.tt_default_user_portrait_corner);
            portrait.setCorner(90);
            portrait.setImageUrl(avatar);

        } else {

            if (peerEntity.getType() == DBConstant.SESSION_TYPE_GROUP) {

                GroupEntity groupEntity = (GroupEntity) peerEntity;
                if (groupEntity.getGroupType() == DBConstant.GROUP_TYPE_FAMILY) {
                    portrait.setCorner(90);
                } else {
                    portrait.setCorner(5);
                }
            }

            // 头像设置
            portrait.setDefaultImageRes(R.drawable.tt_default_user_portrait_corner);
            portrait.setImageUrl(avatar);
        }

        // 设定姓名 应该消息都是有的
        if (!isMine) {

            if (peerEntity.getType() == DBConstant.SESSION_TYPE_SINGLE) {
                name.setVisibility(View.GONE);

            } else {


                //显示群昵称
                if (MessageActivity.isShowNick) {
                    if (peerEntity.getType() == DBConstant.SESSION_TYPE_GROUP) {
                        if (userEntity.getIsFriend() == DBConstant.FRIENDS_TYPE_YUYOU
                                || userEntity.getIsFriend() == DBConstant.FRIENDS_TYPE_YES) {

                            GroupNickEntity entity1 = IMGroupManager.instance()
                                    .findGroupNick(peerEntity.getPeerId(),
                                            userEntity.getPeerId());
                            if (entity1 != null) {
                                name.setText(entity1.getNick());
                            } else {
                                if (userEntity == null) {
                                    name.setText("");
                                } else {
                                    if ((userEntity.getComment()!=null)&&(!(userEntity.getComment().equals("")))) {
                                        name.setText(userEntity.getComment());
                                    } else {
                                        name.setText(userEntity.getMainName());
                                    }
                                }

                            }

                        } else {

                            GroupNickEntity entity1 = IMGroupManager.instance()
                                    .findGroupNick(peerEntity.getPeerId(),
                                            userEntity.getPeerId());
                            if (entity1 != null) {
                                name.setText(entity1.getNick());
                            } else {
                                if (userEntity == null) {
                                    name.setText("");
                                } else {

                                    if (((userEntity.getComment()!=null)&&(!userEntity.getComment().equals("")))) {
                                        name.setText(userEntity.getComment());
                                    } else {
                                        name.setText(userEntity.getMainName());
                                    }
                                }

                            }
                        }
                    }
                    name.setVisibility(View.VISIBLE);
                } else {
                    name.setVisibility(View.GONE);
                }
            }

        }

        /** 头像的跳转事件暂时放在这里， todo 业务结合紧密，但是应该不会改了 */
        final int userId = userEntity.getPeerId();
        final int friendsType = userEntity.getIsFriend();
        final UserEntity entityDev = userEntity;
        portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isWeiFriends = false;
                if (friendsType == DBConstant.FRIENDS_TYPE_YUYOU) {
                    isWeiFriends = true;
                }

//				if (entity.getMsgType() == DBConstant.MSG_TYPE_GROUP_DEV_MESSAGE) {
//					IMUIHelper.openDeviceInfoActivity(getContext(),
//							entityDev.getPeerId());
//				} else {
//
//				}

//                if (Utils.isClientType(entityDev)) {
//                    IMUIHelper.openDeviceInfoActivity(getContext(),
//                            entityDev.getPeerId());
//                } else {
//                    IMUIHelper.openUserProfileActivity(getContext(), userId,
//                            isWeiFriends);
//                }

                IMUIHelper.openUserProfileActivity(getContext(), userId,
                        isWeiFriends);
            }
        });
        /** 头像事件 end */

        // 设定三种信息的弹窗类型
        // 判定消息的状态 成功还是失败 todo 具体实现放在子类中
        switch (msgStatus) {
            case MessageConstant.MSG_FAILURE:
                msgFailure(messageEntity);
                break;
            case MessageConstant.MSG_SUCCESS:
                msgSuccess(messageEntity);
                break;
            case MessageConstant.MSG_SENDING:
                msgSendinging(messageEntity);
                break;
            default:
                msgStatusError(messageEntity);
        }

        // 如果消息还是处于loading 状态
        // 判断是否是image类型 image类型的才有 loading，其他的都GONE
        // todo 上面这些由子类做
        // 消息总体的类型有两种
        // 展示类型有四种(图片、文字、语音、混排)

    }

    /**
     * -------------------------set/get--------------------------
     */
    public ImageView getPortrait() {
        return portrait;
    }


    public ImageView getMessageFailed() {
        return messageFailed;
    }

    public ProgressBar getLoadingProgressBar() {
        return loadingProgressBar;
    }

    public TextView getName() {
        return name;
    }

}
