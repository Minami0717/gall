const postId = document.getElementById('postId');
const writer = document.getElementById('writer');
const cmtPw = document.getElementById('cmtPw');
const content = document.getElementById('content');
const submit = document.querySelector('.submit');

submit.addEventListener('click', () => {
    if (!inputCheck()) { return; }

    const cmtData = {
        postId: postId.value,
        writer: writer.value,
        pw: cmtPw.value,
        content: content.value
    }

    fetch('/cmt', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cmtData),
    })
    .then(res => res.json())
    .then(data => console.log(data));
});

function inputCheck() {
    if (writer.value === '') {
        alert('닉네임을 입력하세요.');
        writer.focus();
        return false;
    }
    if (cmtPw.value === '') {
        alert('비밀번호를 입력하세요.');
        cmtPw.focus();
        return false;
    }
    if (cmtPw.value.length < 2) {
        alert('비밀번호를 최소 2자리 이상 입력하셔야 합니다. 쉬운 비밀번호는 타인이 수정 또는 삭제하기 쉬우니, 어려운 비밀번호를 입력해 주세요.');
        cmtPw.focus();
        return false;
    }
    if (content.value === '') {
        alert('내용을 입력하세요.');
        content.focus();
        return false;
    }
    return true;
}

// async function writeCmt() {
//     //POST 요청에 보낼 데이터 작성
//     const body = {
//         name: "John Doe",
//         username: "john.doe",
//         email: "john.doe@example.com",
//     };
//     try {
//         const response = await fetch("https://jsonplaceholder.typicode.com/users", {
//             method: "POST",
//             // headers: headers,
//             body: JSON.stringify(body)
//         });
//         const data = await response.json(); // 서버로부터 받은 데이터를 JSON 형태로 변환
//         console.log(data); // 변환된 데이터를 출력
//     } catch (error) {
//         console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
//     }
// }

$(function () {
    $("header").load("../header");
})

let sub = document.getElementsByTagName("#intro_box span");
if(sub.value === "없음")
    sub.style.color = "#999";

$(function() {
    $(".reTop button").on("click", function() {
        $("#tiv").toggle();

        if ($(".reTop button b").text() == "댓글닫기") {
            $(".reTop button b").text("댓글열기")
            $(".reTop button img").attr("src","/image/down.png")
        }
        else {
            $(".reTop button b").text("댓글닫기")
            $(".reTop button img").attr("src","/image/arrow-up.png")
        }
    });

    $(".reBot button").on("click", function() {
        $("#tiv").toggle();

        if ($(".reTop button b").text() == "댓글닫기") {
            $(".reTop button b").text("댓글열기")
            $(".reTop button img").attr("src","/image/down.png")
        }
    });
});

cmtPw.value = generateRandomCode();

function generateRandomCode() {
    let str = ''
    for (let i = 0; i < 4; i++) {
        str += Math.floor(Math.random() * 10)
    }
    return str
}