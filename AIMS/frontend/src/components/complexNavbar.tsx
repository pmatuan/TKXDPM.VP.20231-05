import React from "react";

const ComplexNavbar = () => {
  return (
    <nav className="top-0 flex-wrap px-0 py-0 d-none d-lg-block navbar shadow navbar-expand-lg">
      <div className="container py-2">
        <nav aria-label="breadcrumb">
          <div className="d-flex align-items-center">
            <span className="text-dark px-3 text-lg font-weight-bold me-4">
              <a href="/aims-ecommerce/">AIMS</a>
            </span>
          </div>
        </nav>
        <ul className="navbar-nav d-none d-lg-flex">
          <li className="nav-item px-3 py-3 border-radius-sm d-flex align-items-center">
            <a href="/aims-ecommerce/store/" className="p-0 nav-link">
              Cửa hàng
            </a>
          </li>
          <li className="nav-item px-3 py-3 border-radius-sm d-flex align-items-center">
            <a href="/aims-ecommerce/trace-order/" className="p-0 nav-link">
              Theo dõi đơn hàng
            </a>
          </li>
        </ul>
        <div
          className="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4"
          id="navbar"
        >
          <ul className="navbar-nav ms-md-auto  justify-content-end">
            <li className="nav-item d-flex align-items-center ps-2">
              <a href="#" className="px-0 nav-link font-weight-bold"></a>
            </li>
            <li className="nav-item dropdown pe-2 d-flex align-items-center">
              <a
                href="/aims-ecommerce/shopping-cart/"
                className="px-0 nav-link font-weight-bold"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="32"
                  height="32"
                  viewBox="0 0 16 16"
                >
                  <path
                    fill="currentColor"
                    d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607L1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4a2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4a2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2a1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2a1 1 0 0 1 0-2"
                  />
                </svg>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default ComplexNavbar;
