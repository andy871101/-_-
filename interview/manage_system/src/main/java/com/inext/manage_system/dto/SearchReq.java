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
public class SearchReq {

    /** 性別 **/
    private String gender;

    /** 最小年齢範囲 **/
    private int age1;

    /** 最大年齢範囲 **/
    private int age2;

    /** クラス **/
    private String idClass;

    /** 名前 **/
    private String name;

    /** ページ番号 **/
    private Integer currentPage;
}
