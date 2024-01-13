interface Props {
  subtotal: number;
  shippingFee: number;
  vat: number;
  total: number;
}

export default function OrderSummary({
  subtotal, shippingFee, vat, total
}: Props) {
  return (
    <>
      <ul className="list-unstyled">
        {subtotal >= 0 && (
            <li className="mt-2">
              <div className="d-flex justify-content-between">
                <p className={`opacity-8`}>Tổng đơn</p>
                <p className={`fw-bold opacity-8`}>{subtotal.toLocaleString()} đồng</p>
              </div>
            </li>
        )}
        {shippingFee >= 0 && (
            <li className="mt-2">
              <div className="d-flex justify-content-between">
                <p className={`opacity-8`}>Phí vận chuyển</p>
                <p className={`fw-bold opacity-8`}>{shippingFee.toLocaleString()} đồng</p>
              </div>
            </li>
        )}
        {vat >= 0 && (
            <li className="border-bottom mt-2">
              <div className="d-flex justify-content-between">
                <p className={`opacity-8`}>VAT <span data-bs-toggle="tooltip" data-bs-placement="top" title="This may vary depending on the country you are in" data-container="body" data-animation="true"></span></p>
                <p className={`fw-bold opacity-8`}>{vat.toLocaleString()} đồng</p>
              </div>
            </li>
        )}
        {total >= 0 && (
            <li className="mt-4">
              <div className="d-flex justify-content-between">
                <h5>Tổng thanh toán</h5>
                <h5>{total.toLocaleString()} đồng</h5>
              </div>
            </li>
        )}
      </ul>
    </>
  );
}