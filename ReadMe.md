- JPA Entity Mapping Example

![image](https://github.com/AngryPig123/AngryPig123.github.io/assets/86225268/b0dba12e-c725-40e9-bd11-ac834e4ede37)

- step
    - customer : order_header
    - order_header : order_line
    - order_line : pizza
    - order_header : delivery
    - order_header : payment

- 수정 사항 (erd에서 삭제된 목록)
  - order_line : pk 추가
  - order_header : delivery_id(FK) 추가
  - delivery : order_id 삭제
  - payment : customer_id 삭제