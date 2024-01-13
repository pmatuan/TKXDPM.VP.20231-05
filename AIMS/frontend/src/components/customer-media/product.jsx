import { Button, ButtonGroup } from "react-bootstrap";
import { useState } from "react";
import SWAL from "sweetalert2";

export default function Product({
  id,
  imageUrl,
  title,
  price,
  cartId,
  product,
  setMediaView,
  setViewDetail,
  position,
}) {
  const [counts, setCounts] = useState(1);
  console.log(cartId);
  const classList = "card-body " + "text-" + position;
  function handleViewDetail() {
    setMediaView(product);
    setViewDetail(true);
  }
  function handleAddMediaToCart() {
    SWAL.fire({
      html: "<h5>Sản phẩm đã được thêm vào giỏ hàng</h5>",
      icon: "success",
      showConfirmButton: false,
      timer: 1000
    });
    async function addMediaToCart() {
      try {
        const apiUrl = `http://127.0.0.1:8080/api/v1/cart/${cartId}/cart-media`;
        const requestOptions = {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ mediaId: id, quantity: counts }),
        };

        const response = await fetch(apiUrl, requestOptions);

        if (response.oke) {
          setCounts(1);
        } else if (!response.ok) {
          throw new Error("Network response was not ok");
        }
      } catch (error) {
        console.error("Error sending POST request:", error);
      } finally {
        setCounts(1);
      }
    }
    addMediaToCart();
  }
  return (
    <div style={{ height: "350px" }}>
      <div className="card card-product border shadow-xs border-radius-lg">
        <div className="height-200">
          <img
            className="w-100 h-100 p-4 rounded-top"
            src={imageUrl.startsWith("http") ? imageUrl : `http://127.0.0.1:8080/api/v1/media/images/${imageUrl}`}
          />
        </div>
        <div className={`${classList} d-flex flex-column align-items-center`}>
          <h4 className="font-weight-bold">{title}</h4>

          <p className="text-body">{price} đồng</p>

          <ButtonGroup
            aria-label="Basic example"
            className="align-items-center"
          >
            <Button
              onClick={() => setCounts(counts - 1)}
              disabled={counts === 1}
              className="mb-0"
              variant="secondary"
            >
              -
            </Button>
            <div style={{ width: 40 }}>{counts}</div>
            <Button
              onClick={() => setCounts(counts + 1)}
              className="mb-0"
              variant="secondary"
            >
              +
            </Button>
          </ButtonGroup>
          <ButtonGroup aria-label="Basic example" className="mt-3">
            <Button
              className="mb-0"
              variant="outline-primary"
              onClick={handleViewDetail}
              style={{ zIndex: 2 }}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                className="bi bi-eye"
                viewBox="0 0 16 16"
              >
                <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8M1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
                <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0" />
              </svg>
            </Button>
            <Button
              className="mb-0"
              variant="primary"
              onClick={handleAddMediaToCart}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                className="bi bi-cart-plus"
                viewBox="0 0 16 16"
              >
                <path d="M9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9z" />
                <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0m7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0" />
              </svg>
            </Button>
          </ButtonGroup>
        </div>
      </div>
    </div>
  );
}
