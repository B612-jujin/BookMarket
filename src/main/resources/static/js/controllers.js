function addToCart(bookId) {
    if(confirm("장바구니에 도서를 추가할까요?")){
        console.log("도서 추가 확인되었습니다!")
        console.log(bookId);
        document.addForm.action = "/BookMarket/cart/book/" + bookId;
        document.addForm.submit();
    }

}
function removeFromCart(bookId, cartId){
    document.removeForm.action = "/BookMarket/cart/book/" + bookId;
    document.removeForm.submit();
    setTimeout('location.reload()', 10);
}
function deleteCart(cartId){
    if(confirm("모든 도서를 장바구니에서 삭제하시겠습니까?")){
        document.deleteForm.submit();
        setTimeout('location.reload()', 10);
    }

}

