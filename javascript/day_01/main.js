console.log("Hello World!");

let name;
console.log(typeof name); // undefined
name = "Bùi Hiên"; // string
let age = 20; // number
age = 21; // number
let status = true; // boolean

const PI = 3.14;
// PI = 3.15; // Error

name = "Bùi Hiên"
let year = 1997
let message = "Xin chào các bạn. Mình tên là " + name + ", năm nay mình " + (2024 - year) + " tuổi."
console.log(message);

// Template String (Template Literal - ES6)
let message2 = `Xin chào các bạn. Mình tên là ${name}, năm nay mình ${2024 - year} tuổi.`
console.log(message2);

// Function
// C1: Function declaration
/* Java
public int sum(int a, int b) {
    return a + b;
}
*/
function sum(a, b) {
    return a + b;
}

// C2: Function expression
let sum2 = function (a, b) {
    return a * b;
}

// C3: Arrow function - ES6 || Lambda expression - Java
let sum3 = (a, b) => {
    let title = "Tính tổng";
    return a + b;
}

console.log(sum(4, "5"));
console.log(sum2(4, "5"));
console.log(sum3(4, typeof 5));
// console.log(title);

{
    let name1 = "Bùi Hiên";
    console.log(name1); // Bùi Hiên
}

// console.log(name1) // Lỗi

let money = 10000

switch (money) {
    case 12000: {
        console.log("Cà phê sữa");
    }
    case 10000: {
        console.log("Cà phê đá");
    }
    case 8000: {
        console.log("String dâu");
    }
    case 2000: {
        console.log("Trà đá");
    }
    default: {
        console.log("Không có đồ uống phù hợp");
    }
}

for (let i = 0; i < 10; i++) {
    console.log(i);
}

let i = 0;
while (i < 10) {
    console.log(i);
    i++;
}

let j = 0;
do {
    console.log(j);
    j++;
} while (j < 10);