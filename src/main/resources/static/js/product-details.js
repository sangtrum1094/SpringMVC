$(document).ready(function() {
    // Lấy giá trị số lượng sản phẩm và giá sản phẩm từ trang chi tiết sản phẩm
    var quantityInput = $('#quantity-input');
    var totalProductPrice = $('#totalProductPrice');
    var productPrice = parseFloat(totalProductPrice.text());

    // Xử lý sự kiện thay đổi số lượng sản phẩm
    quantityInput.change(function() {
        // Lấy giá sản phẩm từ trang chi tiết sản phẩm
        productPrice = parseFloat($('#productActualPrice span').text());

        // Tính toán tổng giá tiền sản phẩm
        var totalPrice = productPrice * quantityInput.val();

        // Hiển thị tổng giá tiền sản phẩm lên trang
        totalProductPrice.text(totalPrice);
    });

    // Xử lý sự kiện nhấn nút "Mua hàng"
    $('button#add-to-cart').click(function(event) {
        event.preventDefault();
        var productId = $('#id').val();
        var productQuantity = parseInt($('#quantity-input').val());
        var cart = getCart();
        var foundProduct = false;

        // Tìm sản phẩm trong giỏ hàng
        for (var i = 0; i < cart.length; i++) {
            if (cart[i].id === productId) {
                cart[i].quantity += productQuantity;
                foundProduct = true;
                break;
            }
        }

        // Nếu không tìm thấy sản phẩm trong giỏ hàng thì thêm sản phẩm mới
        if (!foundProduct) {
            cart.push({
                id: productId,
                quantity: productQuantity
            });
        }

        // Lưu giỏ hàng vào cookie
        setCart(cart);

        // Hiển thị thông báo thành công
        alert('Sản phẩm đã được thêm vào giỏ hàng!');
    });

    // Hàm lấy giỏ hàng từ cookie
    function getCart() {
        var cart = [];

        if (document.cookie.indexOf('cart=') !== -1) {
            var cookieValue = document.cookie.replace(/(?:(?:^|.*;\s*)cart\s*\=\s*([^;]*).*$)|^.*$/, '$1');
            cart = JSON.parse(decodeURIComponent(cookieValue));
        }

        return cart;
    }

    // Hàm lưu giỏ hàng vào cookie
    function setCart(cart) {
        document.cookie = 'cart=' + encodeURIComponent(JSON.stringify(cart)) + '; path=/';
    }
});