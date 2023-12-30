interface Props {
  orderId: string,
  cancelTime: string,
}

export default function OrderDetailCancel({
                                      orderId,
                                      cancelTime,
                                    }: Props) {
  return (
      <>
        <div className="body py-5">
          <div className="container">
            <div className="w-50 m-auto">
              <h2 className="mb-3 text-center text-success mb-5">Huỷ đơn hàng thành công</h2>
              <h4 className="my-2">Chi tiết đơn hàng</h4>
              <table className="table table-bordered">
                <tbody>
                <tr>
                  <td>Mã đơn hàng:</td>
                  <td><span>{orderId}</span></td>
                </tr>
                <tr>
                  <td>Thời gian huỷ đơn hàng:</td>
                  <td><span>{cancelTime}</span></td>
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
