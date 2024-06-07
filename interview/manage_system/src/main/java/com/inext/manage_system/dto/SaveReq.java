package com.inext.manage_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作成者:岳祥 日付 2024/05/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveReq {

    /** リストID **/
    private int id;

    /** 主旨 **/
    private String title;

    /** プロファイル **/
    private String profile;

    /** 写真 **/
    private String photo;
    
    /** 動画リンク **/    
    private String video;

    /** 生活動画リンク **/
    private String lifeVideo;
    
}
