export const CreateUserModal = () => {
    return (
        <div
            className="modal fade"
            id="createUserModal"
            role="dialog"
            aria-labelledby="createUserModalLabel"
            aria-hidden="true"
        >
            <div
                // className="modal-dialog modal-dialog-centered modal-xl"
                className="modal-dialog modal-dialog-centered"
                role="form"
            >
                <div className="modal-content">
                    <div className="card align-items-center">
                        <h3 className="mt-3">Tạo người dùng mới</h3>
                        <div className="card-body d-block d-lg-flex p-4">
                            <form id="createUserForm">
                                <table>
                                    <tr>
                                        <td className="text-end">
                                            <label htmlFor="name" className="mx-1 my-2">Họ và tên</label>
                                        </td>
                                        <td>
                                            <input type="text" name="name" id="name" className="form-control my-1" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td className="text-end">
                                            <label htmlFor="email" className="mx-1 my-2">Email</label>
                                        </td>
                                        <td>
                                            <input
                                                type="text"
                                                name="email"
                                                id="email"
                                                className="form-control my-1"
                                            />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td className="text-end">
                                            <label htmlFor="phoneNumber" className="mx-1 my-2">Số điện thoại</label>
                                        </td>
                                        <td>
                                            <input
                                                className="form-control my-1"
                                                type="text"
                                                name="phoneNumber"
                                                id="phoneNumber"
                                            />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td className="text-end">
                                            <label htmlFor="password" className="mx-1 my-2">Mật khẩu</label>
                                        </td>
                                        <td>
                                            <input
                                                type="text"
                                                name="password"
                                                id="password"
                                                className="form-control my-"
                                            />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td className="text-end">
                                            <label htmlFor="role" className="mx-1 my-2">Vai trò</label>
                                        </td>
                                        <td>
                                            <input type="text" name="role" id="role" className="form-control my-1" />
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <span>
                            <button
                                className="btn btn-light btn-md mx-1"
                                data-bs-toggle="modal"
                                data-bs-target="#createUserModal"
                                id="cancelCreateUser"
                            >
                                Hủy
                            </button>
                            <button
                                className="btn btn-dark btn-md mx-1"
                                id="submitCreateUser"
                                type="submit"
                            >
                                Tạo
                            </button>
                        </span>
                    </div>
                </div>
            </div>
        </div >
    );
};

interface Props {
    user: any
}

export function ViewUserModal({ user }: Props) {
    return (
        <div
            className="modal fade"
            id={'viewUserModal' + user.id}
            role="dialog"
            aria-labelledby={"viewUserModal" + user.id + "Label"}
            aria-hidden="true"
        >
            <div
                // className="modal-dialog modal-dialog-centered modal-xl"
                className="modal-dialog modal-dialog-centered"
                role="form"
            >
                <div className="modal-content">
                    <div className="card align-items-center">
                        <h3 className="mt-3">Thông tin người dùng</h3>
                        <span>ID: {user.id}</span>
                        <div className="card-body d-block d-lg-flex p-4">
                            {/* <div className="w-100 w-lg-70 ps-lg-5 mt-4 mt-md-5"> */}
                            <div className="w-100 ps-lg-5">
                                <div className="d-flex align-items-start flex-column">
                                    <form id="editUserForm">
                                        <table>
                                            <tr>
                                                <td className="text-end"><label htmlFor="name" className="mx-1 my-2">Họ và tên</label></td>
                                                <td><input type="text" name="name" id="name" value={user.name} disabled className="form-control my-1" /></td>
                                            </tr>
                                            <tr>
                                                <td className="text-end"><label htmlFor="email" className="mx-1 my-2">Email</label></td>
                                                <td><input
                                                    type="text"
                                                    name="email"
                                                    id="email"
                                                    value={user.email}
                                                    disabled
                                                    className="form-control my-1"
                                                /></td>
                                            </tr>
                                            <tr>
                                                <td className="text-end"><label htmlFor="phoneNumber" className="mx-1 my-2">Số điện thoại</label></td>
                                                <td><input
                                                    type="text"
                                                    name="phoneNumber"
                                                    id="phoneNumber"
                                                    value={user.phoneNumber}
                                                    disabled
                                                    className="form-control my-1"
                                                /></td>
                                            </tr>
                                            <tr className="text-end">
                                                <td><label htmlFor="role" className="mx-1 my-2">Vai trò</label></td>
                                                <td><input type="text" name="role" id="role" value={user.role} disabled className="form-control my-1" /></td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <span>Mật khẩu: <button className="btn btn-dark btn-sm mx-1 mt-2" id={"changePasswordBtn" + user.id}>Đổi mật khẩu</button></span>

                        <form className="d-none" id={"newPasswordForm" + user.id}>
                            <table>
                                <tr>
                                    <td>
                                        <label htmlFor="newPassword" className="mx-1 my-2">Nhập mật khẩu mới</label>
                                    </td>
                                    <td>
                                        <input type="password" name="newPassword" id="newPassword" className="form-control my-1" />
                                    </td>
                                </tr>
                            </table>
                        </form>

                        <button className="btn btn-dark btn-md mx-1 d-none my-1" id={"submitPasswordButton" + user.id}>Xác nhận</button>


                        <span className="my-1">Trạng thái tài khoản: {user.isBlocked === 1 ? 'Đã bị chặn' : 'Bình thường'} <button className="btn btn-dark btn-sm mt-3" id={"blockedButton" + user.id}>{user.isBlocked === 1 ? 'Bỏ chặn' : 'Chặn'}</button></span>


                        <span className="my-1">
                            <button className="btn btn-white btn-md mx-1" data-bs-toggle="modal" data-bs-target={'#viewUserModal' + user.id}>Hủy</button>
                            <button className="btn btn-dark btn-md mx-1 edit" id={'edit' + user.id}>Chỉnh sửa thông tin</button>
                            <button className="btn btn-dark btn-md mx-1 d-none submit" id={'submit' + user.id}>Xác nhận</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    )
}