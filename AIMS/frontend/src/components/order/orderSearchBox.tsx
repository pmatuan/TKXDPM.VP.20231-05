export default function OrderSearchBox({}) {
  return (
      <div className="row">
        <div className="col-8">
          <div className="form-group">
            <input type="text" className="form-control" placeholder="Nhập mã đơn hàng của bạn" />
          </div>
        </div>
        <div className="col-4">
          <button className="btn btn-dark">Tìm kiếm</button>
        </div>
      </div>
  )
}
