package com.inext.manage_system.dto;

import java.util.List;

import com.inext.manage_system.enums.CommonMessage;
import com.inext.manage_system.model.Interview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作成者:岳祥 日付 2024/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRes {
    private List<Interview> interviewList;

    /** カモンメッセージ **/
    private CommonMessage rtnCode;

    /** カモンメッセージ **/
    public SearchRes(CommonMessage rtnCode) {
        this.rtnCode = rtnCode;
    }
}
