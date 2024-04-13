package com.arcsoft.app.staticdata

/**
 * 常量类
 */
object Constant {

    const val currentItem: String = "currentItem"   //标识:传递课程对象
    const val isCheckTeacher: Int = 1
    const val isCheckStudent: Int = 0
    const val client: String = "client"
    const val clientStudent = "1"
    const val clientTeacher = "2"
    const val Compare = "2"
    const val PassWordTrue: String = "true"
    const val UserSno: String = "usersno"
    const val UserName: String = "username"
    const val ToCompareFace: String = "ToCompareFace"
    const val ToActivity: String = "ToActivity"
    const val ToFaceCollectionFromRegister: String = "ToFaceCollectionFromRegister" //注册界面跳转到收集人脸

    /**
     * OpenGauss数据库
     */
    const val url: String = "jdbc:postgresql://192.168.17.239:26000/postgres"
    const val DBuserpassword: String = "xsc20240401."
    const val DBusername: String = "xsc"

    /**
     * 搜索接口查询关键字
     */
    const val QUERY = "query"

    const val AD = "ad"
    const val DATA = "data"
    const val STYLE = "style"
    const val ID = "id"

    const val TITLE_KEY = "title"
    const val URL = "url"
    const val ARTICLE_ID = "article_id"

    const val ACTION_LOGIN = "com.ixuea.courses.mymusic.ACTION_LOGIN"

    /**
     * 广告点击了
     */
    const val ACTION_AD = "com.ixuea.courses.mymusic.ACTION_AD"
    const val ACTION_CHAT = "com.ixuea.courses.mymusic.ACTION_CHAT"
    const val ACTION_SCAN = "com.ixuea.courses.mymusic.ACTION_SCAN"
    const val ACTION_SEARCH = "com.ixuea.courses.mymusic.ACTION_SEARCH"
    const val ACTION_PUSH = "com.ixuea.courses.mymusic.ACTION_PUSH"

    const val ANONYMOUS = "anonymous"

    /**
     * 请求/响应签名key
     */
    const val HEADER_SIGN = "Sign"

    const val VALUE_NO = -1
    const val VALUE0 = 0
    const val VALUE10 = 10
    const val VALUE20 = 20
    const val VALUE30 = 30

    const val STYLE_CODE_LOGIN = VALUE0
    const val STYLE_FORGOT_PASSWORD = VALUE10
    const val STYLE_FRIEND = 12
    const val STYLE_FANS = 13
    const val STYLE_FRIEND_SELECT = 14
    const val STYLE_TITLE = 4
    const val STYLE_USER = 9

    /**
     * 每页返回数量
     *
     *
     * 对于我们API，大部分API默认为10
     */
    const val DEFAULT_SIZE = 10

    /**
     * 默认页码
     */
    const val DEFAULT_PAGE = 1
    //region 平台
    /**
     * android
     */
    const val ANDROID = 0

    /**
     * ios
     */
    const val IOS = 10

    /**
     * web
     */
    const val WEB = 20

    /**
     * wap
     */
    const val WAP = 30
    //endregion

    //region 订单状态
    /**
     * 待支付
     */
    const val WAIT_PAY = 0

    /**
     * 订单关闭
     */
    const val CLOSE = 10

    /**
     * 待发货
     */
    const val WAIT_SHIPPED = 500

    /**
     * 待收货
     */
    const val WAIT_RECEIVED = 510

    /**
     * 待评价
     */
    const val WAIT_COMMENT = 520

    /**
     * 完成
     */
    const val COMPLETE = 530
    //endregion
    /**
     * 支付宝
     */
    const val ALIPAY = 10

    /**
     * 微信
     */
    const val WECHAT = 20

    /**
     * 花呗分期
     */
    const val HUABEI_STAGE = 30

    const val STYLE_CONFIRM_ORDER = 10
    const val STYLE_ORDER = 11

    /**
     * 左侧（其他人）文本消息
     */
    const val TEXT_LEFT = 100

    /**
     * 右侧（我的）文本消息
     */
    const val TEXT_RIGHT = 110

    /**
     * 左侧（其他人）图片消息
     */
    const val IMAGE_LEFT = 120

    /**
     * 右侧（我的）图片消息
     */
    const val IMAGE_RIGHT = 130

    /**
     * 加盐格式化字符串
     */
    const val SALT_START = "wt5j1URZ1H6RDtt"

    /**
     * 加盐格式化字符串
     */
    const val SALT_END = "uWg7x2E0Mr5Xwzm"
}