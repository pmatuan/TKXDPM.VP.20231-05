import "bootstrap/dist/css/bootstrap.min.css";

const Header = () => (
  <nav className="top-0 flex-wrap px-0 py-0 d-none d-lg-block navbar shadow navbar-expand-lg">
    <div className="container py-2">
      <nav aria-label="breadcrumb">
        <div className="d-flex align-items-center">
          <span className="text-dark px-3 text-lg font-weight-bold me-4">
            <a
              href="/aims-ecommerce/admin/media/home"
              className="p-0 nav-link"
              style={{ fontSize: "24px" }}
            >
              AIMS
            </a>
          </span>
        </div>
      </nav>
      <ul className="navbar-nav d-none d-lg-flex">
        <li className="nav-item px-3 py-3 border-radius-sm d-flex align-items-center">
          <a href="/aims-ecommerce/admin/media/home" className="p-0 nav-link">
            Quản lý sản phẩm
          </a>
        </li>
      </ul>
    </div>
  </nav>
);
export default Header;
