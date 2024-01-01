import ProductCartItem from "./productCartItem";
import OrderSummary from "./orderSummary";
import { useEffect, useState } from "react";
import { setCookie } from 'typescript-cookie'

interface Props {
  products: {
    id: number;
    imageUrl: string;
    category: string;
    title: string;
    price: number;
    quantityInStock: number;
    subtotal: number;
    quantity: number;
  }[],
  cartId: string;
}

export default function ShoppingCart({ products, cartId }: Props) {
  const [cartProducts, setCartProducts] = useState(products);
  const [subtotalCart, setSubtotalCart] = useState(0);
  const [canPlaceOrder, setCanPlaceOrder] = useState(true);
  const BACKEND_URL = "http://localhost:8080/api/v1";

  useEffect(() => {
    let subtotal = 0;
    cartProducts.forEach((product) => {
      subtotal += product.price * product.quantity;
    });
    setSubtotalCart(subtotal);
  }, [cartProducts]);

  const handleChangeQuantity = async (index: number, quantity: number) => {
    const updatedProducts = [...cartProducts];
    updatedProducts[index] = {
      ...updatedProducts[index],
      quantity: quantity,
    };
    if (updatedProducts[index].quantity > updatedProducts[index].quantityInStock){
      setCanPlaceOrder(false)
    }
    else {
      setCanPlaceOrder(true)
    }
    setCartProducts(updatedProducts);

    await Promise.all(updatedProducts.map(async (product) => {
      const response = await fetch(`${BACKEND_URL}/cart/${cartId}/cart-media/${product.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ quantity: product.quantity }),
      });

      if (!response.ok) {
        // Handle error, maybe show a message to the user
        console.error(`Failed to update cart item for product ID ${product.id}`);
        return Promise.reject(`Failed to update cart item for product ID ${product.id}`);
      }
    }));
  };

  const handlePlaceOrder = async () => {
    await Promise.all(cartProducts.map(async (product) => {
      const response = await fetch(`${BACKEND_URL}/cart/${cartId}/cart-media/${product.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ quantity: product.quantity }),
      });

      if (!response.ok) {
        // Handle error, maybe show a message to the user
        console.error(`Failed to update cart item for product ID ${product.id}`);
        return Promise.reject(`Failed to update cart item for product ID ${product.id}`);
      }
    }));

    const orderResponse = await fetch(`${BACKEND_URL}/place-order`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ cartId: cartId }),
    });

    if (orderResponse.ok) {
      const orderData = await orderResponse.json();
      const orderId = orderData.result.orderId;
      console.log("Order placed successfully!");
      setCookie("orderId", orderId)
      window.location.href = `/aims-ecommerce/checkout`;
    } else {
      console.error("Failed to place the order");
    }
  };

  return (
    <>
      <div className="container mt-5">
        <h2 className="mb-3 text-center mb-8">Giỏ hàng</h2>
        <div className="row">
          <div className="col-12 col-lg-7">
            {cartProducts.map((product, i) => (
              <>
                {i != 0 && <hr className="horizontal dark my-4" />}
                {product.quantity > 0 && (
                    <ProductCartItem
                        imageUrl={product.imageUrl}
                        title={product.title}
                        category={product.category}
                        price={product.price}
                        quantityInStock={product.quantityInStock}
                        quantity={product.quantity || 1}
                        onChangeQuantity={(quantity: number) => handleChangeQuantity(i, quantity)}
                    />
                )}
              </>
            ))}
          </div>
          <div className="col-12 col-lg-5 mt-5 mt-lg-0">
            <div className="card shadow-xs border bg-gray-100">
              <div className="card-body p-lg-5">
                <h5 className="mb-4">Thông tin đơn hàng</h5>
                <OrderSummary
                    subtotal={subtotalCart}
                    shippingFee={-1}
                    vat={subtotalCart / 10}
                    total={subtotalCart * 1.1}
                />
                {canPlaceOrder ? (
                    <a onClick={handlePlaceOrder}>
                      <button className="btn btn-dark btn-lg w-100 mt-3">
                        Đặt hàng
                      </button>
                    </a>
                ) : (
                    <button
                        className="btn btn-dark btn-lg w-100 mt-3"
                        disabled
                    >
                      Đặt hàng
                    </button>
                )}
                <a href="/aims-ecommerce/store/">
                  <button className="btn btn-white btn-lg w-100">
                    Tiếp tục mua sắm
                  </button>
                </a>
                <p className="text-center">
                  Phí vận chuyển được tính khi thanh toán.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
