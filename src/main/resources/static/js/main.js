let main = document.getElementById("main");
let minor = document.getElementById("minor");
let mini = document.getElementById("mini");
let a_main = document.querySelector("#inline a:first-child");
let a_minor = document.querySelector("#inline a:nth-child(2)");
let a_mini = document.querySelector("#inline a:last-child");
let ran = Math.floor(Math.random() * 3);

function change() {
    switch (ran) {
        case 0:
            a_main.classList.add("on");
            a_minor.classList.remove("on");
            a_mini.classList.remove("on");
            main.style.display = 'block';
            minor.style.display = 'none';
            mini.style.display = 'none';
            break;
        case 1:
            a_minor.classList.add("on");
            a_main.classList.remove("on");
            a_mini.classList.remove("on");
            main.style.display = 'none';
            minor.style.display = 'block';
            mini.style.display = 'none';
            break;
        case 2:
            a_mini.classList.add("on");
            a_main.classList.remove("on");
            a_minor.classList.remove("on");
            main.style.display = 'none';
            minor.style.display = 'none';
            mini.style.display = 'block';
            break;

        default:
            break;
    }
}

change();

function f_main() {
    ran = 0;
    change();
}

function f_minor() {
    ran = 1;
    change();
}

function f_mini() {
    ran = 2;
    change();
}