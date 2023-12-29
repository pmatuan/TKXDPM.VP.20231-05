import { useEffect } from "react";

// export async function createUserTest() {
//     const data = await fetch("http://localhost:8080/api/v1/user/create", {
//         method: "POST",
//         headers: {
//             'Content-type': 'application/json'
//         },
//         body: JSON.stringify({
//             name: "test",
//             email: "test",
//             phoneNumber: "test",
//             password: "test",
//             role: "Customer",
//         }),
//     });

//     const response = await data.json()
//     const result = response.code
// }

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
            role: user.role,
        }),
    });

    const response = await data.json()
    const result = response.code
}

export async function getUser(userId: number) {
    const data = await fetch(`http://localhost:8080/api/v1/user/${userId}`)
    const response = await data.json()
    const user = response.result.user

    return user
}

export async function editUser(user: any) {
    var body = JSON.stringify({})
    const data = await fetch(`http://localhost:8080/api/v1/user/update/${user.id}`, {
        method: "PUT",
        body: body
    })
}

export async function changePassword(userId: number, password: string) {
    const data = await fetch(`http://localhost:8080/api/v1/user/changePassword`, {
        method: 'PUT',
        body: JSON.stringify({
            userId: userId,
            password: password
        })
    })
}

export async function changeBlockedState(userId: number, setIsBlocked: number) {
    const data = await fetch(`http://localhost:8080/api/v1/user/changeBlockedState?userId=${userId}&setIsBlocked=${setIsBlocked}`, {
        method: 'PUT'
    })
}

export async function deleteUser(userId: number) {
    // alert('delete user clicked')
    const data = await fetch(`http://localhost:8080/api/v1/user/delete?userId=${userId}`, {
        method: "DELETE",
    });

    console.log(data)

    window.location.reload()
}