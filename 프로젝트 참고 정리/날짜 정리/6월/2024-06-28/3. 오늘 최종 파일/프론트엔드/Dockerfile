# 베이스 이미지로 node 사용
FROM node:14

# 앱 디렉토리 생성
WORKDIR /app

# 앱 소스 추가
COPY . .

# 의존성 설치
RUN npm install

# 앱 빌드
RUN npm run build

# Nginx 설치
FROM nginx:alpine

# 빌드된 파일들을 Nginx 디폴트 경로로 복사
COPY --from=0 /app/build /usr/share/nginx/html

# Nginx 포트 노출
EXPOSE 80

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]