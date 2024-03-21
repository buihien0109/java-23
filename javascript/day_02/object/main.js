let user = {
    name: "Nguyễn Văn A",
    age: 23,
    email: "abc@gmail.com",
    languges: ["PHP", "JavaScript", "Python"],
    address: {
        city: "Hà Nội",
        district: "Cầu Giấy",
        street: "Duy Tân"
    },
    eat: function (food) {
        console.log(`Mình đang ăn ${food}`)
    },
    sleep(hours) {
        console.log(`Mình ngủ ${hours} giờ`)
    },
    work(company) {
        console.log(`Mình đang làm việc tại ${company}`)
    }
}

console.log(user.email);
console.log(user.languges[1]);
console.log(user.address.street);
user.sleep(8);
user.work("FPT Software");


// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// 1. In ra thông tin các sản phẩm trong giỏ hàng theo cấu trúc sau
// Tên - Giá - Thương Hiệu - Số lượng
// Ví dụ : OPPO Find X3 Pro - 19500000 - OPPO - 3
const printProduct = (products) => {
    products.forEach(p => {
        console.log(`${p.name} - ${p.price} - ${p.brand} - ${p.count}`)
    });
}
printProduct(products);

// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
// Tổng tiền mỗi sản phẩm = price * count // Áp dụng reduce
const totalMoney = (products) => {
    return products.reduce((total, p) => total + (p.price * p.count), 0);
}

// 3. Tìm các sản phẩm của thuơng hiệu "Apple"
const findByBrand = (products, brand) => {
    return products.filter(p => p.brand === brand);
}