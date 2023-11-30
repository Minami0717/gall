// import {generateRandomCode} from "common";
const postId = document.getElementById('postId');
const writer = document.getElementById('writer');
const cmtPw = document.getElementById('cmtPw');
const content = document.getElementById('content');
const recoBtn = document.getElementById('reco-btn');
const decoBtn = document.getElementById('deco-btn');
const recoNum1 = document.getElementById('reco-num1');
const recoNum2 = document.getElementById('reco-num2');
const decoNum = document.getElementById('deco-num');
const submit = document.querySelector('.submit');

cmtPw.value = generateRandomCode();
recoBtn.addEventListener('click', () => {
    upRecoOrDeco('reco').then((res) => {
        if (res.recoNum === -1) {
            alert('추천은 1일 1회만 가능합니다.')
            return;
        }

        recoNum1.innerHTML = '추천 ' + res.recoNum;
        recoNum2.innerHTML = res.recoNum;
    })
})

decoBtn.addEventListener('click', () => {
    upRecoOrDeco('deco').then((res) => {
        if (res.recoNum === -1) {
            alert('비추천은 1일 1회만 가능합니다.')
            return;
        }

        decoNum.innerHTML = res.decoNum;
    })
})

submit.addEventListener('click', () => {
    if (!inputCheck()) { return; }

    const cmtData = {
        postId: postId.value,
        writer: writer.value.trim(),
        pw: cmtPw.value.trim(),
        content: content.value.trim()
    }
    content.value = '';
    cmtPw.value = generateRandomCode();
    content.focus();

    writeCmt(cmtData)
        .then(() => getCmtList()
            .then(cmtList => createCmtLi(cmtList)));
});

content.addEventListener('keydown', (e) => {
    if (e.key === 'Enter' && !e.shiftKey) {
        e.preventDefault();
        submit.click();
    }
});

async function upRecoOrDeco(mode) {
    try {
        const res = await fetch("/gallery/" + postId.value + '?mode=' + mode, {
            method: 'PATCH'
        });

        return await res.json();
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}

function createCmtLi(cmtList) {
    let tiv = document.getElementById('tiv');
    let ul;
    if (tiv == null) {
        tiv = document.createElement('div');
        tiv.setAttribute('id', 'tiv');
        ul = document.createElement('ul');

        appendLi();
        tiv.append(ul);

        tiv.innerHTML += `
            <div class="reBot right">
                <a href=#header><b>본문 보기</b></a> &nbsp;|&nbsp;
                <button><b>댓글닫기</b> <img src="/image/arrow-up.png"></button> &nbsp;|&nbsp;
                <a href=#reply><b>새로고침</b></a>
            </div>
        `;
        document.getElementById('text').before(tiv);
    } else {
        ul = document.querySelector('#tiv ul');
        ul.innerHTML = '';
        appendLi();
    }

    const cmtNum = document.getElementsByClassName('cmtNum');
    cmtNum.item(0).innerHTML = '댓글 ' + cmtList.length;
    cmtNum.item(1).innerHTML = cmtList.length;

    function appendLi() {
        cmtList.forEach(c => {
            const li = document.createElement('li');
            li.innerHTML = `
            <span>${c.writer}(${c.ip})</span>
            <span>${c.content}</span>
            <span>${c.createdAt}</span>
        `;
            ul.append(li);
        });
    }
}
async function writeCmt(cmtData) {
    try {
        await fetch("/cmt", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cmtData)
        });
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}

async function getCmtList() {
    try {
        const response = await fetch("/cmt?postId=" + postId.value);
        return await response.json(); // 서버로부터 받은 데이터를 JSON 형태로 변환
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}

// async function getPostView() {
//     const baseUrl = '/board/view';
//     const params = {
//         id: document.getElementById('gallId').value,
//         no: postId.value
//     };
//
//     const queryString = new URLSearchParams(params).toString();
//     const reqUrl = `${baseUrl}?${queryString}`;
//
//     try {
//         const response = await fetch(reqUrl);
//         // const data = await response.json(); // 서버로부터 받은 데이터를 JSON 형태로 변환
//         // console.log(data); // 변환된 데이터를 출력
//     } catch (error) {
//         console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
//     }
// }

function inputCheck() {
    if (writer.value.trim() === '') {
        alert('닉네임을 입력하세요.');
        writer.focus();
        return false;
    }
    if (cmtPw.value.trim() === '') {
        alert('비밀번호를 입력하세요.');
        cmtPw.focus();
        return false;
    }
    if (cmtPw.value.trim().length < 2) {
        alert('비밀번호를 최소 2자리 이상 입력하셔야 합니다. 쉬운 비밀번호는 타인이 수정 또는 삭제하기 쉬우니, 어려운 비밀번호를 입력해 주세요.');
        cmtPw.focus();
        return false;
    }
    if (content.value.trim() === '') {
        alert('내용을 입력하세요.');
        content.focus();
        return false;
    }
    return true;
}

// let sub = document.getElementsByTagName("#intro_box span");
// if(sub.value === "없음")
//     sub.style.color = "#999";

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

function generateRandomCode() {
    let str = ''
    for (let i = 0; i < 4; i++) {
        str += Math.floor(Math.random() * 10)
    }
    return str
}