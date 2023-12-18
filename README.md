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

  - 
  - 

- Implementation details:
  - Pull Request(s):
    - []()
  - Specific implementation details:
    - 
    - 

</details>

<details>
<summary>Ngô Thị Lam</summary>
<br>

- Assigned tasks:

  - 

- Implementation details:
  - Pull Request(s):
    - []()
  - Specific implementation details:
    - 

</details>

<details>
<summary>Vũ Minh Long</summary>
<br>

- Assigned tasks:

  - 

- Implementation details:
  - Pull Request(s):
    - []()
  - Specific implementation details:
    - 

</details>
</details>
