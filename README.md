# 2022 Computer Network
1. ARP Protocol
2. Static Router

# TermProject#1 ARP

## ARP Protocol
IP 주소를 이용하여 MAC Address 를 알아내기 위한 프로토콜 
### Basic ARP

### Proxy ARP
### Gratuitous ARP

### TimeOut
- Completed Entry : 20 minutes
- Incomplete Entry : 3 minutes

## 구현 목표
### Layer Structure
![img](https://user-images.githubusercontent.com/81208791/193212564-697f86bb-0696-4ea2-8037-2233dc866bed.png)


### GUI Layout
![img_1](https://user-images.githubusercontent.com/81208791/193212624-7a132b05-d467-4ce4-ab5d-cb3197b66982.png)


## ToDo List

### 1. Application Layer
- [x] GUI 생성
- [ ] 각 Layer 연결
- [ ] cache table update

### 2. ARP Layer
Basic ARP, Proxy ARP, Grauitous ARP에 대한 모든 기능 구성
- [x] ARP_MSG의 구성 요소
- [x] ARP Cache Table 자료 구조 결정
- [x] 각각의 Entry Class로 구성
- [x] ARP_MSG 의 초기화
- [ ] Send 함수, Receive 함수 수정
- [ ] ARP Request에 대한 응답을 보내는 함수 생성(자기 주소 입력 하고 Swapping하는 등의 과정)
- [ ] ARP Layer는 IP Layer와는 단방향 연결, Ethernet Layer과는 양방향 연결)
- [ ] TimeOut 관련해서 어떻게 할 것인지?


### 3. TCP Layer
- [x] TCP Header 생성
- [ ] Send() 함수 생성

### 4. IP Layer
- [x] IP Header 생성
- [ ] Send() 함수 생성
- [ ] IP 주소는 GUI에 나타나야 하므로 String으로 변경하는 것

### 5. Ethernet Layer
- [ ] 이전 과제 참고해서 필요한 함수들 구현

### 6. 추가 구현(ChatFileDlg)
- [ ] GUI 추가 구현
- [ ] Layer 연결 수정
- [ ] 각각의 Chat/File과 연결
