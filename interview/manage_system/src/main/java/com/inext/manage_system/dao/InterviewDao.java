package com.inext.manage_system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.inext.manage_system.dto.SaveReq;
import com.inext.manage_system.dto.SearchReq;
import com.inext.manage_system.model.Interview;

@Mapper
public interface InterviewDao {

    // インタビュー取得 by gender、class、name、age
    public List<Interview> selectInterview(@Param("req") SearchReq req);

    // インタビュー取得 by id
    public List<Interview> selectInterviewById(@Param("id") int id);

    // 使用者インタビュー取得 by id
    public List<Interview> selectUserInterviewById(@Param("id") int id);

    // インタビュー編修保存
    public int updateInterview(@Param("saveReq") SaveReq saveReq);

    // 使用者新規保存
    public int updateUserInterview(@Param("saveReq") SaveReq saveReq);

    // インタビュー削除
    public int delInterview(@Param("id") int id);

    // IDの存在を確認する
    public int checkId(@Param("id") int id);
}