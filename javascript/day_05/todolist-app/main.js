let todos = [
    {
        id: 1,
        title: "Làm bài tập",
        status: true
    },
    {
        id: 2,
        title: "Đi chơi",
        status: false
    },
    {
        id: 3,
        title: "Đá bóng",
        status: true
    }
];

const generateId = () => {
    if (todos.length === 0) {
        return 1;
    }
    return Math.max(...todos.map(todo => todo.id)) + 1; // [1, 2, 3] -> max = 3 -> next id = max + 1 = 4
}

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
        // if (todo.status) {
        //     html += `
        //         <li>
        //             <input type="checkbox" checked />
        //             <span class="active">${todo.title}</span>
        //             <button>Edit</button>
        //             <button>Delete</button>
        //         </li>
        //   `
        // } else {
        //     html += `
        //         <li>
        //             <input type="checkbox"/>
        //             <span>${todo.title}</span>
        //             <button>Edit</button>
        //             <button>Delete</button>
        //         </li>
        //     `;
        // }
    })
    ulEl.innerHTML = html;
}

// Thêm công việc
const inputTodo = document.getElementById("input-todo");
const btnAdd = document.getElementById("btn-add");

btnAdd.addEventListener("click", () => {
    // Lấy nd trong ô input
    const title = inputTodo.value.trim();

    // Kiểm tra nd có rỗng không
    if (title === "") {
        alert("Vui lòng nhập công việc");
        return;
    }

    // Tạo todo mới
    const newTodo = {
        id: generateId(),
        title,
        status: false
    }

    // Thêm vào mảng todos
    todos.push(newTodo);

    // Render lại giao diện
    renderTodos(todos);

    inputTodo.value = "";
    inputTodo.focus();
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

renderTodos(todos);