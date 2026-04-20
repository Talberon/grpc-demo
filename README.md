# gRPC Web Chat Demo

A demo project showcasing a gRPC-based Web Chat service with a .NET server and client implementations in multiple languages.

## Overview

The server exposes a streaming gRPC Web Chat service defined in `protobuf/webchat-service.proto`. Clients connect to the server, join a chat room, and exchange messages in real time using bidirectional streaming.

**Server:** .NET (C#)  
**Clients:** .NET (C#), Go, Java, Node.js, Android (Kotlin)

All implementations connect to `localhost:9090` by default.

---

## Server — .NET

**Requirements:** .NET 10 SDK

```bash
cd grpc-demo-dotnet
dotnet run --project GrpcDemoDotnet/GrpcDemoDotnet.csproj -- server
```

---

## Clients

### .NET (C#)

```bash
cd grpc-demo-dotnet
dotnet run --project GrpcDemoDotnet/GrpcDemoDotnet.csproj -- client
```

### Go

**Requirements:** Go 1.24+

```bash
cd grpc-demo-golang
go run main.go
```

### Java

**Requirements:** JDK 8+

```bash
cd grpc-demo-java
./gradlew run
```

### Node.js

**Requirements:** Node.js, npm

```bash
cd grpc-demo-nodejs
npm install
npm run start:chat
```

### Android

**Requirements:** Android Studio

Open `grpc-demo-android` in Android Studio and run on a device or emulator. Ensure the emulator can reach the server at `10.0.2.2:9090` (the Android emulator's alias for `localhost` on the host machine).
