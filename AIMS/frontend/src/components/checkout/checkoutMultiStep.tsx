import RushDeliveryInfo from "./rushDeliveryInfo";
import CheckoutSingleItemDark from "../checkout/checkoutSingleItemDark";
import {useEffect, useState} from "react";
import OrderSummary from "../cart/orderSummary";

interface OrderMedia {
  id: string;
  imageUrl: string;
  category: string;
  title: string;
  price: number;
  quantityInStock: number;
  subtotal: number;
  quantity: number;
  isAbleToRushDelivery: boolean;
  isOrderForRushDelivery: boolean;
}

interface OrderSummaryData {
  summary: {
    subtotal: number;
    shippingFee: number;
    vat: number;
    total: number;
  }
}

interface Props {
  orderId: string;
}

const BACKEND_URL = "http://localhost:8080/api/v1"

const provinces = [
  "Hà Nội",
  "Hồ Chí Minh",
  "Hải Phòng",
  "Cần Thơ",
  "Đà Nẵng",
  "Hà Giang",
  "Cao Bằng",
  "Lai Châu",
  "Lào Cai",
  "Tuyên Quang",
  "Lạng Sơn",
  "Bắc Kạn",
  "Thái Nguyên",
  "Phú Thọ",
  "Bắc Giang",
  "Quảng Ninh",
  "Bắc Ninh",
  "Hải Dương",
  "Hưng Yên",
  "Nam Định",
  "Thái Bình",
  "Ninh Bình",
  "Thanh Hóa",
  "Nghệ An",
  "Hà Tĩnh",
  "Quảng Bình",
  "Quảng Trị",
  "Thừa Thiên Huế",
  "Quảng Nam",
  "Quảng Ngãi",
  "Bình Định",
  "Phú Yên",
  "Khánh Hòa",
  "Ninh Thuận",
  "Bình Thuận",
  "Kon Tum",
  "Gia Lai",
  "Đắk Lắk",
  "Đắk Nông",
  "Lâm Đồng",
  "Bình Phước",
  "Tây Ninh",
  "Bình Dương",
  "Đồng Nai",
  "Bà Rịa - Vũng Tàu",
  "Long An",
  "Tiền Giang",
  "Bến Tre",
  "Trà Vinh",
  "Vĩnh Long",
  "Đồng Tháp",
  "An Giang",
  "Kiên Giang",
  "Hậu Giang",
  "Sóc Trăng",
  "Bạc Liêu",
  "Cà Mau"
];

export default function CheckoutSummary({orderId}: Props) {

  const orderSummaryTmp: OrderSummaryData = {
    summary: {
      subtotal: 0,
      shippingFee: 0,
      vat: 0,
      total: 0,
    }
  };

  const [orderProducts, setOrderProducts] = useState<OrderMedia[]>([]);
  const [selectedCity, setSelectedCity] = useState("");
  const [summaryOrder, setSummaryOrder] = useState(orderSummaryTmp);

  const [recipientName, setRecipientName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [deliveryCity, setDeliveryCity] = useState("");
  const [deliveryAddress, setDeliveryAddress] = useState("");
  const [isRushDelivery, setIsRushDelivery] = useState(false);
  const [rushDeliveryTime, setRushDeliveryTime] = useState<Date>(new Date("Thu Nov 30 2023 17:09:46 GMT+0700 (Indochina Time)"));
  const [rushDeliveryInstructions, setRushDeliveryInstructions] = useState("");
  const [canCheckOut, setCanCheckOut] = useState(false); // Updated this line


  const initialize = async () => {
    try {
      const response = await fetch(BACKEND_URL + `/order/${orderId}`);
      const data = await response.json();

      const order = data.result.order;
      const orderMediaList = order.orderMediaList;

      const orderItems = orderMediaList.map((orderMedia: any) => ({
        id: orderMedia.id,
        imageUrl: orderMedia.media.imageUrl,
        category: orderMedia.media.category,
        title: orderMedia.media.title,
        price: orderMedia.media.price,
        quantityInStock: orderMedia.media.quantityInStock,
        subtotal: orderMedia.media.price * orderMedia.quantity,
        quantity: orderMedia.quantity,
        isAbleToRushDelivery: orderMedia.media.isAbleToRushDelivery,
        isOrderForRushDelivery: orderMedia.isOrderForRushDelivery,
      }));

      const orderSummary: OrderSummaryData = {
        summary: {
          subtotal: order.subtotal,
          shippingFee: order.deliveryFee != null ? order.deliveryFee : 0,
          vat: order.vat,
          total: order.total,
        }
      };

      setOrderProducts(orderItems);
      setSummaryOrder(orderSummary);

    } catch (error) {
      console.error("Error fetching data:", error);
      // Handle error as needed
    }
  };

  const handleChangeSelectedCity = async () => {
    try {
      const response = await fetch(
          `${BACKEND_URL}/place-order/${orderId}/delivery-info`,
          {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ province: selectedCity }),
          }
      );

      // If the API call is successful, update the shipping fee
      if (response.ok) {
        setDeliveryCity(selectedCity);
        handleChangeShippingFee();
      }
    } catch (error) {
      console.error("Error updating selected city:", error);
      // Handle error as needed
    }
  };

  const handleChangeShippingFee = async () => {
    try {
      const response = await fetch(BACKEND_URL + `/order/${orderId}`);
      const data = await response.json();

      const order = data.result.order;

      const orderSummary: OrderSummaryData = {
        summary: {
          subtotal: order.subtotal,
          shippingFee: order.deliveryFee != null ? order.deliveryFee : 0,
          vat: order.vat,
          total: order.total,
        },
      };

      setSummaryOrder(orderSummary);
    } catch (error) {
      console.error("Error updating shipping fee:", error);
      // Handle error as needed
    }
  };

  const handleCheckout = async () => {
    try {
      const response = await fetch(
          `${BACKEND_URL}/place-order/${orderId}/delivery-info`,
          {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              customerName: recipientName,
              email: email,
              phoneNumber: phoneNumber,
              province: selectedCity,
              address: deliveryAddress,
              isOrderForRushDelivery: isRushDelivery,
              deliveryTime: rushDeliveryTime.toISOString(),
              deliveryInstruction: rushDeliveryInstructions
            }),
          }
      );
      if (response.ok){
        console.log("Checkout successful")
      }
    } catch (error) {
      console.error("Error updating selected city:", error);
      // Handle error as needed
    }
  }

  useEffect(() => {
    initialize();
  }, []);

  useEffect(() => {
    handleChangeSelectedCity();
  }, [selectedCity]);

  useEffect(() => {
    const isFilled =
        !!recipientName && !!email && !!phoneNumber && !!deliveryCity && !!deliveryAddress;

    const isRushDeliveryFilled =
        isRushDelivery && !!rushDeliveryTime && !!rushDeliveryInstructions;

    setCanCheckOut(isFilled && (!isRushDelivery || isRushDeliveryFilled));
  }, [
    recipientName,
    email,
    phoneNumber,
    deliveryCity,
    deliveryAddress,
    isRushDelivery,
    rushDeliveryTime,
    rushDeliveryInstructions,
  ]);

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
                    onChange={(e) => setRecipientName(e.target.value)}
                />
              </div>

              <div className="form-group">
                <label>Email</label>
                <input
                    type="email"
                    className="form-control"
                    placeholder="Email"
                    onChange={(e) => setEmail(e.target.value)}
                />
              </div>

              <div className="form-group">
                <label>Số điện thoại</label>
                <input
                    type="text"
                    className="form-control"
                    placeholder="Số điện thoại"
                    onChange={(e) => setPhoneNumber(e.target.value)}
                />
              </div>

              <div className="row">
                <div className="col-4">
                  <div className="form-group">
                    <label>Thành phố</label>
                    <select
                        className="form-control"
                        value={selectedCity}
                        onChange={(e) => setSelectedCity(e.target.value)}
                    >
                      <option value="" disabled>
                        Chọn thành phố
                      </option>

                      {provinces.map((city) => (
                          <option key={city} value={city}>
                            {city}
                          </option>
                      ))}
                    </select>
                  </div>
                </div>


                <div className="col-8">
                  <div className="form-group">
                    <label>Địa chỉ giao hàng</label>
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Địa chỉ giao hàng"
                        onChange={(e) => setDeliveryAddress(e.target.value)}
                    />
                  </div>
                </div>
              </div>

              <RushDeliveryInfo
                  isRushDelivery={isRushDelivery}
                  setRushDelivery={(value) => setIsRushDelivery(!!value)} // Updated this line
                  setRushDeliveryTime={setRushDeliveryTime}
                  setRushDeliveryInstructions={setRushDeliveryInstructions}
              />
              {canCheckOut ? (
                  <button className="btn btn-dark w-100 mt-4" onClick={handleCheckout}>Thanh toán</button>
              ) : (
                  <button className="btn btn-dark w-100 mt-4" disabled>Thanh toán</button>
              )}
            </div>
            <div className="col-12 col-lg-6 p-lg-5">
              {orderProducts.map((product, i) => {
                if (product.quantity > 0) {
                  return (
                      <CheckoutSingleItemDark
                          key={product.id}
                          imageUrl={product.imageUrl}
                          title={product.title}
                          price={product.price}
                          quantityInStock={product.quantityInStock}
                          quantity={product.quantity || 1}
                      />
                  );
                }
              })}
              {summaryOrder && (
                  <OrderSummary
                      subtotal={summaryOrder.summary.subtotal}
                      shippingFee={summaryOrder.summary.shippingFee}
                      total={summaryOrder.summary.total}
                      vat={summaryOrder.summary.vat}
                  />
              )}
            </div>
          </div>
        </section>
      </>
  );
}
}