const confirmBtn = document.getElementById('confirm');
const pw = document.getElementById('pw');
const postId = document.getElementById('postId');
const gallId = document.getElementById('gallId');

pw.focus();
confirmBtn.addEventListener('click', () => {
    if (!inputCheck()) { return; }

    const pwData = {
        postId: postId.value,
        pw: pw.value.trim()
    }

    pwCheck(pwData).then(res => {
        if (res === false) {
            alert('비밀번호가 맞지 않습니다. 다시 시도해 주세요.');
            pw.focus();
            return;
        }

        const urlParams = new URL(location.href).searchParams;
        const mode = urlParams.get('mode');
        console.log(mode);

        if (mode === 'del') {
            if (confirm('게시글을 삭제하면 복구가 안됩니다. 삭제하시겠습니까?')) {
                delPost().then(() => {
                    alert('게시글이 삭제되었습니다.');
                    location.href = '/gallery/' + gallId.value;
                });
                return;
            }
            return;
        }

        location.href = '/gallery/' + gallId.value + '/upd/' + postId.value;
    });
});

pw.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        confirmBtn.click();
    }
});

async function delPost() {
    try {
        await fetch("/gallery/" + postId.value, {
            method: 'DELETE',
        });
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}

async function pwCheck(pwData) {
    try {
        const res = await fetch("/gallery/pw", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pwData)
        });

        return await res.json();
    } catch (error) {
        console.error("Error fetching data:", error);  // 오류 발생 시 메시지 출력
    }
}

function inputCheck() {
    if (pw.value.trim() === '') {
        alert('비밀번호를 입력하세요.');
        pw.focus();
        return false;
    }
    if (pw.value.trim().length < 2) {
        alert('비밀번호를 최소 2자리 이상 입력하셔야 합니다.');
        pw.focus();
        return false;
    }
    return true;
}