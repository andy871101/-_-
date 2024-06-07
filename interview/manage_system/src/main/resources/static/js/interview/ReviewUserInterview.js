// 初期
document.querySelector("#getPhotoData").src = document.querySelector("#photoData").innerText;
document.querySelector("#getVideoData").src = document.querySelector("#videoData").innerText;
document.querySelector("#getLifeVideoData").src = document.querySelector("#lifeVideoData").innerText;

$(function () {

    // backBtnにクリックイベントハンドラを追加する
    $(".backBtn").click(function (){

        // IDを設定する
        let id = 1;
        $.ajax({
            url:`/manager/UserInterview?id=${id}`,
            type: "GET",
            success: function(){
                window.location.href = `/manager/UserInterview?id=${id}`;
            }
        });
    })

    // closeBtnにクリックイベントハンドラを追加する
    $(".closeBtn").click(function (){
        let check = confirm("ホームページに戻ります");
        if (check) {
        } else {
            return
        }
        $.ajax({
            url:"/manager/InterviewList",
            type: "GET",
            cache: false,
            success: function(){
                window.location.href = "/manager/InterviewList";
            }
        });
    })
})