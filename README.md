# 💰 환전 요청 앱
- 한 고객이 여러 통화로 환전할 수 있고, 한 통화는 여러 고객에 의해 환전될 수 있음
- currency 테이블에 가지고 있는 환율을 기준으로 환전을 수행
- 특정 고객이 수행한 환전 요청 조회
- 특정 환전 요청 상태를 변경할 수 있음
- 고객이 삭제될 때 해당 고객이 수행한 모든 환전 요청도 삭제

# ⭐ 필수 구현 STEP
- Lv. 1 고객(User)과 통화(Currency) 복잡한 연관관계
- Lv. 2 환전 요청 CRUD
- Lv. 3 예외 처리

# 👊 도전 구현 STEP
- Lv. 4 PostConstruct 적용
- Lv. 5 JPQL
- Lv. 6 달러 이외 통화를 환전할 수 있도록 수정

------------------

# 📄 API 명세서
| 기능  |Method|URL|RequestHeader|Request|Response|상태코드|비고|
|:----|:---:|:---|:---|:---|:---|:---|:---|
|환전 요청|POST|/exchanges|POST /exchanges HTTP/1.1</br>Content-Type: application/json|{</br>"user_id": 1,</br>"currency_id": 1,</br>"amount_in_krw": 10000</br>}|{</br>"id": 1,</br>"user_id": 1,</br>"currency_id": 1,</br>"amount_in_krw": 10000,</br>"amount_after_exchange": 6.99,</br>"status": "normal",</br>"created_at": 2024-11-18 16:42:03.000000,</br>"modified_at": 2024-11-18 16:42:03.000000</br>}|201 CREATED</br>400 Bad Request</br>404 Not Found|- 성공하면 201 반환</br>- 필드 누락이면 400 반환|
|특정 고객이 수행한 환전 요청 조회|GET|/exchanges/{userId}|GET /exchanges/1 HTTP/1.1||[</br>{</br>"id": 1,</br>"user_id": 1,</br>"currency_id": 1,</br>"amount_in_krw": 10000,</br>"amount_after_exchange": 6.99,</br>"status": "normal",</br>"created_at": 2024-11-18 16:42:03.000000,</br>"modified_at": 2024-11-18 16:42:03.000000</br>}</br>]|200 OK|특정 고객이 여러 종류의 환전 요청을 했을 수도 있기 때문에 배열로 응답|
|특정 환전 요청 상태를 취소로 변경|PATCH|/exchanges/{userId}|PATCH /exchanges/1 HTTP/1.1</br>Content-Type: application/json|{</br>"currency_id": 1</br>}|{</br>"id": 1,</br>"user_id": 1,</br>"currency_id": 1,</br>"amount_in_krw": 10000,</br>"amount_after_exchange": 6.99,</br>"status": "canceled",</br>"created_at": 2024-11-18 16:42:03.000000,</br>"modified_at": 2024-11-19 16:42:03.000000</br>}|200 OK</br>400 Bad Request</br>404 Not Found|- 성공하면 200 반환</br>- 필드 누락이면 400 반환</br>- 요청한 내역이 없으면 404 반환|
|고객이 삭제될 때 해당 고객이 수행한 모든 환전 요청도 삭제|DELETE|/users/{id}|DELETE /users/1 HTTP/1.1|||200 OK|cascade 적용|

----------------------

# ☁ ERD
<img src="https://github.com/user-attachments/assets/f1b388fa-38a7-4057-b7cc-0e69506a70f8">
