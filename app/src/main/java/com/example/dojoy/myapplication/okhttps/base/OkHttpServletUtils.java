package com.example.dojoy.myapplication.okhttps.base;

/**
 * Created by Administrator on 2016/8/16.
 * 存放请求的servlet
 */
public class OkHttpServletUtils {
    /*--------------------user放这里-----------------*/
    /**
     * 注册
     */
    public static final String REGISTER = "user/addUser";
    /**
     * 登录
     */
    public static final String LOGIN = "user/login";
    /**
     * 修改昵称
     */
    public static final String NICKNAME = "user/updateNickName";


    /*--------------------game放这里-----------------*/
    /**
     * 更多比赛记录
     */
    public static final String GAMELIST = "game/getGameMore";
    /**
     * 首页数据
     */
    public static final String HOMEDATA = "game/getGameList";

    /**
     * 赔率信息
     */
    public static final String GAMEINFO = "game/getGameInfo";

    /*--------------------bet放这里-----------------*/
    /**
     * 下注
     */
    public static final String BET = "bet/addBet";
    /**
     * 统计
     */
    public static final String STATISTIC = "bet/findBetProfitByUserId";

     /*--------------------notice放这里-----------------*/
    /**
     * 资讯列表
     */
    public static final String NOTICELIST = "notice/getNoticeList";
    /**
     * 资讯详情网页
     */
    public static final String WEBNEWS = "notice/findNoticeById";
    /**
     * 资讯评论
     */
    public static final String NEWSCOMMENT = "notice/getNoticeComment";
    /**
     * 添加评论
     */
    public static final String ADDCOMMENT = "notice/addNoticeComment";

    /*--------------------main放这里-----------------*/
    /**
     * 检查更新
     */
    public static final String CHECKVERSION = "main/checkVersion";

    /*--------------------message放这里-----------------*/
    /**
     * 消息
     */
    public static final String MESSAGE = "message/findUserMessage";

}
