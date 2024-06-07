document.querySelector("#getPhotoData").src = document.querySelector("#photoData").innerText;
document.querySelector("#getVideoData").src = document.querySelector("#videoData").innerText;
document.querySelector("#getLifeVideoData").src = document.querySelector("#lifeVideoData").innerText;

$(function () {

    // backBtnにクリックイベントハンドラを追加する
    $(".backBtn").click(function (){

        // IDを取得する
        const idClass = document.querySelector(".id")
        let id = idClass.value

        $.ajax({
            url:`/manager/EditInterview?id=${id}`,
            type: "GET",
            success: function(){
                window.location.href = `/manager/EditInterview?id=${id}`;
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

