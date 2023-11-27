const submit = document.getElementById('submit');
const title = document.getElementById('title');
const content = document.getElementById('content');
const postId = document.getElementById('postId');
const gallId = document.getElementById('gallId');

submit.addEventListener('click', () => {
    if (!inputCheck()) { return; }

    const postData = {
        postId: postId.value,
        title: title.value.trim(),
        content: content.value.trim()
    }

    updPost(postData).then(() => location.href = '/board/' + gallId.value);
});

async function updPost(postData) {
    try {
        await fetch("/board", {
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
    if (content.value.trim() === '') {
        alert('내용을 입력하세요.');
        content.focus();
        return false;
    }
    return true;
}