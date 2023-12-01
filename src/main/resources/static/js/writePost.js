import generateRandomCode, {loadingStart} from "./common.js";

const pw = document.getElementById('pw');
const img = document.getElementById('img');
const content = document.getElementById('content');
const submitBtn = document.getElementById('submit-btn');
const gallId = document.getElementById('gall-id');
const writer = document.getElementById('writer');
const title = document.getElementById('title');
const key = document.getElementById('api-key');
const keyValue = key.value;
key.remove();

img.addEventListener('change', () => {
    const imgs = img.files;
    if (content.innerText.trim() === '') { content.innerHTML = ''; }

    for (let i = 0; i < imgs.length; i++) {
        content.innerHTML += `
            <p><br></p>
            <p><img src=${URL.createObjectURL(imgs[i])} style="max-width: 90%"></p>
            <p><br></p><p><br></p>
        `;
    }
})

submitBtn.addEventListener('click', () => {
    if (!inputCheck()) { return; }

    uploadImgs().then(imgUrls => {
        const postData = {
            gallId: gallId.value,
            title: title.value.trim(),
            content: content.innerText.trim(),
            writer: writer.value.trim(),
            pw: pw.value.trim(),
            imgUrls: imgUrls
        }

        writePost(postData).then(() => location.href = `/gallery/${gallId.value}`);
    });
})

async function uploadImgs() {
    const imgUrls = [];
    if (img.files.length === 0) { return imgUrls; }

    loadingStart();
    const formData = new FormData();
    const imgs = img.files;

    for (let i = 0; i < imgs.length; i++) {
        formData.append('image', imgs[i])
        await uploadImg(formData).then(res => imgUrls.push(res.data.url));
    }
    return imgUrls;
}

async function writePost(postData) {
    try {
        await fetch("/gallery/write", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        });
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}

async function uploadImg(img) {
    try {
        const res = await fetch(`https://api.imgbb.com/1/upload?key=${keyValue}&name=${crypto.randomUUID()}`, {
            method: 'POST',
            body: img
        });

        return await res.json();
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}

function inputCheck() {
    if (writer.value.trim() === '') {
        alert('닉네임을 입력하세요.');
        writer.focus();
        return false;
    }
    if (pw.value.trim() === '') {
        alert('비밀번호를 입력하세요.');
        pw.focus();
        return false;
    }
    if (pw.value.trim().length < 4) {
        alert('비밀번호를 최소 4자리 이상 입력하셔야 합니다. 쉬운 비밀번호는 타인이 수정 또는 삭제하기 쉬우니, 어려운 비밀번호를 입력해 주세요.');
        pw.focus();
        return false;
    }
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

pw.value = generateRandomCode();
document.getElementById('loading-text').innerText = 'Image Uploading...'