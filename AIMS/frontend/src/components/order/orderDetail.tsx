interface Props {
  paymentStatus: string,
  orderId: string,
  amount: string,
  paymentTime: string,
  transactionId: string,
  paymentMethod: string,
}

export default function OrderDetail({
                                      paymentStatus,
                                      orderId,
                                      amount,
                                      paymentTime,
                                      transactionId,
                                      paymentMethod
                                    }: Props) {
  return (
      <>
        <div className="body py-5">
          <div className="container">
            <div className="w-50 m-auto">
              {paymentStatus == "true" ?
                  <h2 className="mb-3 text-center text-success mb-5">Thanh toán thành công</h2> :
                  <h2 className="mb-3 text-center text-danger mb-5">Thanh toán thất bại</h2>}
              <h4 className="my-2">Chi tiết đơn hàng</h4>
              <table className="table table-bordered">
                <tbody>
                <tr>
                  <td>Mã đơn hàng:</td>
                  <td><span>{orderId}</span></td>
                </tr>
                <tr>
                  <td>Mã giao dịch:</td>
                  <td><span>{transactionId}</span></td>
                </tr>
                <tr>
                  <td>Phương thức thanh toán:</td>
                  <td><span>{paymentMethod}</span></td>
                </tr>
                <tr>
                  <td>Tổng tiền thanh toán:</td>
                  <td><span>{amount} </span>
                    {paymentMethod === "Paypal" ? <span>USD</span>: <span>đồng</span>}
                  </td>
                </tr>
                <tr>
                  <td>Thời gian thanh toán:</td>
                  <td><span>{paymentTime}</span></td>
                </tr>
                </tbody>
              </table>
              <a href="/aims-ecommerce/" className="btn btn-dark btn-primary">Về trang chủ</a>
            </div>
          </div>
        </div>
      </>
  );
}
