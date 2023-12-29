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

    if (result === 200) {
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
    const result = response.code
    if (result == 200) {
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
    const result = response.code
    if (result == 200) {
        window.location.reload()
    }
}

export async function changeBlockedState(userId: number, setIsBlocked: number) {
    const data = await fetch(`http://localhost:8080/api/v1/user/changeBlockedState?userId=${userId}&setIsBlocked=${setIsBlocked}`, {
        method: 'PUT'
    })

    const response = await data.json()
    const result = response.code
    if (result == 200) {
        window.location.reload()
    }
}

export async function deleteUser(userId: number) {
    // alert('delete user clicked')
    const data = await fetch(`http://localhost:8080/api/v1/user/delete?userId=${userId}`, {
        method: "DELETE",
    });

    console.log(data)

    window.location.reload()
}