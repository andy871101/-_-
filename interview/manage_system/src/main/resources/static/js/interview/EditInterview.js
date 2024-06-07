$(function () {

    let videoData = document.querySelector("#video").value
    let lifeVideoData = document.querySelector("#lifeVideo").value

    // saveBtnにクリックイベントハンドラを追加する
    $(".saveBtn").click(function (){
        
        videoData = document.querySelector("#video").value
        // リンクチェック
        if (videoData !== "" && !videoData.includes("https://www.youtube.com") && !videoData.includes("https://youtu.be")){
            return alert("youtubeのリンクを入力してください")
        }

        // 入力リンクに応じて正しいリンクを設定する
        let checkURL = "/watch?v="
        let isContained = videoData.includes(checkURL)
        if (isContained) {
            $("#video").val(videoData.replace("/watch?v=", "/embed/").split("&ab_")[0])
        } else if(videoData !== "" && !videoData.includes("embed")){
            $("#video").val(videoData.slice(0, 8) + "www.youtube.com/embed" + videoData.slice(16))
        }

        lifeVideoData = document.querySelector("#lifeVideo").value

        // リンクチェック
        if (lifeVideoData !== "" && !lifeVideoData.includes("https://www.youtube.com") && !lifeVideoData.includes("https://youtu.be")){
            return alert("youtubeのリンクを入力してください")
        }

        // 入力リンクに応じて正しいリンクを設定する
        isContained = lifeVideoData.includes(checkURL)
        if (isContained) {
            $("#lifeVideo").val(lifeVideoData.replace("/watch?v=", "/embed/").split("&ab_")[0])
        } else if (lifeVideoData !== "" && !lifeVideoData.includes("embed")){
            $("#lifeVideo").val(lifeVideoData.slice(0, 8) + "www.youtube.com/embed" + lifeVideoData.slice(16))
        }

        // 保存資料を得る
        let idClass = document.querySelector(".id")
        let titleClass = document.querySelector("#title")
        let profileClass = document.querySelector("#profile")
        let photoClass = document.querySelector("#photo")
        let videoClass = document.querySelector("#video")
        let lifeVideoClass = document.querySelector("#lifeVideo")
        let saveData = {  
            id: idClass.value,
            title: titleClass.value,
            profile: profileClass.value,
            photo: photoClass.value,
            video: videoClass.value,
            lifeVideo: lifeVideoClass.value
        };
        alert("編修成功");

        // 保存と画面更新
        $.ajax({
            url:"/manager/SaveInterview",
            type: "POST",
            data: JSON.stringify(saveData),
            contentType: "application/json",
            success: function(){
                window.location.href = "/manager/InterviewList"; // インタビューリストに戻る
            }
        });
    })

    // reviewBtnにクリックイベントハンドラを追加する
    $(".reviewBtn").click(function (){

        videoData = document.querySelector("#video").value
        // リンクチェック
        if (videoData !== "" && !videoData.includes("https://www.youtube.com") && !videoData.includes("https://youtu.be")){
            return alert("youtubeのリンクを入力してください")
        }

        // 入力リンクに応じて正しいリンクを設定する
        let checkURL = "/watch?v="
        let isContained = videoData.includes(checkURL)
        if (isContained) {
            $("#video").val(videoData.replace("/watch?v=", "/embed/").split("&ab_")[0])
        } else if(videoData !== "" && !videoData.includes("embed")){
            $("#video").val(videoData.slice(0, 8) + "www.youtube.com/embed" + videoData.slice(16))
        }

        lifeVideoData = document.querySelector("#lifeVideo").value

        // リンクチェック
        if (lifeVideoData !== "" && !lifeVideoData.includes("https://www.youtube.com") && !lifeVideoData.includes("https://youtu.be")){
            return alert("youtubeのリンクを入力してください")
        }

        // 入力リンクに応じて正しいリンクを設定する
        isContained = lifeVideoData.includes(checkURL)
        if (isContained) {
            $("#lifeVideo").val(lifeVideoData.replace("/watch?v=", "/embed/").split("&ab_")[0])
        } else if (lifeVideoData !== "" && !lifeVideoData.includes("embed")){
            $("#lifeVideo").val(lifeVideoData.slice(0, 8) + "www.youtube.com/embed" + lifeVideoData.slice(16))
        }

        // リスト資料を得る
        let idClass = document.querySelector(".id")
        let titleClass = document.querySelector("#title")
        let profileClass = document.querySelector("#profile")
        let photoClass = document.querySelector("#photo")
        let videoClass = document.querySelector("#video")
        let lifeVideoClass = document.querySelector("#lifeVideo")
        let saveData = {  
            id: idClass.value,
            title: titleClass.value,
            profile: profileClass.value,
            photo: photoClass.value,
            video: videoClass.value,
            lifeVideo: lifeVideoClass.value
        };
        
        // 保存後に画面更新
        $.ajax({
            url:"/manager/SaveInterview",
            type: "POST",
            data: JSON.stringify(saveData),
            contentType: "application/json",
            success: function(){

                // リストのIDを得る
                let id = idClass.value

                // 画面更新
                $.ajax({
                    url:`/manager/ReviewInterview?id=${id}`,
                    type: "GET",
                    success: function(){
                        window.location.href = `/manager/ReviewInterview?id=${id}`;
                    }
                });
            }
        });
    })
})

