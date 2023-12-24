interface Props {
  summary: {
    subtotal: number;
    shippingFee: number;
    vat: number;
    total: number;
  }
}

export default function OrderSummary({
  summary
}: Props) {

  return (
    <>
      <ul className="list-unstyled">
        {summary.subtotal > 0 && (
            <li className="mt-2">
              <div className="d-flex justify-content-between">
                <p className={`opacity-8`}>Tổng đơn</p>
                <p className={`fw-bold opacity-8`}>{summary.subtotal.toLocaleString()} đồng</p>
              </div>
            </li>
        )}
        {summary.shippingFee >= 0 && (
            <li className="mt-2">
              <div className="d-flex justify-content-between">
                <p className={`opacity-8`}>Phí vận chuyển</p>
                <p className={`fw-bold opacity-8`}>{summary.shippingFee.toLocaleString()} đồng</p>
              </div>
            </li>
        )}
        {summary.vat > 0 && (
            <li className="border-bottom mt-2">
              <div className="d-flex justify-content-between">
                <p className={`opacity-8`}>VAT <span data-bs-toggle="tooltip" data-bs-placement="top" title="This may vary depending on the country you are in" data-container="body" data-animation="true"></span></p>
                <p className={`fw-bold opacity-8`}>{summary.vat.toLocaleString()} đồng</p>
              </div>
            </li>
        )}
        {summary.total > 0 && (
            <li className="mt-4">
              <div className="d-flex justify-content-between">
                <h5>Tổng thanh toán</h5>
                <h5>{summary.total.toLocaleString()} đồng</h5>
              </div>
            </li>
        )}
      </ul>
    </>
  );
}