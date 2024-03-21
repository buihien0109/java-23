// Khai báo array rỗng
let arr = [];

// Khai báo array
let number = [];

// Gán giá trị cho các phần tử của array thông qua chỉ số
number[0] = 1;
number[1] = "Bùi Hiên";
number[2] = true;
number[10] = false;
console.log(number);

// Khai báo và khởi tạo giá trị cho array
const myArr = [1, 2, 3, 4, true, false, "Nguyễn Văn A"];
console.log(myArr);

myArr[0] = 100;
console.log(myArr);

// Method của array
// pop(): Xóa phần tử cuối cùng của array
// push(): Thêm phần tử vào cuối array
// shift(): Xóa phần tử đầu tiên của array
// unshift(): Thêm phần tử vào đầu array
// splice(): Xóa hoặc thêm phần tử vào array
myArr.splice(4, 1, 100, 200, 300)
console.log(myArr);

const numbers = [4, 2, 6, 3, 7, 11, 22];
console.log(numbers.sort((a, b) => a - b));

let message = "Xin chào các bạn";
let words = message.split(" ");
console.log(words);

// Thủ công
let newNumbers = [];
numbers.forEach(e => {
    newNumbers.push(e * 2);
});
console.log(newNumbers);

const newNumbersMap = numbers.map(e => e * 2);
console.log(newNumbersMap);

// Tìm các giá trị là số chẵn
let mangChan = [];
numbers.forEach(e => {
    if (e % 2 === 0) {
        mangChan.push(e);
    }
});
const mangChan2 = numbers.filter(e => e % 2 === 0);
const result = numbers.find(e => e > 10);
console.log(result)