package com.inext.manage_system.service;

import com.inext.manage_system.dto.SaveReq;
import com.inext.manage_system.dto.SearchReq;
import com.inext.manage_system.dto.SearchRes;
import com.inext.manage_system.enums.CommonMessage;

/**
 * 作成者:岳祥 日付 2024/05/10
 */
public interface InterviewService {

    // インタビュー検索
    public SearchRes selectInterview (SearchReq searchReq);

    // インタビュー検索 by id
    public SearchRes selectInterviewById(int id);

    // 使用者インタビュー検索 by id
    public SearchRes selectUserInterviewById(int id);

    // 編修保存
    public CommonMessage updateInterview(SaveReq saveReq);
    
    // 使用者新規保存
    public CommonMessage updateUserInterview(SaveReq saveReq);
    
    // インタビュー削除 by id
    public CommonMessage delInterview(int id);
}