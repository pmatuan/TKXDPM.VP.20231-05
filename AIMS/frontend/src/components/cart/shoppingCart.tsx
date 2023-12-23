import ProductCartItem from "./productCartItem";
import OrderSummary from "./orderSummary";
import { useEffect, useState } from "react";

interface Props {
  products: {
    image_url: string;
    category: string;
    title: string;
    price: number;
    stock: boolean;
    subtotal: number;
    quantity: number;
  }[];
}

export default function ShoppingCart({ products }: Props) {
  const [cartProducts, setCartProducts] = useState(products);
  const [subtotalCart, setSubtotalCart] = useState(0);

  useEffect(() => {
    let subtotal = 0;
    cartProducts.forEach((product) => {
      subtotal += product.price * product.quantity;
    });
    setSubtotalCart(subtotal);
  }, [cartProducts]);

  const handleRemoveProduct = (index: number) => {
    const updatedProducts = [...cartProducts];
    updatedProducts.splice(index, 1);
    setCartProducts(updatedProducts);
  };

  const handleChangeQuantity = (index: number, quantity: number) => {
    const updatedProducts = [...cartProducts];
    updatedProducts[index] = {
      ...updatedProducts[index],
      quantity: quantity,
    };
    setCartProducts(updatedProducts);
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
                <ProductCartItem
                  image_url={product.image_url}
                  title={product.title}
                  category={product.category}
                  price={product.price}
                  stock={product.stock}
                  quantity={product.quantity || 1}
                  onRemove={() => handleRemoveProduct(i)}
                  onChangeQuantity={(quantity: number) =>
                    handleChangeQuantity(i, quantity)
                  }
                />
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
                <a href="/aims-ecommerce/checkout/">
                  <button className="btn btn-dark btn-lg w-100 mt-3">
                    Đặt hàng
                  </button>
                </a>
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
