package com.wm.remusic;

public interface I {
    String TABLENAME="tableName";
    interface UNCAUGHT{
        String PATH="/Users/mac-yk/Downloads/Server/";
        String FILE_NAME="fileName";
        String FILE="file";
    }
    interface REQUEST{
        String UPLOADUNCAUGHT="uploadUncaught";
        String PATH="Server";
        String PARAM="request";
        String DOWNWEIXIU="downloadWeiXiu";
        String DOWNXUNJIAN="downloadXunJian";
        String YUJING="yujing";
        String TONGJI="tongji";
        String CHAXUN="chaxun";
        String CONTROL="control";
        String SAVE="save";
        String LOGOUT="logOut";
        String SERVER_ROOT="http://192.168.1.100:8080/deviceManagement/";
        String XUNJIAN="xunjian";
        String XIUJUN="xiujun";
        String LOGIN="login";
        String BAOFEI="baofei";
        String DOWNDEVICE="downDevice";
        String DOWNSCRAP="downScrap";
        String YONGHOU="yonghou";
        String DOWNPIC="downPic";
        String GETPICCOUNT="getPicCount";
    }
    interface BAOFEI{
        String TABLENAME="baofei";
        String DID="dId";
        String DNAME="dName";
        String REMARK="remark";
        String USER="user";
        String DATE="Date";
    }
    interface PIC{
        String AVATAR_SUFFIX_JPG="JPG";
        String DEVICE="Device";
        String PID="pId";
        String TYPE="picType";
    }


    interface RESULT{
        int SUCCESS=1;
        int DEFEAT=0;
        String ERROR="error";
    }
    interface CONTROL{
        int START=0;
        int BEIYONG=1;
        int DAIYONG=2;
        int YUNXING=3;
        int WEIXIU=4;
        int XIUJUN=5;
        int XUNJIAN=6;
        int BAOFEI=7;
        int YONGHOU=8;
    }
    interface DNAME{
        int DIANTAI=1;
        int JIKONGQI=2;
        int QUKONGQI=3;
        int DIANCHI=4;
    }
    interface DOWNLOAD{
        String PAGE="page";
        String SIZE="size";
    }
    interface DEVICE{
        String TABLENAME="device";
        String DID="dId";
        String DNAME="dName";
        String CHUCHANG="chuchang";
        String STATUS="status";
        String XUNJIAN="xunjian";
        String ISDIANCHI="isDianchi";
    }
    interface USER{
        String TABLENAME="user";
        String NAME="name";
        String PASSWD="passwd";
    }

    interface WEIXIU{
        String ID="id";
        String TABLENAME="weixiu";
        String WXDATE="wxDate";
        String DID="dId";
        String USER="user";
        String TRANSLATE="translate";
        String REMARK="remark";
        String XJDATE="xjDate";
    }
    interface XUNJIAN{
        String TABLENAME="xunjian";
        String DATE="date";
        String STATUS="status";
        String REMARK="remark";
        String USER="user";
        String DID="dId";
    }
    interface GESTURE{
        int MANUAL=1;
        int AUTO=2;
    }
}