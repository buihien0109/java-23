const heading = document.getElementById("heading");
console.log(heading);

const headingCss = document.querySelector("#heading");
console.log(headingCss);

// Thay đổi css -> inline style
heading.style.color = "red";
heading.style.backgroundColor = "black";

// Thay đổi text
heading.innerHTML = "<span>Xin chào</span>"
heading.innerText = "Xin chào các bạn"

const paraList = document.querySelectorAll("p");
console.log(paraList);

// for (let i = 0; i < paraList.length; i++) {
//     paraList[i].style.color = "blue";
// }

// Array.from(paraList).forEach(p => {
//     p.style.color = "blue";
// });

Array.from(paraList).map(p => p.style.color = "blue");

// Tạo thẻ p có nd là "Para 4" vào sau thẻ para 3
const p4 = document.createElement("p");
p4.innerText = "Para 4";
console.log(p4);

// document.body.appendChild(p4);
// document.body.prepend(p4);

const p2 = document.querySelector(".para");
document.body.insertBefore(p4, p2);

const menu = [
    {
        label: "Facebook",
        url: "https://facebook.com"
    },
    {
        label: "Google",
        url: "https://google.com"
    },
    {
        label: "Youtube",
        url: "https://youtube.com"
    }
]

// Tạo các thẻ a tương ứng với từng phần tử trong mảng menu
// Nội dung của thẻ a là label
// href của thẻ a là url

menu.forEach(item => {
    const link = document.createElement("a");
    link.innerText = item.label;
    link.href = item.url;
    document.body.appendChild(link);
})

// Xóa p1
p1 = document.querySelector("p");
document.body.removeChild(p1);

// Thay thế p2 thành button "Click me"
const btn = document.createElement("button");
btn.innerText = "Click me";

document.body.replaceChild(btn, p2);