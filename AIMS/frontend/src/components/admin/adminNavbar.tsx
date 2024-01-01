import React from "react";

export default function AdminNavbar() {
    return (
        <nav className="top-0 flex-wrap px-0 py-0 d-none d-lg-block navbar shadow navbar-expand-lg">
            <div className="container py-2 justify-content-start">
                <nav aria-label="breadcrumb">
                    <div className="d-flex align-items-center">
                        <span className="text-dark px-3 text-lg font-weight-bold me-4">
                            <a href="/aims-ecommerce/admin">
                                AIMS Management
                            </a>
                        </span>
                    </div>
                </nav>
                <ul className="navbar-nav d-none d-lg-flex w-75">
                    <li className="nav-item px-3 py-3 border-radius-sm d-flex align-items-center" id="manageMedia">
                        <a href="/aims-ecommerce/management/home" className="p-0 nav-link">
                            Quản lý sản phẩm
                        </a>
                    </li>
                    <li className="nav-item px-3 py-3 border-radius-sm d-flex align-items-center" id="manageOrder">
                        <a href="/aims-ecommerce/management/order/" className="p-0 nav-link">
                            Quản lý đơn hàng
                        </a>
                    </li>
                    {/* <li className="nav-item px-3 py-3 border-radius-sm d-flex align-items-center" id="manageOrder">
                        <a href="/aims-ecommerce/trace-order/" className="p-0 nav-link">
                            Quản lý đơn hàng
                        </a>
                    </li> */}
                    <li className="nav-item px-3 py-3 border-radius-sm d-flex align-items-center" id="manageUser">
                        <a href="/aims-ecommerce/management/manage-user" className="p-0 nav-link">
                            Quản lý người dùng
                        </a>
                    </li>
                    <li className="nav-item px-3 py-3 border-radius-sm d-flex align-items-center ms-auto" id="manageUser">
                        <a href="/aims-ecommerce/" className="p-0 nav-link">
                            Đăng xuất
                        </a>
                    </li>
                </ul>
            </div>
        </nav >
    );
};
