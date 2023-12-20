import RushDeliveryInfo from './rushDeliveryInfo';
import ShippingInfo from './shippingInfo';
import OrderSummary from '../cart/orderSummary';
import CheckoutSingleItemDark from '../checkout/checkoutSingleItemDark';

interface Props {
  products: ({
    thumb_src: string;
    thumb_alt: string;
    color: string;
    title: string;
    price: number;
    size: string;
    stock: string;
    subtotal: number;
    shipping: number;
    tax: number;
  })[];
}

export default function CheckoutSummary({
  products,
}: Props) {

  let subtotalCheckout = 0;
  products.map(product =>
    subtotalCheckout += product.price
  )

  return (
    <>
      <section className="bg-gray-100 px-2">
        <div className="row">
          <div className="col-12 col-lg-6 p-3 p-md-5">
            <div className="form-group">
              <label>Tên người nhận</label>
              <input type="text" className="form-control" placeholder="Tên người nhận" />
            </div>

            <div className="form-group">
              <label>Email</label>
              <input type="email" className="form-control" placeholder="Email" />
            </div>

            <div className="form-group">
              <label>Số điện thoại</label>
              <input type="text" className="form-control" placeholder="Số điện thoại" />
            </div>

            <div className="row">
              <div className="col-4">
                <div className="form-group">
                  <label>Thành phố</label>
                  <input type="text" className="form-control" placeholder="Thành phố" />
                </div>
              </div>

              <div className="col-8">
                <div className="form-group">
                  <label>Địa chỉ giao hàng</label>
                  <input type="text" className="form-control" placeholder="Địa chỉ giao hàng" />
                </div>
              </div>
            </div>

            <RushDeliveryInfo />


            <button className="btn btn-dark w-100 mt-4">Thanh toán</button>
          </div>
          <div className="col-12 col-lg-6 p-lg-5">
            {products.map((product, i) =>
                <CheckoutSingleItemDark
                  thumb_src={product.thumb_src}
                  thumb_alt={product.thumb_alt}
                  title={product.title}
                  color={product.color}
                  size={product.size}
                  price={product.price}
                />
            )}
            <OrderSummary subtotal={subtotalCheckout}/>
          </div>
        </div>
      </section>
    </>
  );
};

