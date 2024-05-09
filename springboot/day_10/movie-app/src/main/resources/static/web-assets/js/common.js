// Xử lý active menu
function activeMenu() {
    // Lây đường dẫn hiện tại
    let path = window.location.pathname;
    console.log(path)

    // Xử lý active menu
    const menuItems = document.querySelectorAll('#main-menu .navbar-nav .nav-link')
    menuItems.forEach(function (menu) {
        if (menu.getAttribute('href') === path) {
            menu.classList.add('active')
        }
    })
}

activeMenu()