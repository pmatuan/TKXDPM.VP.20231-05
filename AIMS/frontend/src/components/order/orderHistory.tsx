import OrderHistoryCard from "./orderHistoryCard";
import { useEffect, useState } from "react";

interface Props {
  orderList: {
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
  }[];
}

export default function OrderHistory({ orderList }: Props) {
  const [orders, setOrders] = useState(orderList);

  useEffect(() => {
    console.log("Orders updated:", orders);
  }, [orders]);

  const handleOrderStateChange = (orderId: string) => {
    // Update the order state on the server (similar to your existing code)

    // Assuming a successful state change on the server, update the local state
    const updatedOrders = orders.filter((order) => order.id !== orderId);
    setOrders(updatedOrders);
  };

  return (
      <>
        <div>
          <h3 className="mb-5">Danh sách đơn hàng</h3>
          {orders.map((order) => {
            if (order.state === "PROCESSING") {
              return (
                  <OrderHistoryCard
                      key={order.id}
                      order={order}
                      onOrderStateChange={handleOrderStateChange}
                  />
              );
            }
            return null; // or any other logic if you want to handle other cases
          })}
        </div>
      </>
  );
}
