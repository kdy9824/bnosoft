# 자바 버전
FROM openjdk:17

# 도커 이미지 관리하는 사람
LABEL maintainer="moonhyejee"

# JAR_FILE 위치
ARG JAR_FILE=build/libs/bno2-0.0.1-SNAPSHOT.jar

# JAR_FILE에 도커 이미지로 사용할 이름 입력
# .jar의 이름은 bno2-0.0.1-SNAPSHOT.jar 이지만 도커에서는 docker-springboot.jar로 출력
ADD ${JAR_FILE} docker-springboot.jar

#외부포트 8082로 노출
EXPOSE 8082

# 컨테이너 실행 시 반드시 실행될 명령어를 입력
# []로 여러 명령어 지정 가능
# 기본적으로 자바는 빌드된 .jar를 실행할 때 java -jar <빌드 파일명>으로 실행하기 때문에 java, /docker-springboot.jar는 필수로 입력되므로 추가
# -Djava.security.egd=file:/dev/./urandom 옵션의 경우 세션 ID를 생성할 때 필요한 난수 파일을 미리 적용시켜 톰캣의 실행 속도를 빠르게 하고자 추가한 옵션
ENTRYPOINT ["java","-jar","/docker-springboot.jar"]