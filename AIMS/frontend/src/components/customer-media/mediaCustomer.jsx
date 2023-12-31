import ComplexNavbar from "../complexNavbar";
import CategoryFilters from "./categoryFilters";
import "../../../assets/scss/astro-ecommerce.scss";
export default function MediaCustomer({ cartId }) {
  return (
    <div>
      <div
        style={{
          position: "sticky",
          top: 0,
          backgroundColor: "#fff",
          zIndex: 1000,
        }}
      >
        <ComplexNavbar />
      </div>

      <div className="container mt-5">
        <CategoryFilters title="Sản phẩm" cartId={cartId} />
      </div>
    </div>
  );
}
