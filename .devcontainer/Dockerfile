FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu

ARG MAVEN_VERSION=4.0.0-rc-1
ARG USER_HOME_DIR="/home"
ARG AZURE_FUNCTIONS_CLI_VERSION=4

ENV DEBIAN_FRONTEND=noninteractive
ENV UBUNTU_VERSION=noble

# Install Maven
RUN apt-get update && \
    apt-get install -y curl tar && \
    curl -fsSL https://archive.apache.org/dist/maven/maven-4/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz -o /tmp/apache-maven.tar.gz && \
    tar -xzf /tmp/apache-maven.tar.gz -C /opt && \
    ln -s /opt/apache-maven-${MAVEN_VERSION} /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn && \
    rm /tmp/apache-maven.tar.gz && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install Azure Functions Core Tools
RUN apt-get update && apt-get install -y \
    libicu-dev azure-functions-core-tools-${AZURE_FUNCTIONS_CLI_VERSION} && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

ENV MAVEN_HOME /opt/maven