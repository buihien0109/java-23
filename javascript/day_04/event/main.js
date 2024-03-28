// Sử dụng HTML-attribute
const sayHello = () => {
    alert("Xin chào các bạn 1")
}

// Sử dụng DOM property
const btn2 = document.getElementById("btn2")
btn2.onclick = () => {
    alert("Xin chào các bạn 2")
}

// Sử dụng addEventListener
const btn3 = document.getElementById("btn3")
btn3.addEventListener("click", () => {
    alert("Xin chào các bạn 3")
})