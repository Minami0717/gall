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