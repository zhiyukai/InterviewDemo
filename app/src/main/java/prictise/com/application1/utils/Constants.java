package prictise.com.application1.utils;

/**
 * author：drx12 on 2018/5/11 16:07
 * E-mail：drx123001@163.com
 */
public interface Constants {
    //net work
    String LoginURL = "http://39.105.26.223/api/login";
    String MTask = "http://39.105.26.223/api/mssStart";
    String PTask = "http://39.105.26.223/api/qstUpdate";
    String StatusURL = "http://39.105.26.223/api/statUpdate";
    String SlertorUpdate = "http://39.105.26.223/api/alertorUpdate";

    //message type
    int MESSAGE_ACTIVE = -1;  //heartbeat
    int MESSAGE_EVENT = 0;       //event
    int MESSAGE_COLSE = 3;       //disconnect

    String NAME_SERVER = "server";
    String NAME_CLIENT = "client";

    String SERVER_IP = "192.168.42.128";

    String BROADCASE_SERIAL_RECEIVE = "com.higgs.serial.receive";

    boolean Debug = true;
    int DB_VERSION = 114;

    int SERVER_PORT = 31001;             //server port

    int SOCKET_CONNECT_TIMEOUT = 15;     //socket overtime 15s
    int SOCKET_ACTIVE_TIME = 60;         //heartbeat time interval 60s
    int WEB_SERVER_ACTIVE_TIME = 30;

    int ASK_WATER_STATUS_TIME = 1;

    String TASK_TABLE_NAME = "task_table_name";      //task the name of the table

    interface ResponseType {
        String response = "response";
        String notification = "notification";
        String callback = "callback";
    }

    interface ConnectUrl {
        String queryList = "/api/markers/query_list";
        String move = "/api/move?marker=";
        String turnAround = "/api/joy_control?angular_velocity=%1$.4f&linear_velocity=0";
        String cancel = "/api/move/cancel";
        String status = "/api/robot_status";
        String insert = "/api/markers/insert?name=%1$s&type=%2$d";
        String joyControl = "/api/joy_control?angular_velocity=%1$.2f&linear_velocity=%2$.2f";
        String estop = "/api/estop?flag=%1$b";
        String positionAdjust = "/api/position_adjust?marker=%1$s";
        String requestData = "/api/request_data?topic=%1$s&switch=%2$s&frequency=%3$d";
        String requestDataCom = "/api/request_data";
        String setParams = "/api/set_params";
        String getParams = "/api/get_params";
        String getVersion = "/api/software/get_version";
        String requestWifi = "/api/wifi/list";
        String connectWifi = "/api/wifi/connect?SSID=%1$s&password=%2$s";
        String getCurrentWifi = "/api/wifi/get_active_connection";
        String getCurrentWifiInfo = "/api/wifi/info";
        String getMapList = "/api/map/list";
        String setCurrentMap = "/api/map/set_current_map";
        String getCurrentMap = "/api/map/get_current_map";
        String shutdown = "/api/shutdown?reboot=%1$b";
        String softwareCheckUpdate = "/api/software/check_for_update";
        String softwareUpdate = "/api/software/update";
        String hardwareCheckUpdate = "/api/hardware/check_for_update";
        String hardwareUpdate = "/api/hardware/update";
        String getRobotInfo = "/api/robot_info";
        String requestDatas = "/api/request_data?topic=robot_status&switch=on&frequency=1";
    }

    interface RobotTopic {
        String baseInfo = "robot/yunfan/%1$s/topic/robot_base_info";
        String moveInfo = "robot/yunfan/%1$s/topic/robot_move_info";
        String awakenInfo = "robot/yunfan/%1$s/topic/robot_awaken_info";
        String updateInfo = "robot/yunfan/%1$s/topic/robot_app_update_info";
        String chargeInfo = "robot/yunfan/%1$s/topic/robot_charge_info";
    }

    interface PropertyKey{
        String productId = "productId";
        String sn = "sn";
        String code = "code";
        String apkVersion = "apkVersion";
        String cfgVersion = "cfgVersion";
        String versionCheck = "versionCheck";
        String isCloseFaceDetect = "isCloseFaceDetect";
        String isCloseAutoCharge = "isCloseAutoCharge";
        String leadToFinishSet = "leadToFinishSet";
    }

    interface LogStatus{
        String start = "start";
        String cancel = "cancel";
        String success = "success";
        String fail = "fail";
    }

    interface LogAction{
        String sleep = "sleep";
        String awaken = "awaken";
        String openMicrophone = "openMicrophone";
        String closeMicrophone = "closeMicrophone";
        String startCharge = "开始回桩";
        String stopCharge = "开始下桩";
        String check = "check";
        String download = "download";
        String install = "install";
    }

    interface LogType{
        String apk = "apk";
        String config = "config";
    }

    /**
     * 控制硬件
     */
    interface ControlHardware {
        //眼睛
        byte[] eyesOpenNormal = new byte[]{(byte) 0XAB, 0X00, 0X01, 0X00, 0X01, (byte) 0XAB};
        byte[] eyesCloseNormal = new byte[]{(byte) 0XAB, 0X00, 0X01, 0X00, 0X02, (byte) 0XA8};
        byte[] eyesOpenXiaoXinXin = new byte[]{(byte) 0XAB, 0X00, 0X01, 0X00, 0X03, (byte) 0XA9};
        byte[] eyesOpenDaXinXin = new byte[]{(byte) 0XAB, 0X00, 0X01, 0X00, 0X04, (byte) 0XAE};
        byte[] eyesOpenZuoKan = new byte[]{(byte) 0XAB, 0X00, 0X01, 0X00, 0X05, (byte) 0XAF};
        byte[] eyesOpenYouKan = new byte[]{(byte) 0XAB, 0X00, 0X01, 0X00, 0X06, (byte) 0XAC};
        byte[] eyesOpenShy = new byte[]{(byte) 0XAB, 0X00, 0X01, 0X00, 0X07, (byte) 0XAD};
        byte[] eyesOpenAnger = new byte[]{(byte) 0XAB, 0X00, 0X01, 0X00, 0X08, (byte) 0XA2};

        //射灯
        byte[] lightOpenOne = new byte[]{(byte) 0XAB, 0X00, 0X02, 0X00, 0X01, (byte) 0XA8};
        byte[] lightCloseOne = new byte[]{(byte) 0XAB, 0X00, 0X02, 0X00, 0X02, (byte) 0XAB};
        byte[] lightOpenTwo = new byte[]{(byte) 0XAB, 0X00, 0X02, 0X00, 0X03, (byte) 0XAA};
        byte[] lightCloseTwo = new byte[]{(byte) 0XAB, 0X00, 0X02, 0X00, 0X04, (byte) 0XAD};

        //紫外线灯
        byte[] ultravioletOpen = new byte[]{(byte) 0XAB, 0X00, 0X03, 0X00, 0X01, (byte) 0XA9};
        byte[] ultravioletClose = new byte[]{(byte) 0XAB, 0X00, 0X03, 0X00, 0X02, (byte) 0XAA};

        //红外传感
        byte[] infraredOneYes = new byte[]{(byte) 0XAB, 0X00, 0X04, 0X00, 0X01, (byte) 0XAE};
        byte[] infraredOneNo = new byte[]{(byte) 0XAB, 0X00, 0X04, 0X00, 0X02, (byte) 0XAD};
        byte[] infraredTwoYes = new byte[]{(byte) 0XAB, 0X00, 0X04, 0X00, 0X03, (byte) 0XAC};
        byte[] infraredTwoNo = new byte[]{(byte) 0XAB, 0X00, 0X04, 0X00, 0X04, (byte) 0XAB};

        //翻盖
        byte[] renovateOpen = new byte[]{(byte) 0XAB, 0X00, 0X05, 0X00, 0X01, (byte) 0XAF};
        byte[] renovateClose = new byte[]{(byte) 0XAB, 0X00, 0X05, 0X00, 0X02, (byte) 0XAC};

        //风扇
        byte[] fanOpenOne = new byte[]{(byte) 0XAB, 0X00, 0X06, 0X00, 0X01, (byte) 0XAC};
        byte[] fanCloseOne = new byte[]{(byte) 0XAB, 0X00, 0X06, 0X00, 0X02, (byte) 0XAF};
        byte[] fanOpenTwo = new byte[]{(byte) 0XAB, 0X00, 0X06, 0X00, 0X03, (byte) 0XAE};
        byte[] fanCloseTwo = new byte[]{(byte) 0XAB, 0X00, 0X06, 0X00, 0X04, (byte) 0XA9};

    }

}
