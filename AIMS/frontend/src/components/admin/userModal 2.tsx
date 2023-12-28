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
                className="modal-dialog modal-dialog-centered modal-xl"
                role="form"
            >
                <div className="modal-content">
                    <div className="card">
                        <div className="card-body d-block d-lg-flex p-4">
                            <form id="userForm">
                                <table>
                                    <tr>
                                        <td>
                                            <label htmlFor="name">Name</label>
                                        </td>
                                        <td>
                                            <input type="text" name="name" id="name" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label htmlFor="email">Email</label>
                                        </td>
                                        <td>
                                            <input
                                                type="text"
                                                name="email"
                                                id="email"
                                            />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label htmlFor="phoneNumber">Phone number</label>
                                        </td>
                                        <td>
                                            <input
                                                type="text"
                                                name="phoneNumber"
                                                id="phoneNumber"
                                            />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label htmlFor="password">Password</label>
                                        </td>
                                        <td>
                                            <input
                                                type="text"
                                                name="password"
                                                id="password"
                                            />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label htmlFor="role">Role</label>
                                        </td>
                                        <td>
                                            <input type="text" name="role" id="role" />
                                        </td>
                                    </tr>
                                </table>
                                <br></br>
                                <button
                                    className="btn btn-light btn-md mx-1"
                                    data-bs-toggle="modal"
                                    data-bs-target="#createUserModal"
                                    id="cancelCreateUser"
                                >
                                    Cancel
                                </button>
                                <button
                                    className="btn btn-dark btn-md mx-1"
                                    id="submitCreateUser"
                                    type="submit"
                                >
                                    Submit
                                </button>
                            </form>
                        </div>
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
                className="modal-dialog modal-dialog-centered modal-xl"
                role="form"
            >
                <div className="modal-content">
                    <div className="card">
                        <div className="card-body d-block d-lg-flex p-4">
                            <div className="w-100 w-lg-70 ps-lg-5 mt-4 mt-md-5">
                                <div className="d-flex align-items-start flex-column">
                                    <span>ID: {user.id}</span>
                                    <form>
                                        <table>
                                            <tr>
                                                <td><label htmlFor="name">Name</label></td>
                                                <td><input type="text" name="name" id="name" value={user.name} disabled /></td>
                                            </tr>
                                            <tr>
                                                <td><label htmlFor="email">Email</label></td>
                                                <td><input
                                                    type="text"
                                                    name="email"
                                                    id="email"
                                                    value={user.email}
                                                    disabled
                                                /></td>
                                            </tr>
                                            <tr>
                                                <td><label htmlFor="phoneNumber">Phone number</label></td>
                                                <td><input
                                                    type="text"
                                                    name="phoneNumber"
                                                    id="phoneNumber"
                                                    value={user.phoneNumber}
                                                    disabled
                                                /></td>
                                            </tr>
                                            <tr>
                                                <td><label htmlFor="role">Role</label></td>
                                                <td><input type="text" name="role" id="role" value={user.role} disabled /></td>
                                            </tr>
                                        </table>
                                    </form>


                                    <span>Password: ********<button className="btn btn-dark btn-sm mx-1">Change password</button></span>


                                    <span>Account state: {user.isBlocked === 1 ? 'Blocked' : 'Not blocked'} <button className="btn btn-dark btn-sm">Change</button></span>


                                    <span>
                                        <button className="btn btn-white btn-sm mx-1" data-bs-toggle="modal" data-bs-target={'#viewUserModal' + user.id}>Close</button>
                                        <button className="btn btn-dark btn-sm mx-1">Edit</button>
                                        {/* <button className="btn btn-dark btn-sm mx-1 display-hidden">Submit</button> */}
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}