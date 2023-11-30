// import {generateRandomCode} from "./common";
function generateRandomCode() {
    let str = ''
    for (let i = 0; i < 4; i++) {
        str += Math.floor(Math.random() * 10)
    }
    return str
}

const postPw = document.getElementById('postPw');
postPw.value = generateRandomCode();

const img = document.getElementById('img');
img.addEventListener('change', () => {
    const formData = new FormData()
    formData.append('image', img.files[0])

    uploadImg(formData).then(res => console.log(res))
})

async function uploadImg(img) {
    try {
        const res = await fetch("https://api.imgbb.com/1/upload?" + 'key=' + 'e4a9c0950d6ccca805751cda8bbaea91', {
            method: 'POST',
            body: img
        });

        return await res.json();
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}