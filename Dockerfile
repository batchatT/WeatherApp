FROM eclipse-temurin:17-jdk-jammy

# Install required tools
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    git

# Set Android SDK version
ENV ANDROID_SDK_ROOT /opt/android-sdk
ENV ANDROID_COMPILE_SDK 34
ENV ANDROID_BUILD_TOOLS 34.0.0

# Download and install Android SDK
RUN mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools && \
    wget -q https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip && \
    unzip commandlinetools-linux-11076708_latest.zip -d ${ANDROID_SDK_ROOT}/cmdline-tools && \
    mv ${ANDROID_SDK_ROOT}/cmdline-tools/cmdline-tools ${ANDROID_SDK_ROOT}/cmdline-tools/latest

ENV PATH ${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin:${ANDROID_SDK_ROOT}/platform-tools

# Accept licenses and install SDK components
RUN yes | sdkmanager --licenses && \
    sdkmanager "platforms;android-${ANDROID_COMPILE_SDK}" \
               "build-tools;${ANDROID_BUILD_TOOLS}" \
               "platform-tools"