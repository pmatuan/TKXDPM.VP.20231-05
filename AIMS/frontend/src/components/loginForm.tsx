const LoginForm = () => {
    return (
        <div
            className="modal fade"
            id="loginModal"
            role="dialog"
            aria-labelledby="loginModalLabel"
            aria-hidden="true"
        >
            <div
                className="modal-dialog modal-dialog-centered modal-md"
                role="form"
            >
                <div className="modal-content">
                    <div className="card align-items-center">
                        <h3 className="mt-3">Đăng nhập</h3>
                        <div className="card-body d-block d-lg-flex p-4">
                            <div className="ps-lg-5">
                                <div
                                    className="d-flex align-items-start flex-column"
                                >
                                    <form>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label>Email</label>
                                                </td>
                                                <td>
                                                    <input
                                                        className="form-control"
                                                        type="text"
                                                        name="email"
                                                        id="email"
                                                    />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label className="my-2 mx-1">Mật khẩu</label>
                                                </td>
                                                <td>
                                                    <input
                                                        className="form-control my-2"
                                                        type="password"
                                                        name="password"
                                                        id="password"
                                                    />
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <button className="btn btn-dark btn-lg mx-1" id="loginBtn">Đăng nhập</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default LoginForm;