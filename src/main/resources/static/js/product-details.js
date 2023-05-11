$(document).ready(function() {
    // Lấy giá trị số lượng sản phẩm và giá sản phẩm từ trang chi tiết sản phẩm
    var quantityInput = $('#quantity-input');
    var totalProductPrice = $('#totalProductPrice');
    var productPrice = parseFloat(totalProductPrice.text());

    // Xử lý sự kiện thay đổi số lượng sản phẩm
    quantityInput.change(function() {
        // Lấy giá sản phẩm từ trang chi tiết sản phẩm
        productPrice = parseFloat($('#productActualPrince span').text());

        // Tính toán tổng giá tiền sản phẩm
        var totalPrice = productPrice * quantityInput.val();

        // Hiển thị tổng giá tiền sản phẩm lên trang
        totalProductPrice.text(totalPrice);
    });
});
