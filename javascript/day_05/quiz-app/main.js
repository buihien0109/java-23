const questions = [
    {
        title: "1 + 1 bằng bao nhiêu?",
        choices: ["1", "2", "3", "4"],
        answer: "2"
    },
    {
        title: "Số nào sau đây là số nguyên tố?",
        choices: ["22", "4", "25", "11"],
        answer: "11"
    },
    {
        title: "Căn bậc hai của 81 là bao nhiêu?",
        choices: ["7", "8", "9", "10"],
        answer: "9"
    },
    {
        title: "15% của 100 bằng bao nhiêu?",
        choices: ["10", "15", "20", "25"],
        answer: "15"
    },
    {
        title: "Số nào sau đây chia hết cho 3?",
        choices: ["14", "22", "27", "32"],
        answer: "27"
    }
];

let currentQuestionIndex = 0;
let score = 0;
let yourAnswers = [];

const titleQuestionEl = document.querySelector("#question p");
const choicesEl = document.querySelector(".choices");

const renderQuestion = () => {
    // Lấy thông tin câu hỏi hiện tại
    const currentQuestion = questions[currentQuestionIndex];

    // Hiển thị tiêu đề
    titleQuestionEl.innerText = `Câu ${currentQuestionIndex + 1}: ${currentQuestion.title}`;

    // Hiển thị các lựa chọn
    let choicesHtml = "";
    currentQuestion.choices.forEach((choice, index) => {
        choicesHtml += `
            <div class="choice-item">
                <input type="radio" name="choice" id="${index}" value="${choice}" />
                <label for="${index}">${choice}</label>
            </div>
        `;
    })
    choicesEl.innerHTML = choicesHtml;
}


const btnNext = document.getElementById("btn-next");
const btnFinish = document.getElementById("btn-finish");

btnNext.addEventListener("click", () => {
    // Kiểm tra xem người dùng đã chọn câu trả lời chưa
    const selectedChoice = document.querySelector("input[name='choice']:checked");
    if(!selectedChoice) {
        alert("Bạn chưa chọn câu trả lời");
        return;
    }

    // Lưu lại đáp án người dùng chọn
    yourAnswers.push(selectedChoice.value);
    console.log(yourAnswers);

    // Next câu hỏi
    currentQuestionIndex++;
    renderQuestion();

    // Khi đến câu hỏi cuối cùng thì ẩn nút Next, hiển thị nút Finish
});

btnFinish.addEventListener("click", () => {
    
});

renderQuestion()