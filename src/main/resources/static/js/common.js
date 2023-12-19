export default function generateRandomCode() {
    let str = ''
    for (let i = 0; i < 4; i++) {
        str += Math.floor(Math.random() * 10)
    }
    return str
}

export function loadingStart() {
    const loading = document.querySelector('#loading');
    loading.style.display = 'block';
}

export function loadingEnd() {
    const loading = document.querySelector('#loading');
    loading.style.display = 'none';
}

export function generateRandomFileName() {
    const characters = 'abcdefghijklmnopqrstuvwxyz0123456789';
    let result = '';
    for (let i = 0; i < 10; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        result += characters.charAt(randomIndex);
    }
    return result;
}