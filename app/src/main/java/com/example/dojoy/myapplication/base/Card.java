package com.example.dojoy.myapplication.base;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhr
 *        describe:会员卡列表Mode
 */

/**
 * venueName (string, optional): 场馆名字 ,
 * venueID (integer, optional): 场馆ID ,
 * cardName (string, optional): 会员卡名 ,
 * cardID (integer, optional): 会员卡ID ,
 * receiveStatus (integer, optional): 是否已领取，0未领取，1已领取 ,
 * userCardNo (integer, optional): 用户持有会员卡ID ,
 * expireTime (string, optional): 有效期或待激活或永久有效 ,
 * img (string, optional): 场馆缩略图 ,
 * cardType (integer, optional): 卡片类型 0：储值卡 1：次卡 2：月卡
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder
public class Card implements Serializable {

    String venueName;
    Integer venueID;
    String cardName;
    Integer cardID;
    Integer receiveStatus;
    Integer userCardNo;
    String expireTime;
    String img;
    Integer cardType;


}
