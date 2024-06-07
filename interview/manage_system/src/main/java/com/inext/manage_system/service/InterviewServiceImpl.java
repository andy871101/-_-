package com.inext.manage_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inext.manage_system.dao.InterviewDao;
import com.inext.manage_system.dto.SaveReq;
import com.inext.manage_system.dto.SearchReq;
import com.inext.manage_system.dto.SearchRes;
import com.inext.manage_system.enums.CommonMessage;
import com.inext.manage_system.enums.Status;
import com.inext.manage_system.model.Interview;

/**
 * 作成者:岳祥 日付 2024/05/10
 */
@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewDao interviewDao;

    /** 
     * インタビュー検索
     *
     * @param searchReq インタビュー検索条件
     * @return SearchRes インタビューリストと検索成功メッセージ
     */
    @Override
    public SearchRes selectInterview(SearchReq searchReq) {
        
        // 性別がnull、空の文字列、空白の文字列の場合，任意の文字列に設定する
        if (!StringUtils.hasText(searchReq.getGender())) {
            searchReq.setGender("%%");
            } else {
            // 性別が男性の場合
            if(searchReq.getGender().equals("1")){
                // 性別を男性に設定する
                searchReq.setGender(Status.MALE.getStatusName());
            } else {
                // 性別を女性に設定する
                searchReq.setGender(Status.FEMALE.getStatusName());
            }
        }
        // 年齢範囲がエラーの場合
        if (searchReq.getAge1() < Status.AGE_MIN.getStatusCode() || searchReq.getAge1() > Status.AGE_MAX.getStatusCode() || searchReq.getAge2() < Status.AGE_MIN.getStatusCode() || searchReq.getAge2() > Status.AGE_MAX.getStatusCode() || searchReq.getAge2() < searchReq.getAge1()) {
            return new SearchRes(CommonMessage.WRONG_AGE);
        }
        // 年齢を設定する
        if (searchReq.getAge2() == Status.AGE_MIN.getStatusCode()){
            searchReq.setAge2(Status.AGE_MAX.getStatusCode());
        }
        
        // クラスがnull、空の文字列、空白の文字列の場合，空の文字列に設定する
        if (!StringUtils.hasText(searchReq.getIdClass())) {
            searchReq.setIdClass("");
        } else {
            // クラスがJavaの場合
            if(searchReq.getIdClass().equals("1")){
                // クラスをJavaに設定する
                searchReq.setIdClass(Status.JAVA.getStatusName());
            } else {
                // クラスをJavascriptに設定する
                searchReq.setIdClass(Status.JAVASCRIPT.getStatusName());
            }
        }
        // 名前がnull、空の文字列、空白の文字列の場合，空の文字列に設定する
        if (!StringUtils.hasText(searchReq.getName())) {
            searchReq.setName("");
        }
        // インタビュー取得 by gender、class、name、age
        List<Interview> interviewList = interviewDao.selectInterview(searchReq);
        return new SearchRes(interviewList, CommonMessage.SEARCH_SUCCESS);
    }
    
    /**
     *  インタビューリスト編修
     * 
     *  @param id インタビューリストId
     *  @return SearchRes インタビューリストと検索成功メッセージ
     */
    @Override
    public SearchRes selectInterviewById(int id) {
        List<Interview> interviewList = interviewDao.selectInterviewById(id);
        return new SearchRes(interviewList, CommonMessage.SEARCH_SUCCESS);
    }

    /**
     *  使用者編修
     * 
     *  @param id 使用者インタビューリストId
     *  @return SearchRes インタビューリストと検索成功メッセージ
     */
    @Override
    public SearchRes selectUserInterviewById(int id) {
        List<Interview> interviewList = interviewDao.selectUserInterviewById(id);
        return new SearchRes(interviewList, CommonMessage.SEARCH_SUCCESS);
    }

    /**
     *  インタビューリスト編修保存
     * 
     *  @param saveReq インタビューリスト資料
     *  @return CommonMessage 保存成功メッセージ
     */
    @Override
    public CommonMessage updateInterview(SaveReq saveReq) {
        // 主旨が設定されていない場合
        if (!StringUtils.hasText(saveReq.getTitle())) {
            // 主旨が設定されていないメッセージ
            return CommonMessage.TITLE_IS_EMPTY;
        }
        // インタビューリスト編修保存
        interviewDao.updateInterview(saveReq);
        return CommonMessage.SEND_SUCCESS;
    }
    

    /**
     *  使用者新規保存
     * 
     *  @param saveReq 使用者インタビューリスト資料
     *  @return CommonMessage 保存成功メッセージ   
     */
    @Override
    public CommonMessage updateUserInterview(SaveReq saveReq) {
        // 主旨が設定されていない場合
        if (!StringUtils.hasText(saveReq.getTitle())) {
            // 主旨が設定されていないメッセージ
            return CommonMessage.TITLE_IS_EMPTY;
        }
        // 新規保存
        interviewDao.updateUserInterview(saveReq);
        return CommonMessage.SEND_SUCCESS;
    }

    /**
     *  インタビュー削除 by id
     * 
     *  @param id インタビューリストID
     *  @return CommonMessage 削除成功メッセージ
     */
    @Override
    public CommonMessage delInterview(int id) {
        // IDが存在しない場合
        if (interviewDao.checkId(id) == 0) {
            // IDが存在しないメッセージ
            return CommonMessage.ID_NOT_FOUND;
        }
        // インタビュー削除
        interviewDao.delInterview(id);
        return CommonMessage.REMOVE_SUCCESS;
    }

}