import RushDeliveryInfo from "./rushDeliveryInfo";
import CheckoutSingleItemDark from "../checkout/checkoutSingleItemDark";
import {useEffect, useState} from "react";
import OrderSummary from "../cart/orderSummary";

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
    isAbleToRushDelivery: boolean;
    isOrderForRushDelivery: boolean;
  }[],
  summary: {
    subtotal: number;
    shippingFee: number;
    vat: number;
    total: number;
  },
  orderId: string;
}

export default function CheckoutSummary({ products, summary, orderId }: Props) {
  
  const [orderProducts, setOrderProducts] = useState(products);
  const [subTotalOrder, setSubtotalOrder] = useState(summary.subtotal);
  const [canCheckOut, setCanCheckOut] = useState(false);

  useEffect(() => {
    let subtotal = 0;
    orderProducts.forEach((product) => {
      subtotal += product.price * product.quantity;
    });
    setSubtotalOrder(subtotal);
    summary.subtotal = subtotal
    summary.vat = subtotal / 10
    summary.total = subtotal * 1.1
  }, [orderProducts]);

  const handleRemoveProduct = (index: number) => {
    const updatedProducts = [...orderProducts];
    updatedProducts.splice(index, 1);
    setOrderProducts(updatedProducts);
  };

  const handleChangeQuantity = (index: number, quantity: number) => {
    const updatedProducts = [...orderProducts];
    updatedProducts[index] = {
      ...updatedProducts[index],
      quantity: quantity,
    };
    if (updatedProducts[index].quantity > updatedProducts[index].quantityInStock){
      setCanCheckOut(false)
    }
    else {
      setCanCheckOut(true)
    }
    setOrderProducts(updatedProducts);
  };

  return (
    <>
      <section className="bg-gray-100 px-2">
        <div className="row">
          <div className="col-12 col-lg-6 p-3 p-md-5">
            <div className="form-group">
              <label>Tên người nhận</label>
              <input
                type="text"
                className="form-control"
                placeholder="Tên người nhận"
              />
            </div>

            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                className="form-control"
                placeholder="Email"
              />
            </div>

            <div className="form-group">
              <label>Số điện thoại</label>
              <input
                type="text"
                className="form-control"
                placeholder="Số điện thoại"
              />
            </div>

            <div className="row">
              <div className="col-4">
                <div className="form-group">
                  <label>Thành phố</label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Thành phố"
                  />
                </div>
              </div>

              <div className="col-8">
                <div className="form-group">
                  <label>Địa chỉ giao hàng</label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Địa chỉ giao hàng"
                  />
                </div>
              </div>
            </div>

            <RushDeliveryInfo />
            {canCheckOut ? (
                <button className="btn btn-dark w-100 mt-4">Thanh toán</button>
            ) : (
                <button className="btn btn-dark w-100 mt-4" disabled>Thanh toán</button>
            )}
          </div>
          <div className="col-12 col-lg-6 p-lg-5">
            {orderProducts.map((product, i) => (
              <CheckoutSingleItemDark
                  imageUrl={product.imageUrl}
                  title={product.title}
                  price={product.price}
                  quantityInStock={product.quantityInStock}
                  quantity={product.quantity || 1}
                  onRemove={() => handleRemoveProduct(i)}
                  onChangeQuantity={(quantity: number) =>
                      handleChangeQuantity(i, quantity)
                  }
              />
            ))}
            <OrderSummary summary={summary} />
          </div>
        </div>
      </section>
    </>
  );
}
