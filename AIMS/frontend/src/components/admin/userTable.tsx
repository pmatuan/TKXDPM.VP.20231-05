import UserRow from "./userRow"

interface Props {
    users: any
}

export default function UserTable({
    users,
}: Props) {

    return (
        <div className="table-responsive">
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col" className="text-secondary text-xs font-weight-bold border-light ps-2">Họ và tên</th>
                        <th scope="col" className="text-secondary text-xs font-weight-bold border-light ps-2">Email</th>
                        <th scope="col" className="text-secondary text-xs font-weight-bold border-light ps-2">Số điện thoại</th>
                        <th scope="col" className="text-secondary text-xs font-weight-bold border-light ps-2">Vai trò</th>
                        {/* <th scope="col" className="text-secondary text-xs font-weight-bold border-light ps-2 text-end">Details</th> */}
                        <th scope="col" className="text-secondary text-xs font-weight-bold border-light ps-2 text-center">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user: any) => <UserRow user={user} />)}
                </tbody>
            </table>
        </div>
    )
}