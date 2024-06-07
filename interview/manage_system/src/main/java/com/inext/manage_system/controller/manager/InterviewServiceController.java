package com.inext.manage_system.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.inext.manage_system.dto.BaseRes;
import com.inext.manage_system.dto.SaveReq;
import com.inext.manage_system.dto.SearchReq;
import com.inext.manage_system.dto.SearchRes;
import com.inext.manage_system.enums.CommonMessage;
import com.inext.manage_system.service.InterviewService;

import org.springframework.ui.Model;

import jakarta.annotation.Resource;

/**
 * 作成者:岳祥 日付 2024/05/16
 */
@Controller
public class InterviewServiceController {

    @Resource
    private InterviewService interviewService;

    /**  
     * インタビューのリストページを表示する
     * 
     * @param model モデルオブジェクト
     * @return リストページのビュー名
    */
    @GetMapping("/manager/InterviewList")
    public String InterviewList(Model model){

        // ページ番号
        int defaultPageNumber = 1;
        // ページ数
        int pageSize = 3;

        // ページを設定する
        PageHelper.startPage(defaultPageNumber, pageSize);

        // インタビュー検索、結果を取得する
        SearchReq searchReq = new SearchReq();
        SearchRes searchResult = interviewService.selectInterview(searchReq);

        // インタビューリストページ表示
        model.addAttribute("searchData", searchResult.getInterviewList());

        return "/manager/InterviewList";
    }

    /**  
     * インタビューのリストページを表示する
     * 
     * @param model     モデルオブジェクト
     * @param searchReq 検索条件
     * @return 検索後のリストページのビュー名
    */
    @PostMapping("/manager/SearchInterview")
    public String searchInterviewList(@RequestBody SearchReq searchReq, Model model){

        // ページ番号
        int defaultPageNumber = 1;
        // ページ数
        int pageSize = 3;
        
        // 実際のページ番号情報を取得する
        Integer allPageNumber = searchReq.getCurrentPage() != null ? searchReq.getCurrentPage() : defaultPageNumber;
        // ページを設定する
        PageHelper.startPage(allPageNumber, pageSize);

        // インタビュー検索、結果を取得する
        SearchRes searchResult = interviewService.selectInterview(searchReq);
        // インタビューのリストページを更新する
        model.addAttribute("searchData", searchResult.getInterviewList());

        return "/manager/InterviewList::search";
    }

    /**  
     * インタビューリストをプレビューに移動する
     * 
     * @param model モデルオブジェクト
     * @param id クリックしたのインタビューリスト
     * @return プレビューページのビュー名
    */
    @GetMapping("/manager/EditInterview")
    public String previewInterview(@RequestParam int id, Model model) {

        // インタビュー検索、結果を取得する
        SearchRes searchResult = interviewService.selectInterviewById(id);
        model.addAttribute("interviewData", searchResult.getInterviewList());

        return "/manager/EditInterview";
    } 

    /**  
     * インタビューリストをプレビューに移動する
     * 
     * @param model モデルオブジェクト
     * @param id 使用者のインタビューリスト
     * @return プレビューページのビュー名
    */
    @GetMapping("/manager/UserInterview")
    public String createUserInterview(@RequestParam int id, Model model) {
        
        // 使用者検索、結果を取得する
        SearchRes searchResult = interviewService.selectUserInterviewById(id);
        model.addAttribute("interviewData", searchResult.getInterviewList());
        
        return "/manager/UserInterview";
    } 

    /**  
     * インタビューリストを削除する
     * 
     * @param model モデルオブジェクト
     * @param id 削除するリストのID
     * @return BaseRes 削除成功メッセージ
    */
    @PostMapping("/manager/DelInterview")
    @ResponseBody
    public BaseRes delInterview(@RequestParam int id,Model model) {

        // インタビューリストを削除する
        CommonMessage del = interviewService.delInterview(id);

        return new BaseRes(del.getCode(), del.getMessage());
    }

    /**  
     * 編修を保存する
     * 
     * @param model モデルオブジェクト
     * @param saveReq 保存リストの資料
     * @return BaseRes 保存成功メッセージ
    */
    @PostMapping("/manager/SaveInterview")
    @ResponseBody
    public BaseRes updateInterview(@RequestBody SaveReq saveReq,Model model){

        // インタビューリストを保存する
        CommonMessage save = interviewService.updateInterview(saveReq);

        return new BaseRes(save.getCode(), save.getMessage());
    }

    /**  
     * 使用者新規を保存する
     * 
     * @param model モデルオブジェクト
     * @param saveReq 保存リストの資料
     * @return BaseRes 保存成功メッセージ
    */
    @PostMapping("/manager/SaveUserInterview")
    @ResponseBody
    public BaseRes updateUserInterview(@RequestBody SaveReq saveReq,Model model){

        // 使用者リストを保存する
        CommonMessage save = interviewService.updateUserInterview(saveReq);

        return new BaseRes(save.getCode(), save.getMessage());
    }

    /**  
     * インタビューリストレビュー
     * 
     * @param model モデルオブジェクト
     * @param id レビューリストのId
     * @return レビューページのビュー名
    */ 
    @GetMapping("/manager/ReviewInterview")
    public String reviewInterview(@RequestParam int id, Model model){

        // インタビュー検索、結果を取得する
        SearchRes searchResult = interviewService.selectInterviewById(id);
        model.addAttribute("interviewData", searchResult.getInterviewList());
        
        return "/manager/ReviewInterview";
    }

    /**  
     * 使用者リストレビュー
     * 
     * @param model モデルオブジェクト
     * @param id レビューリストのId
     * @return レビューページのビュー名
    */
    @GetMapping("/manager/ReviewUserInterview")
    public String reviewUserInterview(@RequestParam int id, Model model){

        // インタビュー検索、結果を取得する
        SearchRes searchResult = interviewService.selectUserInterviewById(id);
        model.addAttribute("interviewData", searchResult.getInterviewList());
        
        return "/manager/ReviewUserInterview";
    }

}
