export async function createUser(user: any) {
    const data = await fetch("http://localhost:8080/api/v1/user/create", {
        method: "POST",
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            name: user.name,
            email: user.email,
            phoneNumber: user.phoneNumber,
            password: user.password,
            role: user.role.toLowerCase(),
        }),
    });

    const response = await data.json()
    const result = response.code

    console.log(response)

    switch(result) {
        case "EMAIL_001":
            alert("Email trống hoặc không hợp lệ")
            break
        case "PASSWORD_001":
            alert("Mật khẩu không được để trống")
            break
        case "ROLE_001":
            alert("Chưa chọn vai trò")
            break
        case "EMAIL_002":
            alert("Email đã được sử dụng cho một tài khoản khác")
    }

    if (result == 200) {
        alert("Tạo mới người dùng thành công")
        window.location.reload()
    }
}

export async function getUser(userId: number) {
    const data = await fetch(`http://localhost:8080/api/v1/user/${userId}`)
    const response = await data.json()
    const user = response.result.user

    return user
}

export async function editUser(user: any) {
    const data = await fetch(`http://localhost:8080/api/v1/user/update/${user.id}`, {
        method: "PUT",
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            name: user.name,
            phoneNumber: user.phoneNumber,
            email: user.email,
            role: user.role.toLowerCase()
        })
    })

    const response = await data.json()
    console.log(response)
    const result = response.code

    switch(result) {
        case "ROLE_001":
            alert("Chọn ít nhất 1 vai trò")
            break
        case "EMAIL_001":
            alert("Email không được để trống")
            break
        case "EMAIL_002":
            alert("Email đã được sử dụng cho một tài khoản khác")
    }

    if (result == 200) {
        alert("Chỉnh sửa thành công")
        window.location.reload()
    }
}

export async function changePassword(userId: number, password: string) {
    const data = await fetch(`http://localhost:8080/api/v1/user/changePassword`, {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            userId: userId,
            password: password
        })
    })

    const response = await data.json()

    console.log(response)

    const result = response.code
    if (result == 'PASSWORD_001') {
        alert("Mật khẩu không được để trống")
    }

    if (result == 200) {
        alert("Đổi mật khẩu thành công")
        window.location.reload()
    }
}

export async function changeBlockedState(userId: number, setIsBlocked: number) {
    const data = await fetch(`http://localhost:8080/api/v1/user/changeBlockedState?userId=${userId}&setIsBlocked=${setIsBlocked}`, {
        method: 'PUT'
    })

    const response = await data.json()
    console.log(setIsBlocked)
    console.log(response)
    const result = response.code
    
    if (result == 200) {
        if (setIsBlocked == 1) {
            alert("Chặn thành công")
        } else {
            alert("Bỏ chặn thành công")
        }
        window.location.reload()
    }
}

export async function deleteUser(userId: number) {
    // alert('delete user clicked')
    const data = await fetch(`http://localhost:8080/api/v1/user/delete?userId=${userId}`, {
        method: "DELETE",
    });

    const response = await data.json()

    window.location.reload()
}