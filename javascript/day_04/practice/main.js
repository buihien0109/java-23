// Yêu cầu 1:
// Khi nhấn vào button “Change content” hiển thị một nội dung quote bất kỳ
const p = document.getElementById("text");
const changeContent = () => {
    const quotes = [
        "The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela",
        "The way to get started is to quit talking and begin doing. - Walt Disney",
        "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
        "If life were predictable it would cease to be life, and be without flavor. - Eleanor Roosevelt",
    ]
    const randomIndex = Math.floor(Math.random() * quotes.length);
    p.innerText = quotes[randomIndex];
}

// Yêu cầu 2:
// Khi nhấn vào button “Change color” thì thay đổi màu của thẻ p (sử dụng màu HEX - cần viết 1 function để random màu HEX)
const randomHexColor = () => {
    const hexChars = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += hexChars[Math.floor(Math.random() * hexChars.length)];
    }
    return color;
}

const btn2 = document.getElementById("btn-2");
btn2.onclick = () => {
    p.style.color = randomHexColor();
}

// Yêu cầu 3:
// Khi nhấn vào button “Change background” thì thay đổi màu background-color của thẻ p (sử dụng màu RGB - cần viết 1 function để random màu RGB)
const randomRgbColor = () => {
    // Tạo ba số ngẫu nhiên từ 0 đến 255
    const r = Math.floor(Math.random() * 256);  // Red
    const g = Math.floor(Math.random() * 256);  // Green
    const b = Math.floor(Math.random() * 256);  // Blue

    // Trả về chuỗi màu dạng "rgb(r, g, b)"
    return `rgb(${r}, ${g}, ${b})`;
}

const btn3 = document.getElementById("btn-3");
btn3.addEventListener("click", () => {
    p.style.backgroundColor = randomRgbColor();
})