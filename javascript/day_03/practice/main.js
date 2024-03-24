// 1. Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
const heading = document.getElementById("heading");
heading.style.color = "red";
heading.style.textTransform = "uppercase"

// 2. Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
const paraList = document.querySelectorAll(".para");
Array.from(paraList).map(p => {
    p.style.color = "blue";
    p.style.fontSize = "20px";
});

// 3. Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
// const a = document.createElement("a");
// a.innerText = "Facebook";
// a.href = "https://facebook.com";
const div = document.querySelector("div");
// document.body.insertBefore(a, div);
// div.insertAdjacentElement("beforebegin", a);

div.insertAdjacentHTML("beforebegin", "<a href='https://facebook.com'>Facebook</a>");

// 4. Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
const title = document.getElementById("title");
title.innerText = "Đây là thẻ tiêu đề";

// 5. Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)

// 6. Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”

// 7. Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó

// 8. Xóa thẻ có class=“para-1”