# 📑 Project: Smart Mobility DX – DeepLearning Integration

## 📌 Project Overview

본 프로젝트는 **스마트 모빌리티 DX 과정**의 일환으로 수행된 딥러닝 기반 예측 시스템입니다. 붓꽃(Iris), 당뇨병(Diabetes) 데이터셋에 이어 **와인(Wine) 성분 데이터를 활용한 이진 분류 모델**을 구축하였습니다.

가장 큰 특징은 사용자가 웹 UI를 통해 학습 횟수와 데이터를 입력하면, **Java Spring Boot가 Python 프로세스를 실행**하여 모델을 학습/예측하고 그 결과를 다시 **Oracle DB**에 자동 저장하는 **Full-stack AI 연동**을 구현한 점입니다.

## 🛠 Tech Stack
**AI/Deep Learning**: TensorFlow 2.x (Keras), Scikit-learn (StandardScaler).
**Backend**: Java Spring Boot 3, Spring Data JPA.
**Database**: Oracle SQL Developer.
**Frontend**: Thymeleaf, Bootstrap, JavaScript (Ajax/Post).

## ⚙️ Key Implementations

### 1. 딥러닝 모델 설계 (Wine Classification)
**Input Features**: 이산화황, 휘발성 산도, 염화물, 황산염 (4개 독립변수).
**Architecture**:
* `InputLayer`: 4 Features.
* `Hidden Layers`: 18 nodes (ReLU) → 8 nodes (ReLU).
* `Output Layer`: 1 node (Sigmoid) – 화이트/레드 와인 이진 분류.
**Optimization**: Adam Optimizer & Binary Cross-entropy Loss.
**Model Management**: `ModelCheckpoint`를 통해 검증 정확도(`val_accuracy`)가 가장 높은 최적 모델을 `.keras` 형식으로 저장.

### 2. Spring Boot & Python REST 연동 파이프라인
**사용자 입력**: 웹 브라우저의 Prompt 창을 통해 학습 반복 횟수(Epochs) 및 테스트 데이터를 입력받습니다.
**프로세스 제어**: `ProcessBuilder`를 통해 파이썬 스크립트를 동적으로 실행하며 파라미터를 전달합니다.
**데이터 피드백**: 파이썬의 `requests` 라이브러리를 사용해 예측값, 손실률, 정확도를 Spring 서버의 `@PostMapping("/wineout")`으로 전송합니다. 
**결과 영속화**: 전달받은 데이터를 DTO를 거쳐 Entity로 변환 후 Oracle DB(`WINE2024` 테이블)에 저장합니다.

## 🖥 Execution Results
**학습 로그**: `ModelCheckpoint`에 의해 각 에포크마다 최적의 모델 가중치가 갱신되는 것을 확인할 수 있습니다.
**데이터베이스**: Oracle SQL Developer를 통해 수집된 학습 결과(Loss, Accuracy, Epochs)와 분류 결과(화이트/레드 와인)가 정상적으로 저장됨을 증명하였습니다.
**결과 뷰**: 사용자는 `/wineview` 페이지에서 저장된 모든 학습 이력을 테이블 형태로 열람할 수 있습니다.

| ID (NUM3) | Loss (LOSS3) | Accuracy (ACCURACY3) | Epochs | Result (NAME3) |
| --- | --- | --- | --- | --- |
| 1000 | 0.0513 | 0.9853 | 5 | 화이트와인 
| 1001 | 0.0524 | 0.9861 | 5 | 레드와인 
