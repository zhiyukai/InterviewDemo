package prictise.com.application1.kotlin.database

/**
 * @Author zhisiyi
 * @Date 2019.11.08 10:54
 * @Comment
 */
data class AccountBean(var name: String, var account: String, var psw: String, var notes: String?
                       , var createTime: String?, var id: Int) {
    constructor() : this("", "", "", "", "", 0)
}

data class ImportBean(var isSelect: Boolean, var bean:AccountBean )