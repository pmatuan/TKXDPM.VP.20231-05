interface Props {
  product: {
    id: string;
    imageUrl: string;
    quantity: number;
    title: string;
    price: number;
  };
}

export default function OrderSummary({product}: Props) {
  return (
      <>
        <tr>
          <td
              scope="row"
              className="d-flex align-items-center text-sm text-body py-3"
          >
            <img
                className="w-20 w-lg-10 rounded-3"

                src={`${product.imageUrl}`}
            />
            <p className="text-sm text-dark font-weight-bold mb-0 ms-3">
              {product.title}
            </p>
          </td>
          <td className="text-sm text-body align-middle pt-3 w-20">
            {product.price.toLocaleString()} đồng
          </td>
          <td className="text-sm text-body align-middle pt-3  w-20">{product.quantity}</td>
        </tr>
      </>
  );
}
