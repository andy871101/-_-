package com.inext.manage_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作成者:岳祥 日付 2024/05/10
 */
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Interview {
    
    // リスト番号
    private int id;
    // 性別
    private String gender;
    // 年齢
    private int age;
    // クラス
    private String idClass;
    // 英語名
    private String firstnameEng;
    // 英語姓
    private String lastnameEng;
    // 中国語名前
    private String nameCn;
    // 主旨
    private String title;
    // プロファイル
    private String profile;
    // 写真リンク
    private String photo;
    // 講座受けるインタビュー動画リンク
    private String video;
    // 生活動画リンク
    private String lifeVideo;
    // 削除状態
    private int delFlg;
}