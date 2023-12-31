import OrderProductRow from "./orderProductRow";
import SWAL from "sweetalert2";

interface Props {
  order: {
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
  };
}

const getStateVietnamese = (state: string) => {
  switch (state){
    case "PROCESSING":
      return "Đang xử lý"
    case "CANCEL":
      return "Đã huỷ"
    case "REJECT":
      return "Bị từ chối"
    case "ACCEPT":
      return "Đã chấp nhận"
    default:
      return "Không rõ"
  }
}

export default function OrderInfo({order}: Props) {
  let orderInfoCards: any[] = [];

  order.products.map((product) => {
    orderInfoCards.push(
        <OrderProductRow
            product={product}
        />
    );
  });

  const handleRequestCancelOrder = async () => {
    const BACKEND_URL = "http://localhost:8080/api/v1";
    try {
      const response = fetch(`${BACKEND_URL}/order/${order.id}/request-cancel`);
      await SWAL.fire({
        html: "<h5>Vui lòng xác nhận huỷ đơn hàng <br /> thông qua email của bạn</h5>",
        confirmButtonColor: "#1E293B"

      });
    } catch (error) {
      console.error(error);
      // Handle error as needed
    }
  }

  return (
      <div className="card shadow-xs border p-3 p-md-4 mb-4">
        <div className="shadow-xs border rounded-3 mb-5">
          <div className="d-block d-md-flex justify-content-between align-items-center">
            <div className="d-block d-md-flex">
              <div className="d-flex d-md-block justify-content-between p-3 me-md-4">
                <p className="text-sm mb-0">Mã đơn hàng</p>
                <h6>{order.id}</h6>
              </div>
              <div className="d-flex d-md-block justify-content-between p-3 me-md-4">
                <p className="text-sm mb-0">Ngày đặt hàng</p>
                <h6>{order.date}</h6>
              </div>
              <div className="d-flex d-md-block justify-content-between p-3">
                <p className="text-sm mb-0">Tổng tiền</p>
                <h6>{order.total}{order.paymentMethod === "Paypal" ? " USD" : " đồng"}</h6>
              </div>
              <div className="d-flex d-md-block justify-content-between p-3">
                <p className="text-sm mb-1">Phương thức thanh toán</p>
                {order.paymentMethod === '' ? (
                    <span></span>
                ) : (
                    <>
                      {order.paymentMethod === "PAYPAL" ? (
                          <img
                              src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/PayPal_logo.svg/2560px-PayPal_logo.svg.png"
                              style={{ width: "80px" }}
                              alt="PayPal Logo"
                          />
                      ) : (
                          <img
                              src="https://cdn.haitrieu.com/wp-content/uploads/2022/10/Logo-VNPAY-QR-1.png"
                              style={{ width: "80px" }}
                              alt="VNPAY Logo"
                          />
                      )}
                    </>
                )}

              </div>
              <div className="d-flex d-md-block justify-content-between p-3">
                <p className="text-sm mb-0">Trạng thái</p>
                <h6>{getStateVietnamese(order.state)}</h6>
              </div>
            </div>
            <button
                className="btn btn-dark btn-sm mb-md-0 ms-4 md-md-0 me-4"
                data-bs-toggle="modal"
                data-bs-target={`#${order.id}`}
            >
              Xem chi tiết đơn hàng
            </button>
            <div
                className="modal fade"
                id={order.id}
                role="dialog"
                aria-labelledby={order.id}
                aria-hidden="true"
            >
              <div
                  className="modal-dialog modal-dialog-centered modal-lg"
                  role="document"
              >
                <div className="modal-content">
                  <div className="card p-4">
                    <div className="card-header text-center">
                      <div className="row justify-content-between">
                        <div className="col-md-4 text-start">
                          <img
                              className="mb-2 w-50 rounded-2"
                              src={`${import.meta.env.BASE_URL}Logo.png`}
                              alt="Logo"
                          />

                          <h4>
                            Đơn hàng: <br/>
                            <small className="mr-2">{order.id}</small>
                          </h4>

                        </div>
                        <div className="col-lg-4 col-md-5 text-end">
                          <button
                              type="button"
                              className="btn-close float-end"
                              data-bs-dismiss="modal"
                              aria-label="Close"
                          ></button>
                          <h4 className="mt-7 mb-1">Gửi đến:</h4>
                          <span className="d-block">{order.customerName}</span>
                          <p className="text-sm">
                            {order.address} <br/>
                            {order.phoneNumber}
                          </p>
                        </div>
                      </div>
                      <div className="row justify-content-md-between">
                        <div className="col-md-4">

                        </div>
                        <div className="col-lg-6 col-md-5">
                          <div className="d-flex align-items-center mt-5 ms-auto float-end">
                            <div className="text-sm me-3 font-weight-bold">Ngày đặt hàng:</div>
                            <div className="text-end">{order.date}</div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div className="card-body">
                      <div className="row">
                        <div className="col-12">
                          <div className="table-responsive">
                            <table className="table text-right border-spacing-2">
                              <tfoot>
                              <tr>
                                <th colSpan={2}>
                                  Tổng đơn
                                </th>
                                <th className="text-end">{order.subtotal}</th>
                              </tr>
                              <tr>
                                <th colSpan={2}>
                                  VAT
                                </th>
                                <th className="text-end">{order.subtotal / 10}</th>
                              </tr>
                              <tr>
                                <th colSpan={2}>
                                  Phí giao hàng
                                </th>
                                <th className="text-end">{order.deliveryFee}</th>
                              </tr>
                              <tr>
                                <th colSpan={2}>
                                  Tổng
                                </th>
                                <th className="text-end">{order.total}</th>
                              </tr>
                              </tfoot>
                            </table>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div className="d-block d-md-flex">
                      {order.state === 'PROCESSING'?
                          <button className="btn btn-danger btn-lg w-100 mt-md-4"
                                  data-bs-dismiss="modal"
                                  aria-label="Close"
                                  onClick={handleRequestCancelOrder}>
                            Huỷ đơn hàng
                          </button>: <div></div>}
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>
        </div>
        <div className="table-responsive">
          <table className="table">
            <thead>
            <tr>
              <th
                  scope="col"
                  className="text-secondary text-xs font-weight-bold border-light ps-2"
              >
                Sản phẩm
              </th>
              <th
                  scope="col"
                  className="text-secondary text-xs font-weight-bold border-light ps-2"
              >
                Đơn giá
              </th>
              <th
                  scope="col"
                  className="text-secondary text-xs font-weight-bold border-light ps-2"
              >
                Số lượng
              </th>
            </tr>
            </thead>
            <tbody>{orderInfoCards}</tbody>
          </table>
        </div>
      </div>
  );
}
