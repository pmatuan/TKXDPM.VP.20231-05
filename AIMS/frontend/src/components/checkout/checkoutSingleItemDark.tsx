interface Props {
  imageUrl: string;
  title: string;
  price: number;
  quantityInStock: number;
  quantity: number;
  isAbleToRushDelivery: boolean;
  isOrderForRushDelivery: boolean;
}

export default function CheckoutSingleItem({
  imageUrl,
  title,
  price,
  quantityInStock,
  quantity,
  isAbleToRushDelivery,
  isOrderForRushDelivery,
}: Props) {
  return (
    <>
      <div className="card card-product card-plain d-flex mb-4">
        <div className="row">
          <div className="col-4 col-md-2">
            <img
              className="w-100 max-height-100 rounded-3"
              src={
                imageUrl.includes("http")
                    ? `${imageUrl}`
                    : `${import.meta.env.BASE_URL}${imageUrl}`
              }
            />
          </div>
          <div className="col-5 col-md-6">
            <h5 className="text-base mb-1">{title}</h5>
            <h6 className="text-sm font-weight-bold mb-0">
              {price.toLocaleString()} đồng
            </h6>
            <div className="d-flex align-items-center mt-2">
              {isOrderForRushDelivery ? (
                  isAbleToRushDelivery ? (
                      <>
                        <i className="fas fa-check text-lg text-success"></i>
                        <p className="mb-0 ms-2 text-sm">Hỗ trợ giao hàng nhanh</p>
                      </>
                  ) : (
                      <>
                        <i className="fas fa-minus-circle text-lg"></i>
                        <p className="mb-0 ms-2 text-sm">Không hỗ trợ giao hàng nhanh</p>
                      </>
                  )
              ) : (
                  <div></div>
              )}
            </div>

          </div>
          <div className="col-2">
            <input
                type="number"
                min={1}
                className="form-control"
                value={String(quantity)}
                aria-label="amount"
                readOnly
            />
          </div>
        </div>
      </div>
    </>
  );
}
