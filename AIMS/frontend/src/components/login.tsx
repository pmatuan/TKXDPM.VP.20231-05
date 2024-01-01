export async function login(
    credentials: any
) {
    const data = await fetch("http://localhost:8080/api/v1/login", {
      method: "POST",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify(credentials),
    });

    const response = await data.json();
    console.log(response);

    switch(response.code) {
      case "EMAIL_001":
        alert("Email không được để trống")
        break
      case "LOGIN_001":
        alert("Không tìm thấy email")
        break;
      case "LOGIN_002":
        alert("Sai mật khẩu")
        break;
    } 

    const id = response.result.id;
    const role = response.result.role;

    localStorage.setItem("id", id)
    localStorage.setItem("role", role)

    if (role.includes("product manager")) {
      window.location.href = "management/home"; // home for product manager
    } else {
      window.location.href = "management/manage-user"; // home for admin
    }
}