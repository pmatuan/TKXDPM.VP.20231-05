interface Props {
  subtotal: number;
  textColor: string
}

export default function OrderSummary({
  subtotal,
  textColor
}: Props) {

  const tax = 7;
  const shipping = (subtotal >= 100) ?  0 : 25;
  let sum = 0;
  sum+=subtotal;

  let variant = "";

  if (textColor) {
    variant = ` text-${textColor}`;
  }
  return (
    <>
      <ul className="list-unstyled">
        <li className="mt-2">
          <div className="d-flex justify-content-between">
            <p className={`opacity-8` + variant}>Tổng đơn</p>
            <p className={`fw-bold opacity-8` + variant}>${sum.toLocaleString()}</p>
          </div>
        </li>
        <li className="border-bottom mt-2">
          <div className="d-flex justify-content-between">
            <p className={`opacity-8` + variant}>VAT <span data-bs-toggle="tooltip" data-bs-placement="top" title="This may vary depending on the country you are in" data-container="body" data-animation="true"></span></p>
            <p className={`fw-bold opacity-8` + variant}>${tax.toLocaleString()}</p>
          </div>
        </li>
        <li className="mt-4">
          <div className="d-flex justify-content-between">
            <h5 className={variant}>Tổng thanh toán</h5>
            <h5 className={variant}>${(subtotal + shipping + tax).toLocaleString()}</h5>
          </div>
        </li>
      </ul>
    </>
  );
}