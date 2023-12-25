import Layout from "../../layouts/Layout.astro";
import AdminNavbar from "../../components/admin/adminNavbar";
import UserRow from "../../components/admin/userRow";
import { result } from "./manage-user.astro";

// let users : any = []
// result.userList.array.forEach((user: any) => {
//     users.push(<UserRow name={user.name} email={user.email} role={user.role} />)
// });
<Fragment>
<Layout title="Manage user">
<main>
<AdminNavbar />
<div class="container my-3">
<div class="row">
<div class="col-md-9"></div>
<div class="col-md-3">
<button class="btn btn-dark btn-md">Create user</button>
</div>
</div>
<div class="row">
<div class="col">
{result.userList.map((user: any) => <Fragment><UserRow name={user.name} email={user.email} role={user.role} /></Fragment>
)}
</div>
</div>
</div>
</main>
</Layout>

</Fragment>;
