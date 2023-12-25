# TKXDPM.VN.20231-05

This is a Capstone's source code for Software Design and Construction project

## Team member

| Name                 | Role        |
| :------------------- | :---------- |
| Phan Minh Anh Tuấn   | Team Leader |
| Nguyễn Thị Hoài Linh | Member      |
| Vũ Minh Long         | Member      |
| Ngô Thị Lam          | Member      |

## Report Content

<details>
  <summary>W10: 28/11/2023~04/12/2023 </summary>
<br>
<details>
<summary>Phan Minh Anh Tuấn</summary>
<br>

- Assigned tasks:

  - Refactor the code base of AIMS project

- Implementation details:
  - Pull Request(s): [https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/1](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/1)
    [https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/2](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/2)
    [https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/3](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/3)
  - Specific implementation details:
    - Refactor code of cart, place-order and payment

</details>

<details>
<summary>Nguyễn Thị Hoài Linh</summary>
<br>

- Assigned tasks:

  - Conducting an analysis of coupling levels between classes related to payment functionality.

- Implementation details:
  - Pull Request(s): [Link to pull request](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/5)
  - Specific implementation details:
    - Conducted an analysis of the coupling between classes related to payment functionality, then introduced comments in the code indicating the type of coupling

</details>

<details>
<summary>Vũ Minh Long</summary>
<br>

- Assigned tasks:

  - Conducting an analysis of coupling levels between classes related to payment functionality.

- Implementation details:
  - Pull Request(s): [https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/9](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/9)
  - Specific implementation details:
    - Conducted an analysis of the coupling between classes related to payment functionality, then introduced comments in the code indicating the type of coupling and potential solution to reduce coupling.

</details>

<details>
<summary>Ngô Thị Lam</summary>
<br>
- Assigned tasks:

- Conducting an analysis of coupling levels between classes related to cart functionality.

- Implementation details:
  - Pull Request(s): https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/7
  - Specific implementation details:
    - Comments code indicating the type of coupling

</details>

</details>

<details>
  <summary>W11: 05/12/2023~11/12/2023 </summary>
<br>

<details>
<summary>Phan Minh Anh Tuấn</summary>
<br>

- Assigned Tasks:

  - Fix control coupling in `PaymentSubsystemFactory`
  - Resolve stamp coupling in the handling process of the place order service
  - Correct the calculation of order shipping fees
  - Introduce a new exception class
  - Enhance cohesion in the cart and order service

- Implementation Details:

  - Pull Request(s):

    - [PR #11](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/11)
    - [PR #12](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/12)
    - [PR #14](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/14)
    - [PR #15](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/15)
    - [PR #16](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/16)
    - [PR #17](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/17)

  - Specific Implementation Details:

    - Fix control coupling in `PaymentSubsystemFactory` using annotations and reflection.
    - Resolve stamp coupling in the place order service by modifying the handling of stamp-related processes.
    - Correct the calculation of order shipping fees.
    - Introduce a new exception class; avoid direct usage of `AimsException`.
    - Enhance cohesion in cart and order service by splitting into subclasses.

</details>

<details>
<summary>Nguyễn Thị Hoài Linh</summary>
<br>

- Assigned tasks:

  - Conducting an analysis of cohesion levels between classes related to payment functionality.
  - Developing front-end code base

- Implementation details:
  - Pull Request(s):
    - [Pull request for analysis of cohesion levels](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/18)
    - [Pull request for front-end code base](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/13)
  - Specific implementation details:
    - Conducted cohesion analysis between payment-related classes, identified types of cohesion, and added comments in the code.
    - Created the front-end code base, focusing on user interface components.

</details>

<details>
<summary>Ngô Thị Lam</summary>
<br>

- Assigned tasks:

  - Conduct analysis of cohesion levels in cart related classes

- Implementation details:
  - Pull Request(s):
    - [Cohesion analysis](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/20)
  - Specific implementation details:
    - Analyze cohesion levels in cart related classes by adding comments in the source code.

</details>

<details>
<summary>Vũ Minh Long</summary>
<br>

- Assigned tasks:

  - Conduct analysis of cohesion levels in place order related classes.

- Implementation details:
  - Pull Request(s):
    - [Cohesion analysis](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/22)
  - Specific implementation details:
    - Analyze cohesion levels in place related classes by adding comments in the source code.

</details>
</details>

<details>
  <summary>W12: 12/12/2023~18/12/2023 </summary>
<br>

<details>
<summary>Phan Minh Anh Tuấn</summary>
<br>

- Assigned Tasks:

  - Analyze the design principles of order.
  - Coding use case cancel order.

- Implementation Details:

  - Pull Request(s):

    - [PR #27](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/27)
    - [PR #28](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/31)

  - Specific Implementation Details:

    - Analyze the SOLID design principles of classes related to order.
    - Coding classes of use case cancel order.

</details>

<details>
<summary>Nguyễn Thị Hoài Linh</summary>
<br>

- Assigned tasks:

  - Reviewed and commented on the adherence of the payment-related classes to the SOLID principles.
  - Implementing code related to order processing for the admin.

- Implementation details:
  - Pull Request(s):
    - [PR - SOLID principles](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/32)
    - [PR - Order processing](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/26)
  - Specific implementation details:
    - Conducted a analysis of the adherence to SOLID principles in payment-related classes.
    - Added comments to the code reflecting the adherence to SOLID principles.
    - Implemented order processing functionality.

</details>

<details>
<summary>Ngô Thị Lam</summary>
<br>

- Assigned tasks:

  - Analyze SOLID of cart related classes
  - Implement CRUD feature for Media related classes

- Implementation details:
  - Pull Request(s):
    - [29](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/29)
    - [33](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/33)
  - Specific implementation details:
    - Added MediaController, MediaService and necessary DTO classes
    - Added CRUD methods in MediaController, MediaService

</details>

<details>
<summary>Vũ Minh Long</summary>
<br>

- Assigned tasks:

  - Implement user management use cases for admin.
  - Review and comment on the adherence of user management related classes to the SOLID principles.

- Implementation details:
  - Pull Request(s):
    - [User management](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/30)
    - [SOLID principles](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/36)
  - Specific implementation details:
    - Implement code for user management related entity, repository, service and controller classes.
    - Conducted an analysis of the adherence to SOLID principles in user managment related classes.

</details>
</details>

<details>
  <summary>W13: 19/12/2023~25/12/2023 </summary>
<br>

<details>
<summary>Phan Minh Anh Tuấn</summary>
<br>

- Assigned Tasks:

  - Connect the frontend to the backend of the order section (cart and order).
  - Save cartId to cookie.
  - Fix CORS in backend.

- Implementation Details:

  - Pull Request(s):

    - [PR #39](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/39)
    - [PR #41](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/41)
    - [PR #42](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/42)
    - [PR #43](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/43)
    - [PR #44](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/44)

  - Specific Implementation Details:

    - Connect the frontend to the backend of the order section (cart and order).
    - Initial cartId and save it to cookie if user don't have cart before.
    - Fix CORS in backend by add CorsConfig class.

</details>

<details>
<summary>Nguyễn Thị Hoài Linh</summary>
<br>

- Assigned tasks:

  - 

- Implementation details:
  - Pull Request(s):
    - 
  - Specific implementation details:
    - 

</details>

<details>
<summary>Ngô Thị Lam</summary>
<br>

- Assigned tasks:

  - 

- Implementation details:
  - Pull Request(s):
    - 
  - Specific implementation details:
    - 

</details>

<details>
<summary>Vũ Minh Long</summary>
<br>

- Assigned tasks:

  - Implement front-end code for user management.

- Implementation details:
  - Pull Request(s):
    - [PR #47](https://github.com/pmatuan/TKXDPM.VP.20231-05/pull/47)
  - Specific implementation details:
    - Implement admin menu for user management.
    - Edit user hasn't been fully implemented.

</details>
</details>
