const API_URL = "http://localhost:3000/todos";
let todos = [];

const ulEl = document.querySelector("ul")
const renderTodos = (todos) => {
    ulEl.innerHTML = "";
    if (todos.length === 0) {
        ulEl.insertAdjacentHTML("afterbegin", "<li>Danh sách công việc trống</li>");
        return;
    }

    let html = "";
    todos.forEach(todo => {
        html += `
                <li>
                    <input 
                        type="checkbox" 
                        ${todo.status ? "checked" : ""} 
                        onchange="toggleStatus(${todo.id})"
                    />
                    <span class=${todo.status ? "active" : ""}>${todo.title}</span>
                    <button onclick="editTodo(${todo.id})">Edit</button>
                    <button onclick="deleteTodo(${todo.id})">Delete</button>
                </li>
            `;
    })
    ulEl.innerHTML = html;
}

// Lấy danh sách todo trên server
const getAllTodos = async () => {
    try {
        const response = await axios.get(API_URL);
        console.log(response)

        todos = response.data; // Lưu lại ds todo vào biến todos
        renderTodos(todos); // Render lại giao diện
    } catch (error) {
        console.log(error)
    }
}

// Thêm công việc
const inputTodo = document.getElementById("input-todo");
const btnAdd = document.getElementById("btn-add");

btnAdd.addEventListener("click", async () => {
    // Lấy nd trong ô input
    const title = inputTodo.value.trim();

    // Kiểm tra nd có rỗng không
    if (title === "") {
        alert("Vui lòng nhập công việc");
        return;
    }

    // Tạo todo mới
    const newTodo = {
        title,
        status: false
    }

    try {
        const response = await axios.post(API_URL, newTodo)

        // Thêm cv mới vào mảng todos
        todos.push(response.data);

        // Render lại giao diện
        renderTodos(todos);

        inputTodo.value = "";
        inputTodo.focus();
    } catch (error) {
        console.log(error)
    }
})

const deleteTodo = (id) => {
    const confirm = window.confirm("Bạn có chắc chắn muốn xóa không?");
    if (confirm) {
        todos = todos.filter(todo => todo.id !== id);
        renderTodos(todos);
    }
}

const editTodo = (id) => {
    // Tìm công việc theo id
    const todo = todos.find(todo => todo.id === id);

    // ...
}

const toggleStatus = (id) => {
    // Tìm công việc theo id
    const todo = todos.find(todo => todo.id === id);

    // Thay đổi status: true -> false, false -> true
    todo.status = !todo.status;

    // Render lại giao diện
    renderTodos(todos);
}

getAllTodos();