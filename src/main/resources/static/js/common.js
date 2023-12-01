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