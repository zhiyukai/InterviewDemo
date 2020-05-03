package prictise.com.application1.kotlin.rxjava

/**
 * Description: $Description$
 * Created by yangdongze on 2018/4/17 21:21.
 * Email: yangdongze@sunlands.com
 * Version: $Version$
 */

object Keys {
    //微信消息类型
    const val MSG_IMG = 3
    const val MSG_AUDIO = 34
    const val MSG_GIF = 47

    const val SEND_SUCCESS = 2
    const val RECEIVE_SUCCESS = 3

    const val ACTION_DELAY = 1
    const val ACTION_START = 0

    //消息发送状态,1发送中，2我发出去成功，3我接受成功，5发送消息失败，4加好友时候的打招呼消息、6一些本地消息标记
    const val MSG_SENDING = 1
    const val MSG_SEND_SUCCESS = 47
    const val MSG_RECEIVE_SUCCESS = 47
    const val MSG_SEND_FAILURE = 47

    //hasUsagePermission
    const val PHONE_USAGE_PERMISSION = "hasUsagePermission"

    const val MIPUSH_REGID = "mi_push_id"
    const val USERINFO_LAST_UPDDATE = "userinfo_last_update"

    const val AGREE_FRIEND_APPLY = "我通过了你的朋友验证请求，现在我们可以开始聊天了"
    const val ADD_THIS_PHONE = "_add_this_phone"        //去添加这个号码。。。
    //添加好友过程中，对方的一些状态
    const val ADD_FRIEND_NORMAL = 0
    const val ADD_FRIEND_ALREADY = 1
    const val ADD_FRIEND_NOT_EXIST = 2
    const val ADD_FRIEND_OTHER = 3
    const val USER_NOT_EXIST = "该用户不存在"
    const val USER_INFO_EXCEPTION = "被搜帐号状态异常，无法显示"

    //好友状态
    const val STATE_APPLY = "apply"       //我去申请
    const val STATE_DEL_BY_OTHER = "del_by_other"           //对面删我
    const val STATE_DEL_BY_ME = "del_by_me"           //我删对面
    const val STATE_BLOCK_BY_OTHER = "block_by_other"           //对面拉黑我
    const val STATE_BLOCK_BY_ME = "block_by_me"           //我拉黑对面
    const val STATE_INIT = "init"         //初始同步
    const val STATE_INVITE = "invite"     //我同意申请
    const val STATE_ADD = "add"           //对方同意我的申请

    const val THRITY_MINUTES = 30 * 60 * 1000
    const val DAY_MILLIS = 24 * 60 * 60 * 1000

//    const val QUARTER_HOUR =


    //secret
    const val POINT_SERVICES_ORDER =
        "settings put secure enabled_accessibility_services com.sunlands.chatagent/com.sunlands.chatagent.robot.ChatAgentService"
    const val ENABLE_SERVICE_PUT = "settings put secure accessibility_enabled 1"
    val DISENABLE_SERVICE_PUT = "settings put secure accessibility_enabled 0"
    //$ adb shell am start -n "com.sunlands.chatagent/com.sunlands.chatagent.activity.DragElfActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER

    const val LAUNCH_CUR_WECHAT_PAGE = "am start -n \"com.tencent.mm/com.tencent.mm.ui.LauncherUI\" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER"
    const val LAUNCH_WECHAT = "am start -n \"com.tencent.mm/com.tencent.mm.ui.LauncherUI\" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER --activity-clear-top"
    //微信首页->加号->添加朋友
    const val WECHAT_AddMoreFriendsUI = "am start -n \"com.tencent.mm/com.tencent.mm.plugin.subapp.ui.pluginapp.AddMoreFriendsUI\""
    //微信首页->加号->添加朋友->输入
    const val WECHAT_FTSAddFriendUI = "am start -n \"com.tencent.mm/com.tencent.mm.plugin.fts.ui.FTSAddFriendUI\""
    const val WECHAT_FTSMainUI = "am start -n \"com.tencent.mm/com.tencent.mm.plugin.fts.ui.FTSMainUI\""
    const val LAUNCH_APP = "am start -n \"com.sunlands.chatagent/com.sunlands.chatagent.activity.DragElfActivity\" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER"

    // 修改好友备注
    const val WECHAT_ContactRemarkInfoModUI = "am start -n \"com.tencent.mm/.ui.contact.ContactRemarkInfoModUI\" --es \"Contact_User\" "

    //    public static final String LAUNCH_SELF = "am start com.tencent.mm/com.tencent.mm.ui.LauncherUI";

    const val HEAT_LAST_TIME = "heart_last_time"
    const val LATEST_DOWNLOAD_ID = "_latest_download_id"

    const val SUPPORT_WXVERSION = "support_wx_version"
    const val COULD_DEGRADW_WX = "could_degrade_wechat"
    const val CONTROL_WX_FLAG = "control_wx_flag"
    const val PHONE_CALLING_FLAG = "phone_calling_flag"
    const val WX_RECORDING_FLAG = "wx_recording_flag"
    const val LAST_LOGIN_WXID = "last_login_wxid"
    const val CUR_DEVICE_ID = "cur_device_Id"
    const val LAST_UPLOAD_LOG_TIME = "last_upload_log_time"
    const val LAST_SAVE_LOG_NAME = "last_save_log_name"
    const val CURRENT_MASSINFO_CLIENT_ID = "current_massinfo_Client_Id"

    const val HAS_LOGIN = "has_login"
    const val LAST_LOGIN_TIME = "last_login_time"
    const val LAST_CHECK_LOGIN_TIME = "last_check_notify_login_time"
    const val LOGIN_VERIFY_COUNT = "login_verify_count"
    const val LAST_LOGIN_NAME= "last_login_name"
    const val LAST_LOGIN_PWD= "last_login_pwd"
    const val HAS_ROOT = "has_root"
    const val LAST_MASS_TIME= "_last_mass_send_time"
    const val USER_WXALIAS= "_wxalias"


    const val AGENT_SERVICE_RUNNING = "is_agent_service_running"

    const val UPLOAD_STATE_LAST_SYNC_TIME = "upload_state_sync_time"



    const val SYNC_CONTACT_TIME = "_sync_contact_time"
    const val SYNC_SNS_MOMENT_TIME = "_sync_snsmoment_time"
    const val SYNC_CONTACT_SIZE = 100  //每次同步100条通讯录
    const val SYNC_MSG_COUNT = 20 //每次上传消息条数

    const val LOGFILE_MAX_BYTES = 1024 * 1024 // 500K averages to a 4000 lines per file


    const val ROOT_PATH = "chatagent/"  //app 文件根目录
    const val RECORD_DIR = "record/"   //录音文件存放目录，放到sunlands目录下


    //2018-06-12 yyyy-MM-dd
    const val DATE_PATTERN =
        "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))){1}[\\s\\S]*"


    const val APK_DOWNLOAD_DIR = "/storage/emulated/0/chatagent/download/"
    //"mass_send_contact_list" "wxid_2dvtexvatxro21;hyxsile "
    const val massiveHelper = "am start -n com.tencent.mm/.plugin.masssend.ui.MassSendMsgUI --es \"mass_send_contact_list\" "
    const val createGroupHelper = "am start -n com.tencent.mm/.ui.contact.SelectContactUI --es already_select_contact "
    const val createGroupHelper_tail = " --es titile 自动发起群聊 --ei list_type 0 --ei list_attr 4951 --ei scene 7"
    const val FMESSAGE_CONVERSATION = "am start -n com.tencent.mm/.plugin.subapp.ui.friend.FMessageConversationUI --activity-clear-top "

    const val contactInfoUIVerifyUrl = "am start -n com.tencent.mm/com.tencent.mm.plugin.profile.ui.SayHiWithSnsPermissionUI"
    const val contactInfoUIVerifyParam1Key = " --es Contact_User  "
    const val contactInfoUIVerifyParam2Key = " --es  Verify_ticket "
    const val contactInfoUIVerifyParam3KeyAndValue = " --ez sayhi_with_sns_perm_add_remark true"

    const val clearAtyTop = "--activity-clear-top"

    const val IMEI_DEFAULT = "1234567890ABCDEF"

    // 分享朋友圈
    const val WECHAT_SnsTimeLineUI = "am start -n \"com.tencent.mm/.plugin.sns.ui.SnsTimeLineUI\""
    const val WECHAT_SnsUploadUI_PIC = "am start -n \"com.tencent.mm/com.tencent.mm.plugin.sns.ui.SnsUploadUI\" --ei Ksnsupload_type 0 --ei sns_comment_type 1 --ez KSnsPostManu true --es sns_kemdia_path "
    const val WECHAT_SnsUploadUI_PIC_LIST = "am start -n \"com.tencent.mm/com.tencent.mm.plugin.sns.ui.SnsUploadUI\" --ei Ksnsupload_type 0 --ei sns_comment_type 1 --ez KSnsPostManu true --esal sns_kemdia_path_list "
    const val WECHAT_SnsUploadUI_TEXT = "am start -n \"com.tencent.mm/com.tencent.mm.plugin.sns.ui.SnsUploadUI\" --ei Ksnsupload_type 9 --ei sns_comment_type 1 --ez KSnsPostManu true"
    const val SHARE_FRIEND_CONTENT = "share_friend_content"
    const val SHARE_FRIEND_PICTURE_DIR= "FriendCirclePic"
    const val FRIEND_PICTURE_SEPARATOR= ","
    const val FRIEND_PICTURE_SORT_SEPARATOR= "_"

}
