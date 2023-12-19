import {loadingStart} from "./common.js";

const submitBtn = document.getElementById('submit-btn');
const title = document.getElementById('title');
const content = document.getElementById('content');
const postId = document.getElementById('post-id');
const gallId = document.getElementById('gall-id');

submitBtn.addEventListener('click', () => {
    if (!inputCheck()) { return; }

    loadingStart();
    const postData = {
        postId: postId.value,
        title: title.value.trim(),
        content: content.innerHTML.trim()
    }

    updPost(postData).then(() => location.href = `/gallery/${gallId.value}`);
});

async function updPost(postData) {
    try {
        await fetch("/gallery", {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        });
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}

function inputCheck() {
    if (title.value.trim() === '') {
        alert('제목을 입력하세요.');
        title.focus();
        return false;
    }
    if (content.innerHTML.trim() === '') {
        alert('내용을 입력하세요.');
        content.focus();
        return false;
    }
    return true;
}