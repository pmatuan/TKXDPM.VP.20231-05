import OrderInfo from "./orderInfo";
import OrderSearchBox from './orderSearchBox';
import {useEffect, useState} from "react";

interface Props {
  orderIdUrl: string;
}

interface Order {
  id: string;
  products: {
    id: string;
    imageUrl: string;
    quantity: number;
    title: string;
    price: number;
  }[];
  address: string;
  customerName: string;
  email: string;
  date: string;
  paymentMethod: string;
  phoneNumber: string;
  subtotal: number;
  deliveryFee: number;
  total: number;
  state: string;
}


const BACKEND_URL = "http://localhost:8080/api/v1"

const convertDate = (originalDate: string) => {
  const dateObject = new Date(originalDate);
  const options = { year: 'numeric', month: 'long', day: 'numeric' };
  const formattedDateString = dateObject.toLocaleString('vi-VN', options);
  return formattedDateString
}

const sampleOrder: Order = {
  id: "123456",
  products: [
    {
      id: "product1",
      imageUrl: "https://example.com/product1.jpg",
      quantity: 2,
      title: "Product 1",
      price: 19.99,
    },
    {
      id: "product2",
      imageUrl: "https://example.com/product2.jpg",
      quantity: 1,
      title: "Product 2",
      price: 29.99,
    },
  ],
  address: "123 Main Street, Cityville",
  customerName: "John Doe",
  email: "john.doe@example.com",
  date: "2023-01-01",
  paymentMethod: "Credit Card",
  phoneNumber: "555-1234",
  subtotal: 69.97,
  deliveryFee: 5.00,
  total: 74.97,
  state: "PROCESSING",
};


export default function OrderSummaries({ orderIdUrl }: Props) {

  const [orderId, setOrderId] = useState(orderIdUrl);
  const [order, setOrder] = useState<Order>(sampleOrder);

  const initialize = async () => {
    const response = await fetch(BACKEND_URL + `/order/${orderId}`)
    const data = await response.json()
    if(data.code === "ORDER_001"){
      setOrderId(undefined)
    }
    else {
      const rawOrder = data.result.order;

      const orderData = {
        id: rawOrder.id,
        products: rawOrder.orderMediaList.map(orderMedia => ({
          id: orderMedia.id,
          imageUrl: orderMedia.media.imageUrl,
          quantity: orderMedia.quantity,
          title: orderMedia.media.title,
          price: orderMedia.media.price,
        })),
        address: rawOrder.deliveryInfo.address,
        customerName: rawOrder.deliveryInfo.customerName,
        email: rawOrder.deliveryInfo.email,
        date: convertDate(rawOrder.createdAt),
        paymentMethod: rawOrder.paymentTransaction.paymentMethod,
        phoneNumber: rawOrder.deliveryInfo.phoneNumber,
        subtotal: rawOrder.subtotal,
        deliveryFee: rawOrder.deliveryFee,
        total: rawOrder.total,
        state: rawOrder.state,
      };

      setOrder(orderData);
    }

  }

  useEffect(() => {
    initialize()
    console.log("Order idaaaaa: " + orderId)
  }, [orderId]);

  useEffect(() => {
    console.log("Order changed")
  }, [order])

  return (
    <>
      <div className="flex justify-center mb-4">
        <OrderSearchBox setOrderId={setOrderId}/>
      </div>

      {orderId ? <OrderInfo order={order} /> : <div></div>}
    </>
  );
}
