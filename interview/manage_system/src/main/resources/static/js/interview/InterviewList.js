$(function() {
    const genderId = document.querySelector("#gender")
    const ageId = document.querySelector("#age")
    const classId = document.querySelector("#idClass")
    const nameClass = document.querySelector(".name")
    
    // reviewBtnにクリックイベントハンドラを追加する
    $(".searchBtn").click(function (){

        // 初期の検査条件を設定する
        let age = ageId.value;
        let searchReq = {  
            gender: genderId.value,
            age1: age.split("~")[0],
            age2: age.split("~")[1],
            idClass: classId.value,
            name: nameClass.value,
            currentPage: 1
        };
        
        // 検索と画面更新
        $.ajax({
            url:"/manager/SearchInterview",
            type: "POST",
            data: JSON.stringify(searchReq),
            contentType: "application/json",
            success: function(res){
                $("#search").html(res);
            }
        });
    })

    // createBtnにクリックイベントハンドラを追加する
    $(".createBtn").click(function (){

        // 使用者のIDを設定する
        let id = 1;

        // IDをコントロールに送信すると画面更新
        $.ajax({
            url:`/manager/UserInterview?id=${id}`,
            type: "GET",
            success: function(){
                window.location.href = `/manager/UserInterview?id=${id}`; // 使用者の編修画面を更新する
            }
        });
    })

    // formAreaにクリックイベントハンドラを追加する
    $(".formArea").on('click', '.previewBtn', function(e){

        // クリックしたのボンタンのIDを取得する
        let id = e.target.value

        // IDをコントロールに送信すると画面更新
        $.ajax({
            url:`/manager/EditInterview?id=${id}`,
            type: "GET",
            success: function(){   
                window.location.href = `/manager/EditInterview?id=${id}`; // 編修画面を更新する
            }
        });
    }),

    // delBtnにクリックイベントハンドラを追加する
    $(".formArea").on('click', '.delBtn', function(e) {

        // インタビュー削除確認
        let check = confirm("インタビュー削除確認");
        if(check){
            alert("インタビュー削除成功した");
        } else {
            return
        }

        // クリックしたのボンタンのIDを取得する
        let id = e.target.value;

        // IDをコントロールに送信する
        $.ajax({
            url:`/manager/DelInterview?id=${id}`, 
            type: "POST",
            cache: false,
            dataType: "json",
            success: function(){
                history.go(0); // ページ更新
            }
        });
    }),

    // nextBtnにクリックイベントハンドラを追加する
    $(".nextBtn").click(function () {

        
        // 検索条件を設定する
        let age = ageId.value;
        let searchReq = {
            gender: genderId.value,
            age1: age.split("~")[0],
            age2: age.split("~")[1],
            idClass: classId.value,
            name: nameClass.value,
            currentPage: Number(document.getElementById('pageNum').innerText) + 1 // ページ数を設定する
        };
        console.log(document.getElementById('pageNum'))

        let pages = Number(document.getElementById("pages").innerText) + 1
        if (searchReq.currentPage === pages) {
            return alert("次のページはありません")
        }
        // ページ数に応じて画面を更新する
        $.ajax({
            url:"/manager/SearchInterview",
            type: "POST",
            data: JSON.stringify(searchReq),
            contentType: "application/json",
            success: function(res){
                $("#search").html(res);
            }
        });
    })

    // backBtnにクリックイベントハンドラを追加する
    $(".backBtn").click(function () {

        // 検索条件を設定する
        let age = ageId.value;
        let searchReq = {
            gender: genderId.value,
            age1: age.split("~")[0],
            age2: age.split("~")[1],
            idClass: classId.value,
            name: nameClass.value,
            currentPage: Number(document.getElementById('pageNum').innerText) - 1 // ページ数を設定する
        };
            console.log(document.getElementById('pageNum'))
        if (searchReq.currentPage === 0) {
            return alert("次のページはありません")
        }
        // ページ数に応じて画面を更新する
        $.ajax({
            url:"/manager/SearchInterview",
            type: "POST",
            data: JSON.stringify(searchReq),
            contentType: "application/json",
            success: function(res){
                $("#search").html(res);
            }
        });
    })
}) 
