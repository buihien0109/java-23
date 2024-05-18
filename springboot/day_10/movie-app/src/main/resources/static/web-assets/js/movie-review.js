const stars = document.querySelectorAll(".star");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;

stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.getAttribute("data-rating"));
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.getAttribute("data-rating"));
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

// render review
const formatDate = dateStr => {
    const date = new Date(dateStr);
    const day = `0${date.getDate()}`.slice(-2); // 01 -> 01, 015 -> 15
    const month = `0${date.getMonth() + 1}`.slice(-2);
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}
const reviewListEl = document.querySelector(".review-list");
const renderReview = reviews => {
    let html = "";
    reviews.forEach(review => {
        html += `
            <div class="rating-item d-flex align-items-center mb-3 pb-3">
                <div class="rating-avatar">
                    <img src="${review.user.avatar}" alt="${review.user.name}">
                </div>
                <div class="rating-info ms-3">
                    <div class="d-flex align-items-center">
                        <p class="rating-name mb-0">
                            <strong>${review.user.name}</strong>
                        </p>
                        <p class="rating-time mb-0 ms-2">${formatDate(review.createdAt)}</p>
                    </div>
                    <div class="rating-star">
                        <p class="mb-0 fw-bold">
                            <span class="rating-icon"><i class="fa fa-star"></i></span>
                            <span>${review.rating}/10 Tuyệt vời</span>
                        </p>
                    </div>
                    <p class="rating-content mt-1 mb-0 text-muted">${review.content}</p>
                    ${
                        currentUser != null && currentUser.id === review.user.id
                            ? `
                                <div>
                                    <button class="border-0 bg-transparent btn-edit-review text-primary me-2 text-decoration-underline">
                                        Sửa
                                    </button>
                                    <button class="border-0 bg-transparent btn-delete-review text-danger text-decoration-underline">
                                        Xóa
                                    </button>
                                </div>
                                `
                            : ''
                    }
                </div>
            </div>
        `
    })

    reviewListEl.innerHTML = html;
}

// Tạo review
const formReviewEl = document.getElementById("form-review");
const reviewContentEl = document.getElementById("review-content");
const modalReviewEl = document.getElementById("modal-review");
const myModalReviewEl = new bootstrap.Modal(modalReviewEl, {
    keyboard: false
})

modalReviewEl.addEventListener('hidden.bs.modal', event => {
    console.log("Su kien dong modal")
    currentRating = 0;
    reviewContentEl.value = "";
    ratingValue.textContent = "";
    resetStars();
})

formReviewEl.addEventListener("submit", async (e) => {
    e.preventDefault();

    // TODO: Validate các thông tin (sử dụng thư jQuery Validation)
    if (currentRating === 0) {
        alert("Vui lòng chọn số sao");
        return;
    }

    if (reviewContentEl.value.trim() === "") {
        alert("Nội dung đánh giá không được để trống");
        return;
    }

    const data = {
        content: reviewContentEl.value,
        rating: currentRating,
        movieId: movie.id
    }

    // Gọi API
    try {
        let res = await axios.post("/api/reviews", data);
        reviews.unshift(res.data);
        renderReview(reviews);

        // Dong modal
        myModalReviewEl.hide();

        toastr.success("Đánh giá thành công");
    } catch (e) {
        console.log(e)
    }
})
