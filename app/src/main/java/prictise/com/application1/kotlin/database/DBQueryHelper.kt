package com.sunlands.chatagent.dbaccessor

import android.database.Cursor
import android.text.TextUtils
import com.sunlands.chatagent.logger.Logger
import com.sunlands.chatagent.model.*
import com.sunlands.chatagent.utils.Keys
import com.sunlands.chatagent.utils.LvBuffReader
import com.sunlands.chatagent.wxapi.MessageBufferParser
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteQueryBuilder
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.util.*

/**
 * Description: $Description$
 * Created by yangdongze on 2019-05-22 11:16.
 * Email: yangdongze@sunlands.com
 * Version: $Version$
 */
object DBQueryHelper {
    /**
     * wx 数据库里面发送/接收成功的消息
     * 最新的一条
     */
    fun maxSuccessedWXMessage(db: SQLiteDatabase?): MutableList<WxMessageEntity>? {
        return maxSuccessedWXMessageByCount(db, 1)
    }

    /**
     * wx 数据库里面发送/接收成功的消息
     * 最新的N条
     */
    fun maxSuccessedWXMessageByCount(db: SQLiteDatabase?, count: Int): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val orderBy = "msgId DESC"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        //取发送或者接收成功的消息
        val okStates = arrayOf(Keys.SEND_SUCCESS.toString(), Keys.RECEIVE_SUCCESS.toString())
        val inList = StringBuilder(okStates.size * 2)
        for (i in 0 until okStates.size) {
            if (i > 0) {
                inList.append(",")
            }
            inList.append("?")
        }
        val sel = "status IN ($inList)"
        val cursor = sqLiteQueryBuilder.query(db, null, sel, okStates, null, null, orderBy, count.toString())
        return cursor2msg(cursor)
    }

    /**
     * wx 数据库里面的消息,不过滤状态
     * 最新的N条
     */
    fun maxWXMessageByCount(db: SQLiteDatabase?, count: Int): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val orderBy = "msgId DESC"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                null,
                null, null, null,
                orderBy,
                count.toString()
        )
        return cursor2msg(cursor)
    }

    /**
     * wx 数据库里面发送/接收成功的消息
     * 最旧的一条
     */
    fun minSuccessedWXMessage(db: SQLiteDatabase?): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val orderBy = "msgId ASC"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        //取发送或者接收成功的消息
        val okStates = arrayOf(Keys.SEND_SUCCESS.toString(), Keys.RECEIVE_SUCCESS.toString())
        val inList = StringBuilder(okStates.size * 2)
        for (i in 0 until okStates.size) {
            if (i > 0) {
                inList.append(",")
            }
            inList.append("?")
        }
        val sel = "status IN ($inList)"
        val cursor = sqLiteQueryBuilder.query(db, null, sel, okStates, null, null, orderBy, "1")
        return cursor2msg(cursor)
    }


    /**
     * IN query
     * 取特定msgSvrId的消息
     */
    fun getWXMsgBySvrIds(db: SQLiteDatabase?, msgSvrArr: LongArray): MutableList<WxMessageEntity>? {
        if (db == null || msgSvrArr.isEmpty()) return null
        val strArgs = mutableListOf<String>()
        for (svr in msgSvrArr) strArgs.add(svr.toString())
        val inList = StringBuilder(msgSvrArr.size * 2)
        for (i in 0 until msgSvrArr.size) {
            if (i > 0) {
                inList.append(",")
            }
            inList.append("?")
        }
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"

        val cursor = sqLiteQueryBuilder.query(
                db,
                null,
                "msgSvrId IN ($inList)",
                strArgs.toTypedArray(),
                null,
                null,
                null,
                null
        )
        return cursor2msg(cursor)
    }


    /**
     * 升序
     * 找大于 msgTime 的消息
     */
    fun getWXNewMessageByTime(db: SQLiteDatabase?, msgTime: Long): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val orderBy = "createTime ASC"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        val sel = "createTime > ?"
        val arg: Array<String> = arrayOf(msgTime.toString())
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null,
                orderBy,
                Keys.SYNC_MSG_COUNT.toString()
        )
        return cursor2msg(cursor)
    }

    /**
     * 降序
     * 找小于 msgId 的消息
     */
    fun getWXOldMessageByTime(db: SQLiteDatabase?, msgTime: Long): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val orderBy = "createTime DESC"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        val sel = "createTime < ?"
        val arg: Array<String> = arrayOf(msgTime.toString())
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null,
                orderBy,
                Keys.SYNC_MSG_COUNT.toString()
        )

        return cursor2msg(cursor)
    }

    /**
     * 升序
     * 找大于 msgId 的消息
     */
    fun getWXNewMessage(db: SQLiteDatabase?, msgId: Int): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val orderBy = "msgId ASC"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        val sel = "msgId > ?"
        val arg: Array<String> = arrayOf(msgId.toString())

        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null,
                orderBy,
                Keys.SYNC_MSG_COUNT.toString()
        )
        return cursor2msg(cursor)
    }

    /**
     * 降序
     * 找小于 msgId 的消息
     */
    fun getWXOldMessage(db: SQLiteDatabase?, msgId: Int): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val orderBy = "msgId DESC"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        val sel = "msgId < ?"
        val arg: Array<String> = arrayOf(msgId.toString())
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null,
                orderBy,
                Keys.SYNC_MSG_COUNT.toString()
        )

        return cursor2msg(cursor)
    }

    /**
     * 找msgSvrId对应的消息
     */
    fun getWXMsgBySvrId(db: SQLiteDatabase?, msgSvrId: Long): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        val sel = "msgSvrId = ?"
        val arg: Array<String> = arrayOf(msgSvrId.toString())
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null,
                null,
                null
        )
        return cursor2msg(cursor)
    }

    /**
     * 升序
     * 找时间 大于 createTime 消息里面的第一条
     */
    fun getWXMsgByTime(db: SQLiteDatabase?, createTime: Long): MutableList<WxMessageEntity>? {
        if (db == null) return null
        val orderBy = "createTime ASC"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        val sel = "createTime >= ?"
        val arg: Array<String> = arrayOf(createTime.toString())
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null,
                orderBy,
                "1"
        )
        return cursor2msg(cursor)
    }

    /**
     * 找与userId聊天的消息，大于createTime的消息条数
     */
    fun getWXMsgByIDGeTime(db: SQLiteDatabase?, userId: String?, createTime: Long): MutableList<WxMessageEntity>? {
        if (db == null || TextUtils.isEmpty(userId)) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "message"
        val sel = "talker = ? AND createTime > ?"
        val arg: Array<String> = arrayOf(userId!!, createTime.toString())
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null,
                null
        )
        return cursor2msg(cursor)
    }

    //根据微信id找头像
    private fun getHeadImgCursor(db: SQLiteDatabase?, wxId: String?): Cursor? {
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "img_flag"
        if (db == null || TextUtils.isEmpty(wxId)) return null
        val sel = "username = ?"
        val arg = arrayOf(wxId)
        return sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null, null
        )
    }

    fun getHeadImageUrlByWxUsername(db: SQLiteDatabase?, wxId: String?): String? {
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "img_flag"
        if (db == null || TextUtils.isEmpty(wxId)) return null
        val sel = "username = ?"
        val arg = arrayOf(wxId)
        val cursor =  sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null, null
        )
        try {
            if (cursor == null || cursor.count == 0) {
                return null
            } else {
                cursor.moveToFirst()
                return cursor.getString(cursor.getColumnIndex("reserved1"))
            }
        } finally {
            cursor.close()
        }
    }


    /**
     * 使用模糊查询，避免有些空格，不好匹配
     */
    fun getRContactByNickName(db: SQLiteDatabase?, nickName: String?): List<WxFriendEntity>? {
        if (db == null || TextUtils.isEmpty(nickName)) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "rcontact"

        val cursor = sqLiteQueryBuilder.query(
                db, null,
                "nickname like ?",
                arrayOf("%$nickName"),
                null,
                null,
                null
        )

        return cursor2wxFriend(db, cursor)
    }

    //根据微信昵称或者备注
    fun getRContactByRemark(db: SQLiteDatabase?, nick: String): List<WxFriendEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "rcontact"
        val sel = "nickname like ? or conRemark like ?"
        val arg = arrayOf("%$nick", "%$nick")
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null, null
        )
        return cursor2wxFriend(db, cursor)
    }

    /**
     * 获取所有联系人
     */
    fun getAllContacts(db: SQLiteDatabase?): List<WxFriendEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "rcontact"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                null, null,
                null, null,
                null
        )
        return cursor2wxFriend(db, cursor)
    }

    /**
     * 获取群信息
     */
    fun getAllChatRoomInfo(db:SQLiteDatabase?):List<WxChatRoomEntity>?{
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "chatroom"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                null, null,
                null, null,
                null
        )
        return cursor2wxChatroom(db, cursor)
    }

    /**
     * 获取朋友圈信息
     */
    fun getSnsInfo(db:SQLiteDatabase?, wxLoginName:String):MutableList<WxSnsInfoEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "SnsInfo"
        //获取三天内的朋友圈信息, 微信db存的时间单位是秒
        val offset3dayTimeMillis = (System.currentTimeMillis() - 3 * Keys.DAY_MILLIS)/1000
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                "createTime > ?", arrayOf("$offset3dayTimeMillis"),
//                "userName = ?", arrayOf(wxLoginName),
                null, null,
                null
        )
        val wxChatRoomList = ArrayList<WxSnsInfoEntity>()
        if (cursor == null) {
            Logger.d("uploadAllContact getSnsInfo: cursor == null")

            return null
        }
        if (cursor.count == 0) {
            cursor.close()
            return null
        }
        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val meWeiXinId = wxLoginName
                val friWeiXinId = cursor.getString(cursor.getColumnIndex("userName"))
                val type = cursor.getInt(cursor.getColumnIndex("type"))
                val contentByteArray = cursor.getBlob(cursor.getColumnIndex("content"))
                var content: CharArray? = null
                if (contentByteArray != null) {
                    val byteBuffer = ByteBuffer.allocate(contentByteArray.size).apply {
                        put(contentByteArray)
                        flip()
                    }
                    content = Charset.forName("UTF-8").decode(byteBuffer).array()
                }
                //微信db存的时间单位是秒,转成毫秒
                val weiXinCreateDate = (cursor.getLong(cursor.getColumnIndex("createTime"))?:0) * 1000
                val weiXinCircleId = cursor.getString(cursor.getColumnIndex("snsId"))
                val entity = WeiXinUtils.dealWeiXinDataOfCircle(weiXinCircleId, meWeiXinId, wxLoginName, friWeiXinId, type, weiXinCreateDate,
                        content)
                wxChatRoomList.add(WxSnsInfoEntity(
                        entity.weiXinCircleId,
                        entity.type,
                        entity.friWeiXinId,
                        entity.shareTitle,
                        entity.content,
                        entity.weiXinCreateDate,
                        entity.urls?.toMutableList()
                ))
                cursor.moveToNext()
            }
        } catch (e: Exception) {
            Logger.d("getSnsInfo exception: ${e.localizedMessage}")
        } finally {
            cursor?.close()
        }
        return wxChatRoomList
    }

    /**
     * 获取所有群发消息
     */
    fun getAllMassendInfo(db: SQLiteDatabase?): MutableList<WxMassendInfoEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "massendinfo"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                null, null,
                null, null,
                null
        )
        return cursor2wxMassendInfo(cursor)
    }

    /**
     * 获取特定群发消息
     */
    fun getSpecialMassendInfo(db: SQLiteDatabase?, specialCliendId: Long): List<WxMassendInfoEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "massendinfo"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                "clientid = ?", arrayOf(specialCliendId.toString()),
                null, null,
                null
        )
        return cursor2wxMassendInfo(cursor)
    }

    /**
     * 获取特定群发消息
     */
    fun getMassInfo(db: SQLiteDatabase?, lastTime: String, count: Int): MutableList<WxMassendInfoEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "massendinfo"
        val orderBy = "createtime ASC"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                "createtime > ?", arrayOf(lastTime),
                null, null,
                orderBy,
                count.toString()
        )
        return cursor2wxMassendInfo(cursor)
    }

    /**
     * 获取大于specialCliendId的群发消息
     */
    fun getAllMassendInfoByClientId(db: SQLiteDatabase?, specialCliendId: Long): MutableList<WxMassendInfoEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "massendinfo"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                "clientid > ?", arrayOf(specialCliendId.toString()),
                null, null,
                null
        )
        return cursor2wxMassendInfo(cursor)
    }

    fun getSpecialContact(db: SQLiteDatabase?, specialWxId: String?): List<WxFriendEntity>? {
        if (db == null || TextUtils.isEmpty(specialWxId)) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "rcontact"
        val sel = "username = ?"
        val arg = arrayOf(specialWxId)
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null, null
        )
        return cursor2wxFriend(db, cursor)
    }

    fun getSpecialContact(db: SQLiteDatabase?, specialWxId: String?, encryptUsername: String): List<WxFriendEntity>? {
        if (db == null || TextUtils.isEmpty(specialWxId)) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "rcontact"
        val sel = "username = ? OR encryptUsername = ?"
        val arg = arrayOf(specialWxId, encryptUsername)
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                sel,
                arg, null, null, null
        )
        return cursor2wxFriend(db, cursor)
    }

    //stranger 联系人
    fun getStrangersFromContacts(db: SQLiteDatabase?): List<WxFriendEntity>? {
        if (db == null) return null
        val searchStr = "@stranger"
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "rcontact"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                "username like ?",
                arrayOf("%$searchStr"),
                null,
                null,
                null
        )
        return cursor2wxFriend(db, cursor)
    }

    /**
     * IN query
     */
    fun getRContactsByIds(db: SQLiteDatabase?, users: List<String>): List<WxFriendEntity>? {
        if (db == null || users.isEmpty()) return null
        val inList = StringBuilder(users.size * 2)
        for (i in 0 until users.size) {
            if (i > 0) {
                inList.append(",")
            }
            inList.append("?")
        }
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "rcontact"

        val cursor = sqLiteQueryBuilder.query(
                db,
                null,
                "username IN ($inList)",
                users.toTypedArray(),
                null,
                null,
                null,
                null
        )
        return cursor2wxFriend(db, cursor)
    }


    private fun cursor2wxFriend(db: SQLiteDatabase?, cursor: Cursor?): List<WxFriendEntity>? {
        val wxFriends = ArrayList<WxFriendEntity>()
        if (cursor == null) {
            return null
        }
        if (cursor.count == 0) {
            cursor.close()
            return null
        }
        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val entity = WxFriendEntity()
                entity.username = cursor.getString(cursor.getColumnIndex("username"))

                val alias = cursor.getString(cursor.getColumnIndex("alias"))
                entity.alias = alias ?: ""

                val conRemark = cursor.getString(cursor.getColumnIndex("conRemark"))
                entity.conRemark = conRemark ?: ""

                val domainList = cursor.getString(cursor.getColumnIndex("domainList"))
                entity.domainList = domainList ?: ""

                val nickname = cursor.getString(cursor.getColumnIndex("nickname"))
                entity.nickname = nickname ?: ""

                val pyInitial = cursor.getString(cursor.getColumnIndex("pyInitial"))
                entity.pyInitial = pyInitial ?: ""

                val quanPin = cursor.getString(cursor.getColumnIndex("quanPin"))
                entity.quanPin = quanPin ?: ""


                entity.showHead = cursor.getInt(cursor.getColumnIndex("showHead"))
                entity.type = cursor.getInt(cursor.getColumnIndex("type"))
                entity.weiboFlag = cursor.getInt(cursor.getColumnIndex("weiboFlag"))

                val weiboNickname = cursor.getString(cursor.getColumnIndex("weiboNickname"))
                entity.weiboNickname = weiboNickname ?: ""

                val conRemarkPYFull = cursor.getString(cursor.getColumnIndex("conRemarkPYFull"))
                entity.conRemarkPYFull = conRemarkPYFull ?: ""

                val conRemarkPYShort = cursor.getString(cursor.getColumnIndex("conRemarkPYShort"))
                entity.conRemarkPYShort = conRemarkPYShort ?: ""

                entity.verifyFlag = cursor.getInt(cursor.getColumnIndex("verifyFlag"))

                val encryptUsername = cursor.getString(cursor.getColumnIndex("encryptUsername"))
                entity.encryptUsername = encryptUsername ?: ""

                entity.chatroomFlag = cursor.getInt(cursor.getColumnIndex("chatroomFlag"))
                entity.deleteFlag = cursor.getInt(cursor.getColumnIndex("deleteFlag"))

                val contactLabelIds = cursor.getString(cursor.getColumnIndex("contactLabelIds"))
                entity.contactLabelIds = contactLabelIds ?: ""

                //从lvbuff字段中读取性别、签名、省份、城市
                analyzeContactLvbuff(cursor, entity)
                //从img_flag表中获取头像大图、小图
                analyzeImg(db, entity)

                wxFriends.add(entity)
                cursor.moveToNext()
            }

            cursor.close()
        } catch (e: Exception) {
            Logger.d("cursor2wxFriend exception: ${e.localizedMessage}")
        }
        return wxFriends
    }
    private fun cursor2wxChatroom(db: SQLiteDatabase?, cursor: Cursor?): List<WxChatRoomEntity>? {
        val wxChatRoomList = ArrayList<WxChatRoomEntity>()
        if (cursor == null) {
            Logger.d("uploadAllContact cursor2wxChatroom: cursor == null")

            return null
        }
        if (cursor.count == 0) {
            cursor.close()
            return null
        }
        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val entity = WxChatRoomEntity()
                entity.chatRoomName = cursor.getString(cursor.getColumnIndex("chatroomname"))?:""
                entity.addTime = cursor.getLong(cursor.getColumnIndex("addtime"))
                entity.memberList = cursor.getString(cursor.getColumnIndex("memberlist"))?:""
                entity.displayName = cursor.getString(cursor.getColumnIndex("displayname"))?: ""

                entity.chatRoomNick = cursor.getString(cursor.getColumnIndex("chatroomnick"))?:""
                entity.roomFlag = cursor.getInt(cursor.getColumnIndex("roomflag"))
                entity.roomOwner = cursor.getString(cursor.getColumnIndex("roomowner"))?:""
//                entity.roomdata = cursor.getString(cursor.getColumnIndex("roomdata"))?:""
                entity.isShowName = cursor.getInt(cursor.getColumnIndex("isShowname"))
                entity.selfDisplayName = cursor.getString(cursor.getColumnIndex("selfDisplayName"))?:""
                entity.style = cursor.getInt(cursor.getColumnIndex("style"))
                entity.chatRoomDataFlag = cursor.getInt(cursor.getColumnIndex("chatroomdataflag"))
                entity.modifyTime = cursor.getString(cursor.getColumnIndex("modifytime"))?:""
                entity.chatRoomNotice = cursor.getString(cursor.getColumnIndex("chatroomnotice"))?:""
                entity.chatRoomVersion = cursor.getInt(cursor.getColumnIndex("chatroomVersion"))
                entity.chatRoomNoticeEditor = cursor.getString(cursor.getColumnIndex("chatroomnoticeEditor"))?:""
                entity.chatRoomNoticePublishTime = cursor.getLong(cursor.getColumnIndex("chatroomnoticePublishTime"))
                entity.chatRoomLocalVersion = cursor.getInt(cursor.getColumnIndex("chatroomLocalVersion"))
                entity.chatRoomStatus = cursor.getInt(cursor.getColumnIndex("chatroomStatus"))
                entity.memberCount = cursor.getInt(cursor.getColumnIndex("memberCount"))

                wxChatRoomList.add(entity)
                cursor.moveToNext()
            }

            cursor.close()
        } catch (e: Exception) {
            Logger.d("cursor2wxFriend exception: ${e.localizedMessage}")
        }
        return wxChatRoomList
    }

    private fun cursor2wxMassendInfo(cursor: Cursor?): MutableList<WxMassendInfoEntity>? {
        val wxMassendInfos = ArrayList<WxMassendInfoEntity>()
        if (cursor == null) {
            return null
        }
        if (cursor.count == 0) {
            cursor.close()
            return wxMassendInfos
        }

        Logger.d("cursor2wxFriend cursor.count: ${cursor.count}")
        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val entity = WxMassendInfoEntity()
                entity.clientId = cursor.getLong(cursor.getColumnIndex("clientid"))
                entity.status = cursor.getLong(cursor.getColumnIndex("status"))
                entity.createTime = cursor.getLong(cursor.getColumnIndex("createtime"))
                entity.lastModifyTime = cursor.getLong(cursor.getColumnIndex("lastmodifytime"))
                entity.fileName = cursor.getString(cursor.getColumnIndex("filename"))
                entity.thumbFilename = cursor.getString(cursor.getColumnIndex("thumbfilename"))
                entity.toList = cursor.getString(cursor.getColumnIndex("tolist"))
                entity.toListCount = cursor.getInt(cursor.getColumnIndex("tolistcount"))
                entity.msgType = cursor.getInt(cursor.getColumnIndex("msgtype"))
                entity.mediaTime = cursor.getLong(cursor.getColumnIndex("mediatime"))
                entity.dataNetOffset = cursor.getInt(cursor.getColumnIndex("datanetoffset"))
                entity.dataLen = cursor.getInt(cursor.getColumnIndex("datalen"))
                entity.thumbNetOffset = cursor.getInt(cursor.getColumnIndex("thumbnetoffset"))
                entity.thumbTotalLen = cursor.getInt(cursor.getColumnIndex("thumbtotallen"))

                wxMassendInfos.add(entity)
                cursor.moveToNext()
            }

            cursor.close()
        } catch (e: Exception) {
            Logger.d("cursor2wxMassendInfo exception: ${e.localizedMessage}")
        }
        return wxMassendInfos
    }

    /**
     * 从lvbuff字段中获取性别、签名、省份、城市
     */
    private fun analyzeContactLvbuff(cursor: Cursor, entity: WxFriendEntity) {
        try {
            val lvBuffer = cursor.getBlob(cursor.getColumnIndex("lvbuff"))
            val lvBuffReader = LvBuffReader()
            lvBuffReader.read(lvBuffer)
            entity.sex = lvBuffReader.sex
            entity.signature = lvBuffReader.signature
            entity.province = lvBuffReader.province
            entity.city = lvBuffReader.city
        } catch (e: Exception) {
            Logger.d("analyzeMessageLvbuff: ${e.localizedMessage}")
        }
    }

    private fun analyzeMessageLvbuff(cursor: Cursor, entity: WxMessageEntity) {
        try {
            val lvBuffer = cursor.getBlob(cursor.getColumnIndex("lvbuffer"))
            val messageBufferParser = MessageBufferParser()
            messageBufferParser.parse(lvBuffer)
        } catch (e: Exception) {
            Logger.d("analyzeMessageLvbuff: ${e.localizedMessage}")
        }
    }

    /**
     * 从img_flag表中获取头像大图、小图
     */
    private fun analyzeImg(db: SQLiteDatabase?, entity: WxFriendEntity) {
        //todo 性能
        val cursorHeadImg = getHeadImgCursor(db, entity.username)
        if (cursorHeadImg == null || cursorHeadImg.count == 0) {
            cursorHeadImg?.close()
        } else {
            cursorHeadImg.moveToFirst()
            entity.headImg = cursorHeadImg.getString(cursorHeadImg.getColumnIndex("reserved1"))
            entity.headImgSmall = cursorHeadImg.getString(cursorHeadImg.getColumnIndex("reserved2"))
            cursorHeadImg.close()
        }
    }

    private fun cursor2stranger(cursor: Cursor?): List<WxStrangerEntity>? {
        if (cursor == null) return null
        if (cursor.count == 0) {
            cursor.close()
            return null
        }

        val strangers = mutableListOf<WxStrangerEntity>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val entity = WxStrangerEntity()
            entity.encryptUsername = cursor.getString(cursor.getColumnIndex("encryptUsername"))
            entity.conRemark = cursor.getString(cursor.getColumnIndex("conRemark"))
            entity.contactLabels = cursor.getString(cursor.getColumnIndex("contactLabels"))
            entity.conDescription = cursor.getString(cursor.getColumnIndex("conDescription"))
            entity.conPhone = cursor.getString(cursor.getColumnIndex("conPhone"))

            strangers.add(entity)

            cursor.moveToNext()
        }
        cursor.close()
        return strangers
    }

    fun getAllStrangers(db: SQLiteDatabase?): List<WxStrangerEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "Stranger"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                null,
                null, null, null, null, null
        )
        return cursor2stranger(cursor)
    }

    fun getStrangersByPhone(db: SQLiteDatabase?, phone: String): List<WxStrangerEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "Stranger"
        val cursor = sqLiteQueryBuilder.query(
                db,
                null,
                "conPhone like ?",
                arrayOf("%$phone"), null, null, null, null
        )
        return cursor2stranger(cursor)
    }

    /**
     * 通过cursor读取消息
     */
    private fun cursor2msg(cursor: Cursor?): ArrayList<WxMessageEntity>? {
        if (cursor == null) return null
        if (cursor.count == 0) {
            cursor.close()
            return null
        }
        val wxMessageList = ArrayList<WxMessageEntity>()

        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val wxMessage = WxMessageEntity()
                wxMessage.msgId = cursor.getInt(cursor.getColumnIndex("msgId"))
                wxMessage.msgSvrId = cursor.getLong(cursor.getColumnIndex("msgSvrId"))
                wxMessage.type = cursor.getInt(cursor.getColumnIndex("type"))
                wxMessage.status = cursor.getInt(cursor.getColumnIndex("status"))
                wxMessage.isSend = cursor.getInt(cursor.getColumnIndex("isSend"))
                wxMessage.isShowTimer = cursor.getInt(cursor.getColumnIndex("isShowTimer"))
                wxMessage.createTime = (cursor.getLong(cursor.getColumnIndex("createTime")))
                wxMessage.uiCreateTime = wxMessage.createTime / 1000

                val talker = cursor.getString(cursor.getColumnIndex("talker"))
                wxMessage.talker = talker ?: ""

                var content = cursor.getString(cursor.getColumnIndex("content"))
                if (content != null && content.length >= 100) {
                    //只取前50，避免content过长到问题
                    content = content.substring(0, 99) + "……"
                }

                wxMessage.content = content ?: ""

                val imgPath = cursor.getString(cursor.getColumnIndex("imgPath"))
                wxMessage.imgPath = imgPath ?: ""

                val reserved = cursor.getString(cursor.getColumnIndex("reserved"))
                wxMessage.reserved = reserved ?: ""

                val transContent = cursor.getString(cursor.getColumnIndex("transContent"))
                wxMessage.transContent = transContent ?: ""

                val transBrandWording = cursor.getString(cursor.getColumnIndex("transBrandWording"))
                wxMessage.transBrandWording = transBrandWording ?: ""

                wxMessage.talkerId = cursor.getInt(cursor.getColumnIndex("talkerId"))

                val bizClientMsgId = cursor.getString(cursor.getColumnIndex("bizClientMsgId"))
                wxMessage.bizClientMsgId = bizClientMsgId ?: ""

                wxMessage.bizChatId = cursor.getInt(cursor.getColumnIndex("bizChatId"))

                val bizChatUserId = cursor.getString(cursor.getColumnIndex("bizChatUserId"))
                wxMessage.bizChatUserId = bizChatUserId ?: ""

                wxMessage.msgSeq = cursor.getInt(cursor.getColumnIndex("msgSeq"))
                wxMessage.flag = cursor.getInt(cursor.getColumnIndex("flag"))

//                analyzeMessageLvbuff(cursor, wxMessage)

                wxMessageList.add(wxMessage)
                cursor.moveToNext()
            }
        } catch (e: Exception) {
            Logger.d("cursor2msg exception: ${e.localizedMessage}")
        } finally {
            try {
                cursor.close()
            } catch (e: Exception) {
                Logger.d("cursor2msg cursor.close(): ${e.localizedMessage}")
            }
        }

        return wxMessageList
    }

    /**
     * 获取所有标签
     */
    fun getAllLabels(db: SQLiteDatabase?): List<WxLabelEntity>? {
        if (db == null) return null
        val sqLiteQueryBuilder = SQLiteQueryBuilder()
        sqLiteQueryBuilder.tables = "ContactLabel"
        val cursor = sqLiteQueryBuilder.query(
                db, null,
                null, null,
                null, null,
                null
        )
        return cursor2wxLabel(cursor)
    }

    private fun cursor2wxLabel(cursor: Cursor?): List<WxLabelEntity>? {
        val wxLabels = ArrayList<WxLabelEntity>()
        if (cursor == null) {
            return null
        }
        if (cursor.count == 0) {
            cursor.close()
            return wxLabels
        }

        Logger.d("cursor2wxLabel cursor.count: ${cursor.count}")
        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val entity = WxLabelEntity()
                entity.labelID = cursor.getString(cursor.getColumnIndex("labelID"))
                entity.labelName = cursor.getString(cursor.getColumnIndex("labelName"))
                wxLabels.add(entity)
                cursor.moveToNext()
            }
        } catch (e: Exception) {
            Logger.d("cursor2wxLabel exception: ${e.localizedMessage}")
        } finally {
            try {
                cursor.close()
            } catch (e: Exception) {
                Logger.d("cursor2wxLabel cursor.close(): ${e.localizedMessage}")
            }
        }
        return wxLabels
    }

}