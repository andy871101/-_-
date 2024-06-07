package com.inext.manage_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作成者:岳祥 日付 2024/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRes {

    /** カモンメッセージのコード **/
    private int code;

    /** カモンメッセージ **/
	private String message;

}
